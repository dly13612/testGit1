package com.example.demo.xmlParser.saxEasyUnDo;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

public class PersonTestEasy {
    public static void main(String[] args) {
        List<Object> objects = PersonSaxFactoryEasy
                .xmlToObject("D:\\root.xml", "File", PersonEasy.class);
        List<PersonEasy> persons = new ArrayList<>();
        for (Object obj:objects) {
            PersonEasy person = (PersonEasy) obj;
            persons.add(person);
        }
        System.out.println(persons);
        System.out.println(JSON.toJSONString(objects));
    }
}
