package ru.marial.mygithubapiapp.dao;

import java.util.List;

public class UsersDataSource {

    private final UserDao userDao;

    private List<User> users;

    public UsersDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isUserExistsinDatabase(String login) {
        if (users != null) {
            for (User u:users) {
                if (u.login.equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void loadUsers() {
        users = userDao.getAllUsers();
    }

    public long getCountUsers() {
        return userDao.getCountUsers();
    }

    public void addUser(User user) {
        userDao.insertUser(user);
        loadUsers();// После изменения БД надо повторно прочесть данные из буфера
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
        loadUsers();
    }

    public void removeUser(long id) {
        userDao.deleteUserById(id);
    }
}
