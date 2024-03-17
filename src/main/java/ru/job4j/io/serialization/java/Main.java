package ru.job4j.io.serialization.java;

import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        final MyClass myClass = new MyClass(10, true, "qwer", new MyClass2(0, "00"), new int[] {1, 2, 3});
        System.out.println(new JSONObject(myClass));
    }
}
