package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    myOwnAdapter myAdapter;
    public ArrayList<todo> todoList = new ArrayList<>();
    String s1[];
    String s2[];
    LinearLayoutManager LayoutManager;
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView)findViewById(R.id.myRecycler);

        //store array of todos
        s1 = getResources().getStringArray(R.array.todos);
        s2 = getResources().getStringArray(R.array.description);
        Toast.makeText(this, "list have been refreshed", Toast.LENGTH_SHORT).show();
        for (int i = 0;  i < s1.length; i++){
            todoList.add(new todo (s1[i], s2[i]));
        }

        myAdapter = new myOwnAdapter(todoList,this);
        LayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(LayoutManager);


    }
}