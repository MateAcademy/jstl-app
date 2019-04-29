import java.util.ArrayList;
import java.util.List;

public class UserRegistrated1 {     //Strage
    private static final List<User> list = new ArrayList<User>();

    void addNewUsers(User alone) {
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
