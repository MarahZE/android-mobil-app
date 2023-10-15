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
    private CardView work;
    private CardView love;
    private CardView vacation;


    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        family = view.findViewById(R.id.family);
        work = view.findViewById(R.id.work);
        love = view.findViewById(R.id.love);
        vacation = view.findViewById(R.id.vacation);

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPost("Family");
                Toast.makeText(getActivity(),"new toast!" , Toast.LENGTH_LONG).show();
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPost("Work");
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPost("Love");
            }
        });

        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPost("Vacation");
            }
        });

        return view;
    }

    public void openViewPost(String category) {
        Intent intent = new Intent(getActivity(), ViewPost.class);
        intent.putExtra("Category",category);
        startActivity(intent);
    }
}