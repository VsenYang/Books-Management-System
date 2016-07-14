package com.example.visen.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by www40 on 2016/6/10.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    private int resourceId;

    public BookAdapter(Context context,int textViewResourceId,List<Book> object){
        super(context,textViewResourceId,object);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Book book = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.bookName = (TextView)view.findViewById(R.id.bookNameList);
            viewHolder.author = (TextView)view.findViewById(R.id.authorList);
            viewHolder.pages = (TextView)view.findViewById(R.id.pagesList);
            viewHolder.price = (TextView)view.findViewById(R.id.priceList);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.bookName.setText(book.getBookName());
        viewHolder.author.setText(book.getAuthor());
        viewHolder.pages.setText(book.getPages());
        viewHolder.price.setText(book.getPrice());

        return view;
    }

    class ViewHolder{
        TextView bookName;
        TextView author;
        TextView pages;
        TextView price;
    }
}
