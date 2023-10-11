package com.example.diary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AddPostFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Spinner spinner;
    String spinnerValue;
    private EditText title;
    private EditText post;
    private MaterialButton addPost;
    Post newPost;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public AddPostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addpost, container, false);

        spinner = view.findViewById(R.id.spinner);
        title = view.findViewById(R.id.title);
        post = view.findViewById(R.id.messagePost);
        addPost = view.findViewById(R.id.addPost);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.category, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Categories");

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String postTime = time.format(formatter);
                String category = spinnerValue;
                String postTitle = String.valueOf(title.getText());
                String message = String.valueOf(post.getText());

                addPostToFirebase(postTime,category,postTitle,message);
               // Toast.makeText(getActivity(), postTime, Toast.LENGTH_LONG).show();
            }
        });





        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerValue  = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),spinnerValue,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addPostToFirebase(String time, String category, String title, String post) {
        newPost = new Post();
        newPost.setTime(time);
        newPost.setTitle(title);
        newPost.setPost(post);
        newPost.setCategory(category);
        String userId;

        FirebaseUser currentUser = mAuth.getCurrentUser();

        userId = currentUser.getUid();


        Toast.makeText(requireActivity(), "Hello" , Toast.LENGTH_SHORT).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // databaseReference.setValue(user);
                databaseReference.child(userId).child(category).child(time).setValue(newPost);
                Toast.makeText(requireActivity(), "data added" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireActivity(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}