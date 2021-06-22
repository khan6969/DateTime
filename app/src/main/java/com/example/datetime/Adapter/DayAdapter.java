package com.example.datetime.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datetime.Models.DateModel;
import com.example.datetime.R;

import java.util.ArrayList;

public class DayAdapter extends ArrayAdapter<DateModel> {
    private ArrayList<DateModel> dList = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);

    }
    public DayAdapter(@NonNull Context context, ArrayList<DateModel> dList) {
        super(context,0,dList);

    }
    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.spinnerlayout,parent,false);
        }
        TextView textView = convertView.findViewById(R.id.textview);
        DateModel List = getItem(position);
        if(List!=null) {
//            textView.setText(List.getDate());
        }
        return convertView;
    }

}
