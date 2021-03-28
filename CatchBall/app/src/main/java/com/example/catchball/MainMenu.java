package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    Intent intent;
    SharedPreferences sharedPreferences;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        intent=new Intent(MainMenu.this,MainActivity.class);
        sharedPreferences=this.getSharedPreferences("com.example.catchball",MODE_PRIVATE);
        state=sharedPreferences.getInt("State",0);
        SQLClass sqlClass=new SQLClass(MainMenu.this);
        if(state==0)
        {
            sqlClass.firstValues();
        }
    }
    public void easyClick(View view)
    {
        sharedPreferences.edit().putInt("State",1).apply();
        intent.putExtra("speed",1500);
        intent.putExtra("dif","easy");
        startActivity(intent);
    }
    public void normalClick(View view)
    {
        sharedPreferences.edit().putInt("State",1).apply();
        intent.putExtra("speed",800);
        intent.putExtra("dif","normal");
        startActivity(intent);
    }
    public void hardClick(View view)
    {
        sharedPreferences.edit().putInt("State",1).apply();
        intent.putExtra("speed",300);
        intent.putExtra("dif","hard");
        startActivity(intent);
    }

    public void showScore(View view)
    {
        Intent intent=new Intent(MainMenu.this,BestScores.class);
        startActivity(intent);
    }
}