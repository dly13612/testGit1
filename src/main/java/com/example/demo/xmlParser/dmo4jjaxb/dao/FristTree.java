package com.example.demo.xmlParser.dmo4jjaxb.dao;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "childNodes")
@XmlAccessorType(XmlAccessType.FIELD)
public class FristTree {

    @XmlElementWrapper(name = "childNode")
    @XmlElement(name = "file")
    private List<FirstReq> childNode = new ArrayList<>();

}
