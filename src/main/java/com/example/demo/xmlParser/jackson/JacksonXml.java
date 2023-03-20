package com.example.demo.xmlParser.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

/**
 * 适合单个bean对象，不适合list
 */
public class JacksonXml {
    public static void main(String[] args) throws Exception {
        InputStream input =
                JacksonXml.class.getResourceAsStream("/book.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Book book = mapper.readValue(input, Book.class);
        System.out.println(JSON.toJSONString(book));
    }
}
