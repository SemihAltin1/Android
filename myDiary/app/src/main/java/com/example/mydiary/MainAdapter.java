package com.example.mydiary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
   private Context context;
   private LayoutInflater layoutInflater;
   private String[] titleArray,dateArray;
   public MainAdapter(Context c,String[] array,String[] array2)
   {
       this.context=c;
       this.titleArray=array;
       this.dateArray=array2;
   }

    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.note_list,null);
        }
        TextView textView=convertView.findViewById(R.id.list_note_id);
        if(position%2==0)
            textView.setBackgroundColor(Color.parseColor("#FFFF00"));
        else
            textView.setBackgroundColor(Color.parseColor("#FFA500"));

        textView.setText(titleArray[position]+" - "+dateArray[position]);
        return convertView;
    }
}
