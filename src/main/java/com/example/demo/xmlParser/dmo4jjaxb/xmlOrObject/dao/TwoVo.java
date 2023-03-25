package com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "twoTag")
public class TwoVo {

    @XmlAttribute(name = "twoTagName")
    private String twoTags;

    @XmlElementWrapper(name = "threeVos")
    @XmlElement(name = "threeVo")
    private List<ThreeVo> threeVos;

}
