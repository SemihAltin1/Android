package com.example.mydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDiary extends AppCompatActivity {
    TextView title,date,note;
    DiarySQL sql;
    int id;
    int[] idArray;
    String[] titleArray,dateArray,noteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);
        title=findViewById(R.id.titleTextView);
        date=findViewById(R.id.dateTextView);
        note=findViewById(R.id.noteTextView);

        Intent intent=getIntent();
        id=intent.getIntExtra("diaryId",0);

        sql=new DiarySQL(ShowDiary.this);
        dateArray=new String[sql.Count()];
        titleArray=new String[sql.Count()];
        noteArray=new String[sql.Count()];
        idArray=new int[sql.Count()];

        dateArray=sql.getDate();
        titleArray=sql.getTitle();
        idArray=sql.getIdArray();
        noteArray=sql.getNote();

        title.setText(titleArray[id-1]);
        date.setText(dateArray[id-1]);
        note.setText(noteArray[id-1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(ShowDiary.this);
        menuInflater.inflate(R.menu.diary_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_id)
        {
            sql.deleteWithId(id);
            Toast.makeText(this,"DELETED",Toast.LENGTH_LONG);
            Intent intent=new Intent(ShowDiary.this,Diary.class);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}