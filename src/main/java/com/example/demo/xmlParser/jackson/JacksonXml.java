package com.example.demo.xmlParser.jackson;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.util.List;

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
        System.out.println(book);
    }
}
