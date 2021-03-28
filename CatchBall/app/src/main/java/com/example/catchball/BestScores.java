package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BestScores extends AppCompatActivity {
    TextView easyText,normalText,hardText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);
        easyText=findViewById(R.id.easyText);
        normalText=findViewById(R.id.normalText);
        hardText=findViewById(R.id.hardText);
        SQLClass sqlClass=new SQLClass(BestScores.this);
        easyText.setText(sqlClass.getSQL("easy"));
        normalText.setText(sqlClass.getSQL("normal"));
        hardText.setText(sqlClass.getSQL("hard"));
    }
}