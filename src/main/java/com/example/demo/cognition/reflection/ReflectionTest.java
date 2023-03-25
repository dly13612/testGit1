package com.example.demo.cognition.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        //反射获取方式一
        Student student = new Student();
        Class c1 = student.getClass();
        System.out.println(c1.hashCode());
        //反射获取方式二
        Class c2 = Student.class;
        System.out.println(c2.hashCode());
        //反射获取方式三
        Class c3= Class.forName("com.example.demo.cognition.reflection.Student");
        System.out.println(c3.hashCode());
        Student student3 = (Student) c3.newInstance();
        System.out.println(c3.getName());
        System.out.println(c3.getSimpleName());

        Field[] fields = c3.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.err.println(field);
        }

        Field name = c3.getDeclaredField("name");
        name.setAccessible(true);
        name.set(student3, "DL");


        Method[] methods = c3.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Method setAihao = c3.getDeclaredMethod("setAihao", String.class);
        setAihao.invoke(student3, "DL");
        System.err.println(student3);

    }
}

class Person{
    String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

@Target(value = {ElementType.FIELD,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface StudentAnno{

}

class Student extends Person{
    @StudentAnno
    private String name;
    private String aihao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAihao() {
        return aihao;
    }

    public void setAihao(String aihao) {
        this.aihao = aihao;
    }

    public Student() {
    }

    public Student(String name, String aihao) {
        this.name = name;
        this.aihao = aihao;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", aihao='" + aihao + '\'' +
                '}';
    }
}
