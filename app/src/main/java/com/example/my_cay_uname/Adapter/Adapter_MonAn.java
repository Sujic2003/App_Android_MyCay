package com.example.my_cay_uname.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.my_cay_uname.MonAn;
import com.example.my_cay_uname.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_MonAn extends ArrayAdapter<MonAn> {
    Activity context;
    int Idlayout;
    ArrayList<MonAn> mylist;

    public Adapter_MonAn(Activity context, int idlayout, ArrayList<MonAn> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        Idlayout = idlayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(Idlayout, null);
        MonAn monan = mylist.get(position);
        ImageView img = convertView.findViewById(R.id.imageView);
        img.setImageResource(monan.getImage());
        TextView name = convertView.findViewById(R.id.ten_monan);
        name.setText(monan.getName());
        TextView price = convertView.findViewById(R.id.gia_monan);
        price.setText(String.valueOf(monan.getPrice()));
        return convertView;
    }
}
