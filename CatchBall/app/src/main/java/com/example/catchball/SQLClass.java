package com.example.catchball;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class SQLClass {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private String easy,normal,hard;
    public SQLClass(Context newContext)
    {
        this.context=newContext;
        sqLiteDatabase=context.openOrCreateDatabase("Score",Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS scores (easy VARCHAR,normal VARCHAR,hard VARCHAR)");
    }


    public void updateScore(String key,String score)
    {
        if(key.matches("easy"))
        {
            String sql="UPDATE scores SET easy=?";
            SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.bindString(1,score);
            sqLiteStatement.execute();
        }
        if(key.matches("normal"))
        {
            String sql="UPDATE scores SET normal=?";
            SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.bindString(1,score);
            sqLiteStatement.execute();
        }
        if(key.matches("hard"))
        {
            String sql="UPDATE scores SET hard=?";
            SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.bindString(1,score);
            sqLiteStatement.execute();
        }
    }

    public String getSQL(String key)
    {
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM scores",null);
        int easyIx=cursor.getColumnIndex("easy");
        int normalIx=cursor.getColumnIndex("normal");
        int hardIx=cursor.getColumnIndex("hard");
        while (cursor.moveToNext())
        {
            easy=cursor.getString(easyIx);
            normal=cursor.getString(normalIx);
            hard=cursor.getString(hardIx);
        }
        cursor.close();
        
        if(key.matches("easy"))
            return easy;
        if(key.matches("normal"))
            return normal;
        if(key.matches("hard"))
            return hard;
        return "0";
    }

    public void firstValues()
    {
        sqLiteDatabase.execSQL("INSERT INTO scores(easy,normal,hard) VALUES (0,0,0)");
    }

}
