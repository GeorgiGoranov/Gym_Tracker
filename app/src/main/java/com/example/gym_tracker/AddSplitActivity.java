package com.example.gym_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddSplitActivity extends AppCompatActivity {

    ImageView newSplit;
    LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_split);

        list = findViewById(R.id.layout_list);
        newSplit = findViewById(R.id.addMoreExercises);



        newSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNameOfSplit();
            }
        });




    }

    private void addNewNameOfSplit(){
        View view = getLayoutInflater().inflate(R.layout.row_add_exersice,null,false);

        ImageView delete = (ImageView) view.findViewById(R.id.deleteExercise);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(view);
            }
        });
        list.addView(view);

    }

    private void removeView(View view){
        list.removeView(view);
    }
}