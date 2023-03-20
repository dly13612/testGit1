package com.example.demo.xmlParser.dmo4jjaxb;

import com.alibaba.fastjson.JSON;
import com.example.demo.xmlParser.dmo4jjaxb.dao.FristTree;

public class DlTest {

    public static void main(String[] args) throws Exception {
        XmlUtils xmlUtils = new XmlUtils();
        FristTree fristTree = xmlUtils.getXmlToObject("dk", FristTree.class);
        System.out.println(JSON.toJSONString(fristTree));
    }

}
