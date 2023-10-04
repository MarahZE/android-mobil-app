package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class LogInActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText password;
    private MaterialButton loginBtn;
    private TextView signIn;
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.SignIn);

        loginBtn = findViewById(R.id.loginBtn);

        userEmail = findViewById(R.id.userEmail);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(userEmail.getText());
                String pass = String.valueOf(password.getText());

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users");

               /* databaseReference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String passwordDb = dataSnapshot.child("userPassword").getValue(String.class);

                                if (pass.equals(passwordDb)) {
                                    openHomePage();
                                    Toast.makeText(LogInActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(LogInActivity.this, "User doesn't exist!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LogInActivity.this, "Failed", Toast.LENGTH_LONG).show();
                        }

                    }
                });*/


                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    openHomePage();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LogInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
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