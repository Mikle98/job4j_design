package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<Integer> idUsers = new HashSet<>();
        for (User pre : previous) {
            idUsers.add(pre.getId());
            if (!current.contains(pre)) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        for (User curr : current) {
            if (!previous.contains(curr)) {
                if (idUsers.add(curr.getId())) {
                    info.setAdded(info.getAdded() + 1);
                } else {
                    info.setChanged(info.getChanged() + 1);
                    info.setDeleted(info.getDeleted() - 1);
                }
            }
        }
        return info;
    }
}
