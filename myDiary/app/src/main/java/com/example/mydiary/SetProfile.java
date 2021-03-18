package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetProfile extends AppCompatActivity {
    EditText newName,newPassword,newHint;
    UserSQL sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        newName=findViewById(R.id.newUserName);
        newPassword=findViewById(R.id.newPassword);
        newHint=findViewById(R.id.newHint);
        sql=new UserSQL(SetProfile.this);
        sql.getSQL();
    }
    public void updateClick(View view)
    {
        update();
    }
    public void update()
    {
        if(!newName.getText().toString().matches("") && !newPassword.getText().toString().matches("") && !newHint.getText().toString().matches(""))
        {
            sql.UpdateUser(newName.getText().toString(),newPassword.getText().toString(),newHint.getText().toString());
            Toast.makeText(this,"SAVED",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SetProfile.this,Diary.class);
            finish();
            startActivity(intent);
        }
        else{
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage("can't be empty");
            alert.show();
        }
    }

}