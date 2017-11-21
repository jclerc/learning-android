package com.jclerc.appcours;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.username)
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit)
    void onSubmit() {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        Log.d("user", user);
        Log.d("pass", pass);

        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
