package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    TextView scoreText,timeText;
    ImageView[] imageArray;
    int Score=0,speed,newScore;
    String dif;
    SQLClass sqlClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);

        Intent intent=getIntent();
        speed=intent.getIntExtra("speed",1000);
        dif=intent.getStringExtra("dif");

        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        ChangeImage changeImage=new ChangeImage(imageArray,MainActivity.this,speed);
        changeImage.Run();
        scoreText.setText("SCORE : "+Score);
        sqlClass=new SQLClass(MainActivity.this);

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("TIME : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                changeImage.Stop();
                SaveScore();
            }
        }.start();
    }

    public void score(View view)
    {
         Score++;
         scoreText.setText("SCORE : "+Score);
    }

    public void SaveScore()
    {
        if(dif.matches("easy") && Score>Integer.parseInt(sqlClass.getSQL("easy")) )
        {
            sqlClass.updateScore(dif,String.valueOf(Score));
        }
        if (dif.matches("normal") && Score>Integer.parseInt(sqlClass.getSQL("normal")))
        {
            sqlClass.updateScore(dif,String.valueOf(Score));
        }
        if(dif.matches("hard") && Score>Integer.parseInt(sqlClass.getSQL("hard")))
        {
            sqlClass.updateScore(dif,String.valueOf(Score));
        }
    }

}