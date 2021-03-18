package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class newDiary extends AppCompatActivity {
    EditText diaryTitle,diaryNote,diaryDate;
    DiarySQL sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);
        diaryTitle=findViewById(R.id.diaryTitle);
        diaryDate=findViewById(R.id.diaryDate);
        diaryNote=findViewById(R.id.diaryNote);
        sql=new DiarySQL(newDiary.this);
    }

    public void saveDiary(View view)
    {
        sql.addNote(diaryTitle.getText().toString(),diaryDate.getText().toString(),diaryNote.getText().toString());
        Intent intent=new Intent(newDiary.this,Diary.class);
        finish();
        startActivity(intent);
    }

}