package com.example.catchball;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.logging.LogRecord;

public class ChangeImage {
    private ImageView[] imageArray;
    private Context context;
    private int speed;
    Handler handler;
    Runnable runnable;



    public ChangeImage(ImageView[] newImageArray,Context newContext,int newSpeed)
    {
        this.imageArray=newImageArray;
        this.context=newContext;
        this.speed=newSpeed;
    }

    public void Run()
    {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView:imageArray)
                {
                    imageView.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,speed);
            }
        };
        handler.post(runnable);
    }

    public void Stop()
    {
        handler.removeCallbacks(runnable);
        AlertDialog.Builder alert=new AlertDialog.Builder(context);
        alert.setMessage("GAME OVER");
        alert.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(context,MainActivity.class);
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });
        alert.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(context,MainMenu.class);
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });
        alert.show();
    }

}
