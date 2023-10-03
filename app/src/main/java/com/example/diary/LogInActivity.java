package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LogInActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private MaterialButton loginBtn;
    private TextView signIn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.SignIn);

        loginBtn = findViewById(R.id.loginBtn);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(userName.getText());
                String pass = String.valueOf(password.getText());

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.child(name).child("userName").getValue(String.class);
                        String passwordDb = snapshot.child(name).child("userPassword").getValue(String.class);

                        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                            Toast.makeText(LogInActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(value.equals(name) && passwordDb.equals(pass)) {
                            openHomePage();
                            Toast.makeText(LogInActivity.this, "You are logged in ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LogInActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LogInActivity.this, "Fail to get data." + error , Toast.LENGTH_SHORT).show();
                    }
                });

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