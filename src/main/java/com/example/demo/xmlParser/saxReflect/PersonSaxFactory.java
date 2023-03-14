package com.example.demo.xmlParser.saxReflect;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonSaxFactory {
    static SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    public static List<Object> xmlToObject(String xmlPath, String remark, Class clazz) {
        List<Object> list = new ArrayList<>();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // 3. 传入输入流和handler，解析
            File file = new File(xmlPath);
            InputStream in = new FileInputStream(file);
            PersonParseHandler handler = new PersonParseHandler(remark, clazz);
            saxParser.parse(in, handler);
            // 4. 获得解析结果
            list = handler.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
