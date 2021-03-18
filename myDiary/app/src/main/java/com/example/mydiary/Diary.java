package com.example.mydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Diary extends AppCompatActivity {
    ListView listView;
    String[] titleArray;
    String[] dateArray;
    int[] idArray;
    DiarySQL sql;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        listView=findViewById(R.id.listView);

        sql=new DiarySQL(Diary.this);

        titleArray=new String[sql.Count()];
        dateArray=new String[sql.Count()];
        idArray= new int[sql.Count()];

        titleArray=sql.getTitle();
        dateArray=sql.getDate();
        idArray=sql.getIdArray();

        mainAdapter=new MainAdapter(Diary.this,titleArray,dateArray);
        listView.setAdapter(mainAdapter);

        shortClick();
    }

    public void newNote(View view)
    {
        Intent intent=new Intent(Diary.this, newDiary.class);
        startActivity(intent);
    }

    public void shortClick()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Diary.this,ShowDiary.class);
                intent.putExtra("diaryId",idArray[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(Diary.this);
        menuInflater.inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.profile_id)
        {
            Intent intent=new Intent(Diary.this,SetProfile.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.delete_id)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage("Delete All");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sql.deleteAll();
                    mainAdapter.notifyDataSetChanged();
                    Intent intent=new Intent(Diary.this,Diary.class);
                    finish();
                    startActivity(intent);

                }
            });
            alert.setNegativeButton("Cancel",null);
            alert.show();

        }

        return super.onOptionsItemSelected(item);
    }
}