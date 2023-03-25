package com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "File")
public class FirstVo {
    @XmlAttribute(name = "templateNo")
    private String templateNo;

    @XmlElementWrapper(name = "twoTags")
    @XmlElement(name = "twoTag")
    private List<TwoVo> twoVos;
}
