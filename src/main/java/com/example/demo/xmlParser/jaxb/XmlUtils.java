package com.example.demo.xmlParser.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @Auther: KJ.Li
 * @Description:
 * @Date: Create in 2020/5/6
 * @Modified By:
 */
public class XmlUtils {

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @return
     */
    public static  String convertToXml(Object obj){
        return convertToXml(obj,"UTF-8",false);
    }

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
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
     *
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJava(String xml, Class<T> c) {
        if (xml == null || xml.equals(""))
            return null;
        T t = null;
        //字符输入流
        StringReader reader = null;
        try {
            //获得JAXBContext实例
            JAXBContext context = JAXBContext.newInstance(c);
            //创建一个可以用来将XML数据转换为java内容树的Unmarshaller对象.
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new StringReader(xml);
//            XMLWriter writer = new XMLWriter();
            //写入给定的对象，该对象应该是字符串、节点或节点列表
//            Document
//            writer.write();
            t = (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
        return t;
    }
}
