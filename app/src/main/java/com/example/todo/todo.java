package com.example.todo;

import android.widget.CheckBox;
import android.widget.Toast;

public class todo {
    private String todo;
    private String description;
    public static boolean isChecked = false;
    public todo(String todo, String description){
        this.todo = todo;
        this.description = description;
    }

    public String getTodo(){
        return todo;
    }
    public String getdescription(){
        return description;
    }
    public boolean isDone(){
        return isChecked;
    }

    public static void setDone(Boolean checked){
        isChecked = checked;
    }
}
