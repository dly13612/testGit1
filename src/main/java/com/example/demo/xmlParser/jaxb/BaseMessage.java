package com.example.demo.xmlParser.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: KJ.Li
 * @Description:
 * @Date: Create in 2020/4/30
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ChildNode")
public class BaseMessage {
    protected String ToUserName;
    protected String FromUserName;
    protected String MsgType;
    protected String MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId='" + MsgId + '\'' +
                '}';
    }
}
