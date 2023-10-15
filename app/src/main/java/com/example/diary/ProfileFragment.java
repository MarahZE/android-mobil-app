package com.example.diary;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {


    private TextView name;
    private TextView email;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button logOut;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.currentName);
        email = view.findViewById(R.id.currentEmail);
        logOut = view.findViewById(R.id.logout);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        if(currentUser != null) {
            String userId = currentUser.getUid();

            databaseReference.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            String userName = dataSnapshot.child("userName").getValue(String.class);
                            String userEmail = dataSnapshot.child("userEmail").getValue(String.class);

                            name.setText(userName);
                            email.setText(userEmail);
                        }

                    } else {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                openLogInPage();
            }
        });



        return view;
    }

    public void openLogInPage() {
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        startActivity(intent);
    }
}