package com.example.demo.xmlParser.dmo4jjaxb.dao;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "flie")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstReq {

    @XmlAttribute(name = "name")
    private  String name;

    @XmlAttribute(name = "desc")
    private String desc;

    @XmlElementWrapper(name = "childNode")
    @XmlElement(name = "file")
    private List<FirstReq> childNode = new ArrayList<>();

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private List<Option> options = new ArrayList<>();
}
