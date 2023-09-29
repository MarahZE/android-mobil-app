package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class SignInActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private EditText email;
    private MaterialButton signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signInBtn = findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogInPage();
            }
        });
    }

    public void openLogInPage() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}