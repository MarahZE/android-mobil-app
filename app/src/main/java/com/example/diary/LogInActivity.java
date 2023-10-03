package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private MaterialButton loginBtn;
    private TextView signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.SignIn);

        loginBtn = findViewById(R.id.loginBtn);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        String url = "jdbc:mysql://localhost/Diary";
        String userName = "root";
        String password = "";

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* try {
                    Class.forName("com.mysql.cj.Driver");
                    Connection connection = DriverManager.getConnection(url,userName,password);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from Users");

                    if((resultSet.next())) {
                        openHomePage();
                    }

                } catch (Exception e){
                    Toast.makeText(LogInActivity.this," Error:"  + e , Toast.LENGTH_LONG).show();
                }*/


            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInPage();
            }
        });

    }

    public void openSignInPage() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}