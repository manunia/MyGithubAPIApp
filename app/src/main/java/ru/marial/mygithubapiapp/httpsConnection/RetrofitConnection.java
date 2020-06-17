package ru.marial.mygithubapiapp.httpsConnection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.marial.mygithubapiapp.Constants;
import ru.marial.mygithubapiapp.dao.UsersDataSource;
import ru.marial.mygithubapiapp.model.UserRequest;
import ru.marial.mygithubapiapp.model.Users;
import ru.marial.mygithubapiapp.ui.MyAlertDialogBuilder;

public class RetrofitConnection {

    private static final String TAG = "USERS";
    private OpenAllUsers openAllUsers;
    private UsersDataSource dataSource;
    private String userLogin;

    public void initRetrofit(String baseUrl) {
        Retrofit retrofit;
        dataSource = new UsersDataSource();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openAllUsers = retrofit.create(OpenAllUsers.class);
    }

    public void requestAllUsers(int per_page, Context context) {
        openAllUsers.loadAllUsers(per_page)
                .enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            Log.e(TAG, response.body().toString());
                            for (Users u : response.body()) {
                                createUsers(u.getLogin(), u.getId(), u.getUrl(), u.getAvatar());
                                setUserLogin(u.getLogin());
                                Log.e(TAG, u.getLogin());
                            }
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            showErrorMessage(response, context);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        new MyAlertDialogBuilder(context, "Exception!", "Fail connection").build();
                        Log.e(TAG, "Fail connection", t);
                    }

                });
    }

    public void requestOneUser(String login, Context context) {
        openAllUsers.loadUserRequest(login)
                .enqueue(new Callback<UserRequest>() {
                    @Override
                    public void onResponse(Call<UserRequest> call, Response<UserRequest> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            Intent intent = new Intent("showUserInfo");
                            intent.putExtra(Constants.OPEN_USER_INFO, response.body());
                            context.startActivity(intent);
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            showErrorMessage(response, context);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRequest> call, Throwable t) {
                        new MyAlertDialogBuilder(context, "Exception!", "Fail connection").build();
                        Log.e(TAG, "Fail connection");
                    }
                });
    }

    private void showErrorMessage(Response response, Context context) {
        try {
            JSONObject jsonError = new JSONObject(response.errorBody().string());
            String error = jsonError.getString("message");
            new MyAlertDialogBuilder(context, "Error!", error).build();
            Log.e(TAG, error);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUsers(String login, long id, String url, String avatar) {
        Users users = new Users(login, id, url, avatar);

        dataSource.addUsers(users);
    }

    public UsersDataSource getDataSource() {
        return dataSource;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
