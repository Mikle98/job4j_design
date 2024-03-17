package ru.job4j.io.serialization.java;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import org.json.JSONObject;

@XmlRootElement(name = "myClass")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyClass {
    @XmlAttribute
    private int integer;
    @XmlAttribute
    private boolean bool;
    @XmlAttribute
    private String str;
    @XmlElement
    private MyClass2 myClass2;
    @XmlElement
    @XmlElementWrapper(name = "arrI")
    private int[] arrInt;

    public MyClass() {

    }

    public MyClass(int integer, boolean bool, String str, MyClass2 myClass2, int[] arrInt) {
        this.integer = integer;
        this.bool = bool;
        this.str = str;
        this.myClass2 = myClass2;
        this.arrInt = arrInt;
    }

    @Override
    public String toString() {
        return "MyClass{"
               + "integer=" + integer
               + ", bool=" + bool
               + ", str='" + str + '\''
               + ", myClass2=" + myClass2
               + ", arrInt=" + Arrays.toString(arrInt)
               + '}';
    }

    public int getInteger() {
        return integer;
    }

    public boolean isBool() {
        return bool;
    }

    public String getStr() {
        return str;
    }

    public MyClass2 getMyClass2() {
        return myClass2;
    }

    public int[] getArrInt() {
        return arrInt;
    }
}
