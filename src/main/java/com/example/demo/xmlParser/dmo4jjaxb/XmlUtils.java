package com.example.demo.xmlParser.dmo4jjaxb;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class XmlUtils {
    private static Map<String, String> map = new HashMap<>();
    //解析器
    private static SAXReader saxReader;

    @Value("${xmlTemplate.windows:D:/xmlTemp/}")
    private String windows;
    @Value("${xmlTemplate.linux:home/xmlTemp/}")
    private String linux;

    static {
        saxReader = new SAXReader();
        map.put("dk","dom4j/root.xml");
    }

    public <T> T getXmlToObject(String dk, Class clazz) throws Exception {
        T t = null;
        String tempPath = "";
        //判断是Windows或Linux系统，取对应路径
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")){
            tempPath = "D:/xmlTemp/";
        }
        if (System.getProperty("os.name").toLowerCase().startsWith("linux")){
            tempPath = "home/xmlTemp/";
        }
        String prifiex = map.get(dk).substring(0,map.get(dk).lastIndexOf("/")+1);
        String lastPath = tempPath + prifiex;
        String tempPathXml = lastPath + String.format("%s.xml",dk);
        Document document = saxReader.read(ClassPathResource.class
                .getClassLoader().getResourceAsStream(map.get(dk)));
        recurrence(document.getRootElement(), dk);
        //①document直接转成流，不生成中间临时文件
        InputStream inputStream = getByteArrayInputStream(document);
        //②通过生成中间临时文件方式读取解析
//        File file = new File(tempPathXml);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        OutputFormat outputFormat = new OutputFormat();
//        outputFormat.setEncoding("UTF-8");
//        Writer writer = new FileWriter(tempPathXml);
//        XMLWriter xmlWriter = new XMLWriter(writer,outputFormat);
//        // 设置是否转义，默认使用转义字符
//        xmlWriter.setEscapeText(false);
//        xmlWriter.write(document);
//        xmlWriter.close();

        //JAXBContext实列
        JAXBContext context = JAXBContext.newInstance(clazz);
        //获取Marshaller对象，用作将bean编组(转换)为xml
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //②通过生成中间临时文件方式读取解析
//        t = (T) unmarshaller.unmarshal(new FileInputStream(file));
//        file.delete();
        //①document直接转成流，不生成中间临时文件
        t = (T) unmarshaller.unmarshal(inputStream);
        return t;
    }

    private InputStream getByteArrayInputStream(Document document){
        InputStream inputStream = null;
        try {
            //byte 数组输出流
//            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//            //控制输出格式
//            OutputFormat outputFormat = new OutputFormat();
//            outputFormat.setEncoding("UTF-8");
//            //输出数据类
//            XMLWriter xmlWriter = new XMLWriter(byteOut, outputFormat);
//            xmlWriter.write(document);
//            byte[] byteArr = byteOut.toByteArray();
//            inputStream = new ByteArrayInputStream(byteArr);
            inputStream = new ByteArrayInputStream(document.asXML().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputStream;
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
}
