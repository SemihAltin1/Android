package com.example.menuanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        MyAdapter adapter=new MyAdapter(MainActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}