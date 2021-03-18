package com.example.mydiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class UserSQL {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private String name,password,hint;

    public UserSQL(Context c)
    {
        this.context=c;
        sqLiteDatabase=context.openOrCreateDatabase("Diary",Context.MODE_PRIVATE,null);

    }
    public void addUser(String name,String password,String hint)
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,hint VARCHAR)");
        String sqlString="INSERT INTO user(name,password,hint) VALUES (?,?,?)";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sqlString);
        sqLiteStatement.bindString(1,name);
        sqLiteStatement.bindString(2,password);
        sqLiteStatement.bindString(3,hint);
        sqLiteStatement.execute();
    }

    public void UpdateUser(String newUsername,String newPassword,String newHint)
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,hint VARCHAR)");
        String sqlString="UPDATE user SET name=?,password=?,hint=? WHERE name=?";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sqlString);
        sqLiteStatement.bindString(1,newUsername);
        sqLiteStatement.bindString(2,newPassword);
        sqLiteStatement.bindString(3,newHint);
        sqLiteStatement.bindString(4,getUsername());
        sqLiteStatement.execute();
    }

    public void getSQL()
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR,hint VARCHAR)");
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM user",null);
        int nameIx=cursor.getColumnIndex("name");
        int passwordIx=cursor.getColumnIndex("password");
        int hintIx=cursor.getColumnIndex("hint");
        while (cursor.moveToNext())
        {
            this.name=cursor.getString(nameIx);
            this.password=cursor.getString(passwordIx);
            this.hint=cursor.getString(hintIx);
        }
        cursor.close();
    }

    public String getUsername()
    {
        return name;
    }
    public String getPassword()
    {
        return password;
    }
    public String getHint()
    {
        return hint;
    }


}
