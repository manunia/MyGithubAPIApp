package ru.marial.mygithubapiapp.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
