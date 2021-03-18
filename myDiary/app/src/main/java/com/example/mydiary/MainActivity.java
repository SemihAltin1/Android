package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nameText,passwordText,hintText;
    UserSQL sql;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText=findViewById(R.id.nameText);
        passwordText=findViewById(R.id.passwordText);
        hintText=findViewById(R.id.hintText);
        sql=new UserSQL(MainActivity.this);
        sharedPreferences=this.getSharedPreferences("com.example.mydiary",MODE_PRIVATE);
        int check=sharedPreferences.getInt("state",0);
        if(check==1)
        {
            Intent intent=new Intent(MainActivity.this, Login.class);
            finish();
            startActivity(intent);
        }

    }

    public void saveClick(View view)
    {
        if(nameText.getText().toString().matches("")  || passwordText.getText().toString().matches("") || hintText.getText().toString().matches(""))
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("warning");
            alert.setMessage("can't be empty");
            alert.show();
        }
        else
        {
            sql.addUser(nameText.getText().toString(),passwordText.getText().toString(),hintText.getText().toString());
            sharedPreferences.edit().putInt("state",1).apply();
            Intent intent=new Intent(MainActivity.this, Diary.class);
            finish();
            startActivity(intent);
        }
    }

}