package ru.marial.mygithubapiapp.dao;

import java.util.ArrayList;
import java.util.List;

import ru.marial.mygithubapiapp.model.Users;

public class UsersDataSource {

    private List<Users> users;


    public UsersDataSource() {
        users = new ArrayList<>();

    }

    public List<Users> getUsers() {
        return users;
    }

    public void addUsers(Users user) {
        users.add(user);
    }

    public long getCountUsers() {
        if (users !=null) {
            return users.size();
        } else return 0;
    }

}
