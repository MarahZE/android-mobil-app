package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private EditText email;
    private TextView txt;
    private MaterialButton signInBtn;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

   // private static final String DB_URL = "jdbc:mysql://10.0.2.2:3306/Diary";
   // private static final String USER = "root";
   // private static final String PASS = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.Name);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.Email);

        signInBtn = findViewById(R.id.signInBtn);
        txt = findViewById(R.id.logo);

        progressBar = findViewById(R.id.progressBar);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String userName = String.valueOf(name.getText());
                String userEmail = String.valueOf(email.getText());
                String userPass = String.valueOf(pass.getText());

                if(TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(SignInActivity.this,"enter your email", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignInActivity.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                   // openLogInPage();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


               /* try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();

                    // Example: Execute a SELECT query
                    String query = "SELECT * FROM Users";
                    ResultSet resultSet = stmt.executeQuery(query);

                    // Process ResultSet
                    if (resultSet.next()) {
                        // Retrieve data from the ResultSet
                        Toast.makeText(SignInActivity.this,"", Toast.LENGTH_LONG).show();
                        // Process data
                    }

                    // Close connections
                    resultSet.close();
                    stmt.close();
                    conn.close();
                }catch (Exception e) {
                    Toast.makeText(SignInActivity.this,e.getMessage() + " ", Toast.LENGTH_LONG).show();
                    txt.setText(e.getMessage());
                }*/

            }
        });
    }

    public void openLogInPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}