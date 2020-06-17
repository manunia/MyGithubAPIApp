package ru.marial.mygithubapiapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.List;

import ru.marial.mygithubapiapp.R;
import ru.marial.mygithubapiapp.dao.UsersDataSource;
import ru.marial.mygithubapiapp.httpsConnection.RetrofitConnection;
import ru.marial.mygithubapiapp.model.Users;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private UsersDataSource dataSource;

    private RetrofitConnection connection;

    private int load_users = 15;
    private int load_more_users = 10;

    private final String BASE_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new RetrofitConnection();
        connection.initRetrofit(BASE_URL);
        connection.requestAllUsers(load_users,MainActivity.this);

        dataSource = connection.getDataSource();

        initList();
    }

    private ListAdapter initList() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new ListAdapter(dataSource, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name, int position) {
                showUserInfo(name);
            }
        });

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                connection.requestAllUsers(load_more_users,MainActivity.this);
            }
        });
        return adapter;
    }

    private void showUserInfo(String name) {
        connection.requestOneUser(name,MainActivity.this);
    }

}
