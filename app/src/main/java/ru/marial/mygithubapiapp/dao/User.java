package ru.marial.mygithubapiapp.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"login"})})
public class User {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "login")
    public String login;
    @ColumnInfo(name = "avatar")
    public String avatar;
}
