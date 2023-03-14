package com.example.demo.xmlParser.saxReflect;

import com.alibaba.fastjson2.JSON;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            // 创建指向的文件的对象
            File file = new File("D:\\bookReflect.xml");
            // 1. 得到SAX解析工厂
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 2. 让工厂生产一个sax解析器
            SAXParser parser = spf.newSAXParser();
            // 要解析的node节点 和 解析为的object的类全路径
            XmlParseHandler handler = new XmlParseHandler("book", Book.class);
            // 3. 传入输入流和handler，解析
            parser.parse(new FileInputStream(file), handler);
            // 4. 获得解析结果
            List<Object> list = handler.getList();
            // 5. 遍历输出结果
            System.out.println(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
