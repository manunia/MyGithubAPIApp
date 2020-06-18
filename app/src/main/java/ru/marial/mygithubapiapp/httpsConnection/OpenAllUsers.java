package ru.marial.mygithubapiapp.httpsConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.marial.mygithubapiapp.model.UserRequest;
import ru.marial.mygithubapiapp.model.Users;

public interface OpenAllUsers {
    @GET("/users")
    Call<List<Users>> loadAllUsers(@Query("per_page") int per_page, @Query("since") int since);

    @GET("/users/{login}")
    Call<UserRequest> loadUserRequest(@Path("login") String login);
}
