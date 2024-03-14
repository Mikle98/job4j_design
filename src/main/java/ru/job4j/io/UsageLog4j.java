package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte b = 1;
        boolean bool = true;
        float f = 2F;
        double d = 3D;
        char ch = 'x';
        long l = 4L;
        short s = 5;
        LOG.debug("int: {}, byte: {}, boolean: {}, float: {}, double: {}, char: {}, long: {}, short: {}",
                    age, b, bool, f, d, ch, l, s);
    }
}
