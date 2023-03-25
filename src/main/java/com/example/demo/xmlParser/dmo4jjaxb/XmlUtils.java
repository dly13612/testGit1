package com.example.demo.xmlParser.dmo4jjaxb;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

@Component
public class XmlUtils {
    private static Map<String, String> map = new HashMap<>();
    //解析器
    private static SAXReader saxReader;

    static {
        saxReader = new SAXReader();
        Properties properties = new Properties();
        try(InputStream inputStream = ClassPathResource.class.getClassLoader()
                .getResourceAsStream("dom4j/template.properties")) {
            properties.load(inputStream);
            Set<String> set = properties.stringPropertyNames();
            for (String name : set) {
                map.put(name, properties.getProperty(name));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getXmlToObject(String dk, Class clazz) throws Exception {
        T t = null;
        Document document = saxReader.read(ClassPathResource.class
                .getClassLoader().getResourceAsStream(map.get(dk)));
        recurrence(document.getRootElement(), dk);
        //①document直接转成流，不生成中间临时文件
        InputStream inputStream = new ByteArrayInputStream(document.asXML().getBytes());
        //JAXBContext实列
        JAXBContext context = JAXBContext.newInstance(clazz);
        //获取Marshaller对象，用作将bean编组(转换)为xml
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //①document直接转成流，不生成中间临时文件
        t = (T) unmarshaller.unmarshal(inputStream);
        return t;
    }

    private void recurrence(Element rootElement, String dk) throws DocumentException {
        //获取根节点的子节点
        List<Element> elements = rootElement.elements();
        if (elements!=null){
            for (Element element: elements){
                if ("incloud".equals(element.getName())){
                    dom4jXml(element, dk);
                }
                recurrence(element, dk);
            }
        }
    }

    //解析xml文件
    private void dom4jXml(Element incloudeElement, String dk) throws DocumentException {
        String prifiex = map.get(dk).substring(0,map.get(dk).lastIndexOf("/")+1);
        String incloudePath = prifiex + incloudeElement.attributeValue("src");
        //解析xml
        Document document = saxReader.read(ClassPathResource.class
                .getClassLoader().getResourceAsStream(incloudePath));
        //获取父级
        Element parentElement = incloudeElement.getParent();
        //获取document下所有节点
        Element elementNew = document.getRootElement();
        //添加的同级
        parentElement.add(elementNew);
        //继续调用，如果有icloud标签，接续循环解析
        recurrence(elementNew, dk);
        //移除标签名为“incloud”的元素
        parentElement.remove(incloudeElement);
    }

    /**
     * JavaBean转换成xml
     */
    public static String convertToXml(Object obj, String encoding, boolean format) {
        String result = null;
        StringWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            //创建一个可以用来将XML数据转换为java内容树的Unmarshaller对象.
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
