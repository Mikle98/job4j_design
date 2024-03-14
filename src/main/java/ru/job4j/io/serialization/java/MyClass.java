package ru.job4j.io.serialization.java;

public class MyClass {
    private int integer;
    private boolean bool;
    private String str;
    private MyClass2 myClass2;
    private int[] arrInt;

    public MyClass(int integer, boolean bool, String str, MyClass2 myClass2, int[] arrInt) {
        this.integer = integer;
        this.bool = bool;
        this.str = str;
        this.myClass2 = myClass2;
        this.arrInt = arrInt;
    }
}
