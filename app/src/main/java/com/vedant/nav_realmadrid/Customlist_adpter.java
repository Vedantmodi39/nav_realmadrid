package com.vedant.nav_realmadrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class Customlist_adpter  extends BaseAdapter {
    Context context;
    //  String[] s1;
    /*ArrayList<String> a1=new ArrayList<>();
    ArrayList<String> a2=new ArrayList<>();
    ArrayList<String> a3=new ArrayList<>();*/
    ArrayList<String> a1;
    ArrayList<String> a2;
    ArrayList<String> a3;

    public Customlist_adpter(Context context, ArrayList<String> a1, ArrayList<String> a2, ArrayList<String> a3)
    {
        this.context=context;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
    }


    @Override
    public int getCount() {
        return a1.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listt_item, parent, false);


            viewHolder.txtname=convertView.findViewById(R.id.textView);
            //  viewHolder.txtid=convertView.findViewById(R.id.textView2);
            viewHolder.txtimg=convertView.findViewById(R.id.imageView);
            //     viewHolder.txtname2=convertView.findViewById(R.id.textView2);

            result=convertView;
            convertView.setTag(viewHolder);


        }
        else {

            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        // viewHolder.txtname.setText(s1);
        viewHolder.txtname.setText( a2.get(position));
        //   viewHolder.txtid.setText(a2.get(position));
        //viewHolder.txtimg.setText(a3.get(position));




        Glide.with(context)
                .load(a3.get(position))//jemathi levani

                .into(viewHolder.txtimg);//jema show karvani
        //viewHolder.txtname2.setText(a2.get(position));


        return convertView;
    }
}


class ViewHolder{

    TextView txtname;
    // TextView txtid;
    ImageView txtimg;


}


