package com.example.demo.xmlParser.saxReflect;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class XmlParseHandler extends DefaultHandler {
        // xml节点名称
        private String nodeName;
        // JavaBean类对象
        private Class clazz;
        // 存储解析的数据
        private Object object;
        // 存储所有解析的数据
        private List<Object> list;
        // 当前xml标签
        private String currentTag;
        // 构造方法 做些初始化工作
        public XmlParseHandler(String nodeName, Class clazz) {
            this.nodeName = nodeName;
            this.clazz = clazz;
            this.list = new ArrayList<Object>();
        }
        // 返回所有数据
        public List<Object> getList() {
            return list;
        }

        @Override
        public void startDocument() {
            System.out.println("开始解析文档");
        }

        @Override
        public void endDocument() {
            System.out.println("结束解析文档");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            currentTag = qName;
            if (nodeName.equals(qName)) {
                try {
                    this.object = clazz.newInstance();
                } catch (Exception e) {
                    System.out.println("对像创建失败");
                }
            }
            if (attributes != null) {
                Field[] declaredFields = clazz.getDeclaredFields();
                try {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        for (int j = 0; j < declaredFields.length; j++) {
                            String fieldname = declaredFields[j].getName();
                            if (attributes.getQName(i).equals(fieldname)) {
                                declaredFields[j].setAccessible(true);
                                declaredFields[j].set(object, attributes.getValue(i));
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 每一个标签结束时调用
        public void endElement(String uri, String localName, String qName) {
            if (nodeName.equals(qName)) {
                list.add(object);
                object = null;
            }
            currentTag = null;
        }

        // 当解析到标签中的内容的时候调用(换行也是文本内容)
        public void characters(char[] ch, int start, int length) {
            String contents = new String(ch, start, length).trim();
            if (!contents.isEmpty()) {
                Field[] declaredFields = clazz.getDeclaredFields();
                try {
                    for (int i = 0; i < declaredFields.length; i++) {
                        String fieldname = declaredFields[i].getName();
                        if (currentTag.equals(fieldname)) {
                            declaredFields[i].setAccessible(true);
                            declaredFields[i].set(object, contents);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


