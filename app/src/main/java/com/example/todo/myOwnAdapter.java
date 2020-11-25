package com.example.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myOwnAdapter extends RecyclerView.Adapter<myOwnAdapter.myOwnHolder> {

    public static final String TODO = "TODO";
    public static final String DESC = "DESC";
    public static final String CHECKED = "CHECKED";

    private  ArrayList<todo> todoList;
    public static Context ctx;

    //Adapter Constructor
    public myOwnAdapter(ArrayList<todo> todoList, Context ct){
        this.todoList = todoList;
        ctx = ct;

    }

    @NonNull
    @Override
    public myOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ctx);
        View myView = myInflator.inflate(R.layout.my_row, parent, false);
        return new myOwnHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull myOwnHolder holder, final int position) {
        final  todo currentTodo = todoList.get(position);

        holder.t1.setText(currentTodo.getTodo());
        holder.t2.setText(currentTodo.getdescription());

        holder.taskState.setOnCheckedChangeListener(null);
        holder.taskState.setChecked(currentTodo.isDone());
        holder.taskState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currentTodo.setDone(true);
                } else {
                    currentTodo.setDone(false);
                }
            }
        });

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Activity2.class);
                intent.putExtra(TODO, currentTodo.getTodo());
                intent.putExtra(DESC, currentTodo.getdescription());
                intent.putExtra(CHECKED, currentTodo.isDone());
                ctx.startActivity(intent);
            }
        });

        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
                alertDialog.setTitle("Delete item");
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
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return false;
            }
        });
    }

    //function to remove
    public void removeItem(int position) {
        todoList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class myOwnHolder extends RecyclerView.ViewHolder {

        RelativeLayout mainLayout;
        TextView t1;
        TextView t2;
        public CheckBox taskState;

        public myOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.text1);
            t2 = (TextView)itemView.findViewById(R.id.text2);
            taskState = (CheckBox) itemView.findViewById(R.id.checkBox);

            mainLayout = (RelativeLayout)itemView.findViewById(R.id.mainLayout);
        }
    }
}
