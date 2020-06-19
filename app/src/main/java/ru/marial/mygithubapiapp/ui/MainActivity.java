package ru.marial.mygithubapiapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import ru.marial.mygithubapiapp.R;
import ru.marial.mygithubapiapp.dao.UsersDataSource;
import ru.marial.mygithubapiapp.httpsConnection.RetrofitConnection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private UsersDataSource dataSource;

    private RetrofitConnection connection;

    private int per_page = 11;
    private int since = 0;


    private final String BASE_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new RetrofitConnection();
        connection.initRetrofit(BASE_URL);

        initList();
    }

    private ListAdapter initList() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSource = new UsersDataSource();

        connection.requestAllUsers(per_page, MainActivity.this, dataSource);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

                connection.requestSinceUsers(since+=per_page, MainActivity.this, dataSource);

            }
        });

        return adapter;
    }

    private void showUserInfo(String name) {
        connection.requestOneUser(name,MainActivity.this);
    }

}
