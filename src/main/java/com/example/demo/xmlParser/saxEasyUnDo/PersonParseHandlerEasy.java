package com.example.demo.xmlParser.saxEasyUnDo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PersonParseHandlerEasy extends DefaultHandler {
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
    public PersonParseHandlerEasy(String nodeName, Class clazz) {
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

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        if (nodeName.equals(qName)) {
            try {
                this.object = clazz.newInstance();
            } catch (Exception e) {
                System.out.println("对像创建失败");
            }
        }
        if (attributes != null && !qName.equals("incloud")) {
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
        System.err.println(qName);
        if (qName.equals("incloud")){
            Field[] declaredFields = clazz.getDeclaredFields();
            try {
                for (int j = 0; j < declaredFields.length; j++) {
                    if ("childNode".equals(declaredFields[j].getName())) {
                        declaredFields[j].setAccessible(true);
                        String url = "D:\\" + attributes.getValue("src");
                        declaredFields[j].set(object,
                                PersonSaxFactoryEasy.xmlToObject(url, nodeName, clazz));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (nodeName.equals(qName)) {
            list.add(object);
//            object = null;
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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
