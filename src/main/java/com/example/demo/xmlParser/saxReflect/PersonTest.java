package com.example.demo.xmlParser.saxReflect;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

public class PersonTest {
    public static void main(String[] args) {
        List<Object> objects = PersonSaxFactory
                .xmlToObject("D:\\root.xml", "File", Person.class);
        List<Person> persons = new ArrayList<>();
        for (Object obj:objects) {
            Person person = (Person) obj;
            persons.add(person);
        }
        System.out.println(persons);
        System.out.println(JSON.toJSONString(objects));
    }
}
