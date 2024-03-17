package ru.job4j.io.serialization.java;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "myClass2")
public class MyClass2 {
    @XmlAttribute
    private int i;
    @XmlAttribute
    private String str;

    public MyClass2() {

    }

    public MyClass2(int i, String str) {
        this.i = i;
        this.str = str;
    }

    @Override
    public String toString() {
        return "MyClass2{"
               + "i=" + i
               + ", str='" + str + '\''
               + '}';
    }
}
