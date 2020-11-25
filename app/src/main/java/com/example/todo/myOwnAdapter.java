package com.example.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myOwnAdapter extends RecyclerView.Adapter<myOwnAdapter.myOwnHolder> {

    public static final String TODO = "TODO";
    public static final String DESC = "DESC";
    public static final String CHECKED = "CHECKED";

    private  final ArrayList<todo> todoList;
    public static Context ctx;


    private OnItemClickListener mListener;
    private OnItemLongClickListener LListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnItemLongClickedListener(OnItemLongClickListener listener) {
        LListener = listener;
    }


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
        myOwnHolder evh = new myOwnHolder(myView, mListener, LListener);
        return evh;
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

        public myOwnHolder(View itemView, final OnItemClickListener listener, final OnItemLongClickListener listener2) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.text1);
            t2 = (TextView)itemView.findViewById(R.id.text2);
            taskState = (CheckBox) itemView.findViewById(R.id.checkBox);

            mainLayout = (RelativeLayout)itemView.findViewById(R.id.mainLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener2 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener2.onItemLongClicked(position);
                        }
                        }
                    return false;
                }
            });
        }
    }

}

