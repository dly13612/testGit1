package com.example.demo.xmlParser.jackson;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    public long id;
    public String name;
    public String author;
    public String isbn;
    public List<String> tags;
    public String pubDate;
}

