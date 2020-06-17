package ru.marial.mygithubapiapp.ui;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.marial.mygithubapiapp.Constants;
import ru.marial.mygithubapiapp.R;
import ru.marial.mygithubapiapp.model.UserRequest;

public class UserActivity extends AppCompatActivity {

    private EditText login;
    private EditText followers;
    private EditText biography;
    private EditText fullName;
    private EditText created;
    private EditText company;
    private EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        initFields();
        
        getDataFromMainActivity();
    }

    private void getDataFromMainActivity() {
        UserRequest userRequest = (UserRequest) getIntent().getExtras().getSerializable(Constants.OPEN_USER_INFO);
        if (userRequest != null) {
            login.setText(userRequest.getLogin());
            followers.setText(String.format("%d", userRequest.getFollowers()));
            biography.setText(userRequest.getBiografy());
            fullName.setText(userRequest.getFull_name());
            created.setText(userRequest.getCreated());
            company.setText(userRequest.getCompany());
            email.setText(userRequest.getEmail());
        }else {
            new MyAlertDialogBuilder(UserActivity.this,"Exception",getResources().getText(R.string.incorrect_name).toString()).build();
        }
    }

    private void initFields() {
        login = findViewById(R.id.user_login);
        followers = findViewById(R.id.user_followers);
        biography = findViewById(R.id.user_biography);
        fullName = findViewById(R.id.user_full_name);
        created = findViewById(R.id.user_created);
        company = findViewById(R.id.user_company);
        email = findViewById(R.id.user_email);
    }
}
