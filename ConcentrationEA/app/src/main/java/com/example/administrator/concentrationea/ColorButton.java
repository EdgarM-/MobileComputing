package com.example.administrator.concentrationea;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Administrator on 3/9/2016.
 */
public class ColorButton extends Button {
    private String color;
    public ColorButton(Context context) {
        super(context);
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return this.color;
    }
}
