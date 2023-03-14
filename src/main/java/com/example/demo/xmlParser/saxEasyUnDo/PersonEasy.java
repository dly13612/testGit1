package com.example.demo.xmlParser.saxEasyUnDo;

import java.util.ArrayList;
import java.util.List;

public class PersonEasy {
    private String name;

    private String desc;

    private List<PersonEasy> childNode = new ArrayList<>();

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

    public List<PersonEasy> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<PersonEasy> childNode) {
        this.childNode = childNode;
    }
}
