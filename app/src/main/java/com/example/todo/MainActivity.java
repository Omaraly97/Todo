package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    myOwnAdapter myAdapter;
    public ArrayList<todo> todoList;
    String s1[];
    String s2[];
    LinearLayoutManager LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView)findViewById(R.id.myRecycler);
        recyclerView.setHasFixedSize(true);

        todoList = new ArrayList<>();

        buildRecycleViewList();
        buildClickListeners();
    }

    public void createArrayList(){
        s1 = getResources().getStringArray(R.array.todos);
        s2 = getResources().getStringArray(R.array.description);
        for (int i = 0;  i < s1.length; i++){
            todoList.add(new todo (s1[i], s2[i]));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main", "onResume: ");
    }


    public void buildRecycleViewList (){
        createArrayList();
        myAdapter = new myOwnAdapter(todoList,this);
        LayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(LayoutManager);
    }

    public void buildClickListeners() {

        myAdapter.setOnItemClickListener(new myOwnAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                final todo currentTodo = todoList.get(position);
                Intent intent = new Intent(myOwnAdapter.ctx, Activity2.class);
                intent.putExtra(myOwnAdapter.TODO, currentTodo.getTodo());
                intent.putExtra(myOwnAdapter.DESC, currentTodo.getdescription());
                intent.putExtra(myOwnAdapter.CHECKED, currentTodo.isDone());
                myOwnAdapter.ctx.startActivity(intent);
            }

        });

        myAdapter.setOnItemLongClickedListener(new myOwnAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(final int position) {
                final todo currentTodo = todoList.get(position);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myOwnAdapter.ctx);
                alertDialog.setTitle("Delete " + currentTodo.getTodo() );
                alertDialog.setMessage("Are you sure you want to delete?");
                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                           removeItem(position);
                        Toast.makeText(MainActivity.this, "Todo: (" +currentTodo.getTodo() +") is deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return false;
            }
        });

    }
    public void removeItem(int position) {
        todoList.remove(position);
        myAdapter.notifyItemRemoved(position);
    }
}