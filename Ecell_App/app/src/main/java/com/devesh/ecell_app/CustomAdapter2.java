package com.devesh.ecell_app;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CustomAdapter2 extends BaseAdapter{

    String [] result;
    Context context;
    String [] imageId;
    String[] Designation;
    private static LayoutInflater inflater=null;
    public CustomAdapter2(Context context, String[] prgmNameList, String[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        imageId=prgmImages;
        this.Designation=Designation;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context=context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public class Holder
    {
        TextView tv;
        SquareImageView img;
        TextView tv2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated met  hod stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.table_view, null);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) rowView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //holder.tv2=(TextView)rowView.findViewById(R.id.textView2);
        holder.tv=(TextView) rowView.findViewById(R.id.text);
        holder.img=(SquareImageView) rowView.findViewById(R.id.picture);
        holder.tv.setText(result[position]);
        //holder.tv2.setText(Designation[position]);
        //holder.img.setMaxHeight(height / 2);
        //holder.img.setMaxWidth(width / 2);
        //holder.img.setMinimumWidth(width / 2);
        //holder.img.setMinimumHeight(height/2);
        Picasso.with(context)
                .load(imageId[position])
                .error(R.drawable.ic_launcher)
                .into(holder.img);
        //holder.img.setImageResource(imageId[p osition]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }

}