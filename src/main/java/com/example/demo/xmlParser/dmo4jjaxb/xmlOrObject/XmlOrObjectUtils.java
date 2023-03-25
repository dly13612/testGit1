package com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlOrObjectUtils {

    /**
     * JavaBean转换成xml
     */
    public static  String convertToXml(Object obj){
        return convertToXml(obj,"UTF-8",false);
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

    /**
     * xml转换成JavaBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJava(String xml, Class<T> c) {
        if (xml == null || xml.equals(""))
            return null;
        T t = null;
        //字符输入流
        try(InputStream inputStream =new FileInputStream(new File(xml))) {
            //获得JAXBContext实例
            JAXBContext context = JAXBContext.newInstance(c);
            //创建一个可以用来将XML数据转换为java内容树的Unmarshaller对象.
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
