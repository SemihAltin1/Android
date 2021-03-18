package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText nameText,passwordText;
    SQLiteDatabase sqLiteDatabase;
    UserSQL sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameText=findViewById(R.id.nameLogin);
        passwordText=findViewById(R.id.passwordLogin);
        sqLiteDatabase=this.openOrCreateDatabase("Diary",MODE_PRIVATE,null);
        nameText.setText("");
        passwordText.setText("");
        sql=new UserSQL(Login.this);
        sql.getSQL();
    }


    public void loginClick(View view)
    {
        if(!nameText.getText().toString().matches(sql.getUsername()) || !passwordText.getText().toString().matches(sql.getPassword()))
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("warning");
            alert.setMessage("invalid username or password");
            alert.show();
        }
        else
        {
            Intent intent=new Intent(Login.this,Diary.class);
            finish();
            startActivity(intent);
        }
    }

    public void showHint(View view)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Hint");
        alert.setMessage(sql.getHint());
        alert.show();
    }


}