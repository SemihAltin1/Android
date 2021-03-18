package com.example.menuanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private AnimatorSet front_anim;
    public MyAdapter(Context c)
    {
        this.context=c;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView=layoutInflater.inflate(R.layout.new_list,null);
        }
        float scale= context.getApplicationContext().getResources().getDisplayMetrics().density;
        front_anim= (AnimatorSet) AnimatorInflater.loadAnimator(context.getApplicationContext(),R.animator.flip_animation);
        ImageView imageView=convertView.findViewById(R.id.image_view);
        imageView.setCameraDistance(8000*scale);
        front_anim.setTarget(imageView);
        front_anim.start();
        imageView.setImageResource(R.drawable.animals);
        return convertView;
    }
}
