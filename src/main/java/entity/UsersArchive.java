package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dovashar on 02.11.2016.
 */
public class UsersArchive {
    public static final UsersArchive INSTANCE = new UsersArchive();
    private List<User> usersList = new ArrayList<User>();

    public void addUser(User user)
    {
        usersList.add(user);
    }

    public List<User> getUsersList()
    {
        return usersList;
    }
}
