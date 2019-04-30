package model;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class Registration {     //Strage
    private static final List<User> list = new ArrayList<User>();

    public void addNewUsers(User alone) {
        list.add(alone);
    }

    public void getList() {
        for (User u : list)
            System.out.println(u);
    }

    public static boolean proverka(User alone) {
        if (list.contains(alone)) {
            return true;
        }
        return false;
    }

    public static String nameLastUsera() {
        int l = list.size();
        User user = list.get(l-1);
        String name = user.getName();
        return name;
    }
}
