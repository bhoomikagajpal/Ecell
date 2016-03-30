package com.devesh.ecell_app;

/**
 * Created by user-PC on 10/3/2015.
 */import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    public String[] prgmNameList;
    public String[] prgmImages;
    public Context context;

    public ImageAdapter(Context context,String[] prgmNameList, String[] prgmImages) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.prgmImages=prgmImages;
        this.prgmNameList=prgmNameList;
    }

    @Override
    public int getCount() {
        return prgmNameList.length;
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.table_view, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        //picture.setImageResource(item.drawableId);
        Picasso.with(context)
                .load(item.drawableId)
                .error(R.drawable.ic_launcher)
                .into(picture);

        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}