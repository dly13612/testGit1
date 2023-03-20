package com.example.demo.xmlParser.dmo4jjaxb.dao;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@XmlRootElement(name = "option")
@XmlAccessorType(XmlAccessType.FIELD)
public class Option {

    @XmlAttribute(name = "leavl")
    private String leavl;

    @XmlAttribute(name = "name")
    private String name;
}
