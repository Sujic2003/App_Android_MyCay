package com.example.my_cay_uname;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Table> {
    Activity context;
    int idlayout;
    ArrayList<Table> list;

    public MyArrayAdapter(Activity context, int idlayout, ArrayList<Table> list) {
        super(context, idlayout, list);
        this.context = context;
        this.idlayout = idlayout;
        this.list = list;
    }
    //Gọi hàm getView để sấp xếp

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tạo chứa layout
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idlayout, null);
        Table table = list.get(position);
        //Khai báo tham chiếu id  và hiển thị lên TextView
        TextView textView_table = convertView.findViewById(R.id.textView);
        textView_table.setText(table.getTable());
        return convertView;
    }
}
