package com.example.mydiary;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DiarySQL {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private ArrayList<String> titleList,dateList,noteList;
    private ArrayList<Integer>idList;
    private String[] titleArray,dateArray,noteArray;
    private int[] idArray;

    public DiarySQL(Context c)
    {
        this.context=c;
        sqLiteDatabase=context.openOrCreateDatabase("Diary",Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY,title VARCHAR,note VARCHAR,date VARCHAR)");
    }


    public void addNote(String title,String date,String note)
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY,title VARCHAR,date VARCHAR,note VARCHAR)");
        String sqlString="INSERT INTO notes(title,date,note) VALUES (?,?,?)";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sqlString);
        sqLiteStatement.bindString(1,title);
        sqLiteStatement.bindString(2,date);
        sqLiteStatement.bindString(3,note);
        sqLiteStatement.execute();
    }

    public void getSql()
    {
        titleList=new ArrayList<>();
        dateList=new ArrayList<>();
        idList=new ArrayList<Integer>();
        noteList=new ArrayList<>();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM notes",null);
        int titleIx=cursor.getColumnIndex("title");
        int dateIx=cursor.getColumnIndex("date");
        int idIx=cursor.getColumnIndex("id");
        int noteIx=cursor.getColumnIndex("note");
        while (cursor.moveToNext())
        {
            this.idList.add(cursor.getInt(idIx));
            this.titleList.add(cursor.getString(titleIx));
            this.dateList.add(cursor.getString(dateIx));
            this.noteList.add(cursor.getString(noteIx));
        }
        cursor.close();
    }

    public int Count()
    {
        getSql();
        return this.titleList.size();
    }
    public String[] getTitle()
    {
        titleArray=new String[titleList.size()];
        for(int i=0;i<titleList.size();i++)
            titleArray[i]=titleList.get(i);
        return this.titleArray;
    }

    public String[] getDate()
    {
        dateArray=new String[dateList.size()];
        for(int i=0;i<dateList.size();i++)
            dateArray[i]=dateList.get(i);
        return this.dateArray;
    }
    public String[] getNote()
    {
        noteArray=new String[noteList.size()];
        for(int i=0;i<noteList.size();i++)
            noteArray[i]=noteList.get(i);
        return this.noteArray;
    }

    public int[] getIdArray()
    {
        idArray=new int[idList.size()];
        for(int i=0;i<idList.size();i++)
            idArray[i]=idList.get(i);
        return this.idArray;
    }
    public void deleteWithId(int id)
    {
        String deleteSql="DELETE FROM notes WHERE id=?";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(deleteSql);
        sqLiteStatement.bindDouble(1,id);
        sqLiteStatement.execute();
    }
    public void deleteAll()
    {
        sqLiteDatabase.execSQL("DELETE FROM notes");
    }
}
