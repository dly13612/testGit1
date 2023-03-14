package com.example.demo.xmlParser.saxReflect;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;

    private String desc;

    private List<Person> childNode = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Person> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<Person> childNode) {
        this.childNode = childNode;
    }
}
