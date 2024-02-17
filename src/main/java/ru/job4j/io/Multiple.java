package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/Multiple")) {
            int constOne = 1;
            for (int i = 2; i < 10; i++) {
                output.write((constOne + " * " + i + " = " + i).getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
