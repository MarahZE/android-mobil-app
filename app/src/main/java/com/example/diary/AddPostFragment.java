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


public class AddPostFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Spinner spinner;
    String spinnerValue;
    private EditText title;
    private EditText post;
    private Button addPost;
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

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titlePost = String.valueOf(title.getText());
                String message = String.valueOf(post.getText());
                Toast.makeText(getActivity(), spinnerValue, Toast.LENGTH_LONG).show();
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
}