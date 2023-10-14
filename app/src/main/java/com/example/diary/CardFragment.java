package com.example.diary;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class CardFragment extends Fragment {

    private CardView family;

    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        family = view.findViewById(R.id.family);

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPost();
                Toast.makeText(getActivity(),"new toast!" , Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public void openViewPost() {
        Intent intent = new Intent(getActivity(), ViewPost.class);
        startActivity(intent);
    }
}