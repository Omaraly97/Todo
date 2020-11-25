package com.example.todo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity {
    Button btnBack;
    Intent homeIntent;
    TextView todo;
    TextView desc;
    CheckBox todoState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inner_todo);
        homeIntent = getIntent();
        todo = (TextView)findViewById(R.id.innerTodo);
        desc = (TextView)findViewById(R.id.innerDesc);
        todoState = (CheckBox)findViewById(R.id.checkBox);


        todo.setText(homeIntent.getStringExtra(myOwnAdapter.TODO));
        desc.setText(homeIntent.getStringExtra(myOwnAdapter.DESC));
        todoState.setOnCheckedChangeListener(null);
        todoState.setChecked(homeIntent.getBooleanExtra(myOwnAdapter.CHECKED, false));

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}


