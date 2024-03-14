package ru.job4j.io.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final MyClass myClass = new MyClass(10, true, "qwer", new MyClass2(0, "00"), new int[] {1, 2, 3});
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myClass);
        System.out.println(json);
        System.out.println(gson.fromJson(json, MyClass.class));
    }
}
