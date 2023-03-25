package com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject;

import com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao.FirstVo;
import com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao.ThreeVo;
import com.example.demo.xmlParser.dmo4jjaxb.xmlOrObject.dao.TwoVo;

import java.util.ArrayList;
import java.util.List;

public class XmlOrObjectTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("threeStr1");
        list.add("threeStr2");

        FirstVo firstVo = new FirstVo();
        firstVo.setTemplateNo("000004");

        List<TwoVo> twoVos = new ArrayList<>();
        TwoVo twoVo1 = new TwoVo();
        twoVo1.setTwoTags("twoTag1");
        List<ThreeVo> threeVoList1 = new ArrayList<>();
        ThreeVo threeVo1 = new ThreeVo();
        threeVo1.setThreeTags("threeVo1");
        threeVo1.setStrList(list);
        threeVoList1.add(threeVo1);
        ThreeVo threeVo11 = new ThreeVo();
        threeVo11.setThreeTags("threeVo11");
        threeVo11.setStrList(list);
        threeVoList1.add(threeVo11);
        twoVo1.setThreeVos(threeVoList1);
        twoVos.add(twoVo1);

        TwoVo twoVo2 = new TwoVo();
        twoVo2.setTwoTags("twoTag2");
        List<ThreeVo> threeVoList2 = new ArrayList<>();
        ThreeVo threeVo2 = new ThreeVo();
        threeVo2.setThreeTags("threeVo2");
        threeVo2.setStrList(list);
        threeVoList2.add(threeVo2);
        ThreeVo threeVo22 = new ThreeVo();
        threeVo22.setThreeTags("threeVo22");
        threeVo22.setStrList(list);
        threeVoList2.add(threeVo22);
        twoVo2.setThreeVos(threeVoList2);
        twoVos.add(twoVo2);
        firstVo.setTwoVos(twoVos);

        String str = XmlOrObjectUtils.convertToXml(firstVo);
        System.out.println(str);

        FirstVo firstVo1 = XmlOrObjectUtils
                .convertToJava("src/main/resources/messageTemp.xml", FirstVo.class);
        System.out.println(firstVo1);
    }
}
