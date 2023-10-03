package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignInActivity extends AppCompatActivity {


    private EditText pass;
    private EditText email;
    private EditText name;
    private EditText confirmPass;

    private MaterialButton signInBtn;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        name = findViewById(R.id.Name);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.Email);
        confirmPass = findViewById(R.id.passConfirm);

        signInBtn = findViewById(R.id.signInBtn);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        user = new User();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = String.valueOf(name.getText());
                String userEmail = String.valueOf(email.getText());
                String userPass = String.valueOf(pass.getText());

                if(TextUtils.isEmpty(userName)) {
                    Toast.makeText(SignInActivity.this,"enter your name", Toast.LENGTH_LONG).show();

                }

                else if(TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(SignInActivity.this,"enter your email", Toast.LENGTH_LONG).show();

                }

                else if(!userPass.equals(confirmPass.getText().toString())) {
                    Toast.makeText(SignInActivity.this,"enter your password korrekt", Toast.LENGTH_LONG).show();

                } else {
                    addDataToFirebase(userName,userEmail,userPass);
                }

            }
        });
    }

    public void openLogInPage() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    private void addDataToFirebase(String name, String email, String password){
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPassword(password);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // databaseReference.setValue(user);
                databaseReference.child(name).setValue(user);
                Toast.makeText(SignInActivity.this, "data added", Toast.LENGTH_SHORT).show();
                openLogInPage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SignInActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });


    }

}