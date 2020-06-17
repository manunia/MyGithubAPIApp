package ru.marial.mygithubapiapp;

import android.app.Application;

import androidx.room.Room;

import ru.marial.mygithubapiapp.dao.UserDao;
import ru.marial.mygithubapiapp.dao.UsersDatabase;

public class App extends Application {

    private static App instsnce;

    private UsersDatabase db;

    public static App getInstsnce() {
        return instsnce;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instsnce = this;

        db = Room.databaseBuilder(
                getApplicationContext(),
                UsersDatabase.class,
                "users_database")
                .allowMainThreadQueries() //Только для примеров и тестирования.
                .build();
    }

    public UserDao getUserDao() {
        return db.getUserDao();
    }
}
