package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SplitFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_split, container, false);
        floatingActionButton = v.findViewById(R.id.add_split);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSplitActivity.class));
            }
        });

        return v;


    }


}