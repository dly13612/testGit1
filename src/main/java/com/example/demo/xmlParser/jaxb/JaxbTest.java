package com.example.demo.xmlParser.jaxb;

public class JaxbTest {
    public static void main(String[] args) {
//        List<BaseMessage> list = new ArrayList<>();
//        BaseMessage message = new BaseMessage();
//        message.setToUserName("to名称1");
//        message.setFromUserName("form名称1");
//        message.setMsgId("id1");
//        message.setMsgType("type1");
//        list.add(message);
        BaseMessage message1 = new BaseMessage();
        message1.setToUserName("to名称2");
        message1.setFromUserName("form名称2");
        message1.setMsgId("id2");
        message1.setMsgType("type2");
//        list.add(message1);
        String xmlStr = XmlUtils.convertToXml(message1);
        System.out.println(xmlStr);
        BaseMessage message = XmlUtils.convertToJava("src/main/resources/jaxb.xml", BaseMessage.class);
        System.out.println(message);
    }
}
