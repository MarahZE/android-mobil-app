package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignInActivity extends AppCompatActivity {


    private EditText pass;
    private EditText email;
    private EditText name;
    private EditText confirmPass;

    private MaterialButton signInBtn;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();


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
                    Toast.makeText(SignInActivity.this,"enter your password correct", Toast.LENGTH_LONG).show();

                } else {
                    mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        addDataToFirebase(userName,userEmail,userPass);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignInActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                    //addDataToFirebase(userName,userEmail,userPass);
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
        password = hashPassword(password);
        user.setUserPassword(password);
        String userId;

        FirebaseUser currentUser = mAuth.getCurrentUser();

        userId = currentUser.getUid();

        String b = password;

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // databaseReference.setValue(user);
                databaseReference.child(userId).setValue(user);
                Toast.makeText(SignInActivity.this, "data added" + b , Toast.LENGTH_SHORT).show();
                openLogInPage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SignInActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] result = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < result.length; i++) {
                String hash = Integer.toHexString(0xFF & result[i]);
                while (hash.length() < 2) {
                    hash = "0" + hash;
                }
                stringBuilder.append(hash);
            }
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(getApplication(), e.getMessage() , Toast.LENGTH_LONG).show();
        }
        return "";
    }

}