package com.example.demo.xmlParser.sax;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SaxXml {

    public static void main(String[] args) throws Exception {
        //1.创建解析工厂
        SAXParserFactory sf = SAXParserFactory.newInstance();
        //2.创建解析器
        SAXParser sp = sf.newSAXParser();
        BeanListHandler bh = new BeanListHandler();
        sp.parse(new File(ClassLoader
                .getSystemResource("bookSax.xml").getPath()), bh);
        List<Book> list = bh.getList();
        System.out.println(list);
    }
}

