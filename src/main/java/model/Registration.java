//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Registration {
//    private static final List<User> list = new ArrayList<User>();
//
//    public void addNewUsers(User alone) {
//        list.add(alone);
//    }
//
//    public void getList() {
//        for (User u : list)
//            System.out.println(u);
//    }
//
//    public static boolean checkUser(User alone) {
//        if (list.contains(alone)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static String userLastName() {
//        int l = list.size();
//        User user = list.get(l - 1);
//        String name = user.getName();
//        return name;
//    }
//}
