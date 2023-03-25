package com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "threeVo")
public class ThreeVo {

    @XmlAttribute(name = "threeVoName")
    private String threeTags;

    @XmlElementWrapper(name = "strList")
    @XmlElement(name = "str")
    private List<String> strList;
}
