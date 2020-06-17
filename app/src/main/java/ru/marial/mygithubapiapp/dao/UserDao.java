package ru.marial.mygithubapiapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("DELETE FROM user WHERE id = :id")
    void deleteUserById(long id);
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    @Query("Select * from user where id = :id")
    User getUserById(long id);
    @Query("select count() from user")
    long getCountUsers();
}
