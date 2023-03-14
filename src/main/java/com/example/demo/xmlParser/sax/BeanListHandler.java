package com.example.demo.xmlParser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanListHandler extends DefaultHandler {
    private Book book;
    private List<Book> list = new ArrayList<Book>();
    private String currentTag;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentTag = qName;
        if ("book".equals(currentTag)) {
            book = new Book();
            int id = Integer.parseInt(attributes.getValue("id"));
            book.setId(id);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currentTag = null;
        if ("book".equals(qName)) {
            list.add(book);
            book = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if ("name".equals(currentTag)) {
            String name = new String(ch, start, length);
            book.setName(name);
        }
        if ("author".equals(currentTag)) {
            String author = new String(ch, start, length);
            book.setAuthor(author);
        }
        if ("price".equals(currentTag)) {
            String price = new String(ch, start, length);
            book.setPrice(price);
        }
        if ("tags".equals(currentTag)) {
            String tag = new String(ch, start, length);
            book.setTags(tag != null ? Arrays.asList(tag.split(",")) : null);
        }
    }

    public List<Book> getList() {
        return list;
    }

}
