package com.example.my_cay_uname;

import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapter_NhanVien extends BaseAdapter {
    Context context;
    ArrayList<NhanVien> list;

    public Adapter_NhanVien(Context context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_listview_nhanvien, null);

        TextView txtid = (TextView) row.findViewById(R.id.id);
        TextView ten = (TextView) row.findViewById(R.id.name);
        TextView gioitinh = (TextView) row.findViewById(R.id.sex);
        TextView ngaysinh = (TextView) row.findViewById(R.id.Date);
        Button btnxoa = (Button) row.findViewById(R.id.delete);
        Button btnSua = (Button) row.findViewById(R.id.edit);

        NhanVien nhanVien = list.get(position);
        txtid.setText(nhanVien.id + "");
        ten.setText(nhanVien.ten);
        ngaysinh.setText(nhanVien.ngaysinh);
        gioitinh.setText(nhanVien.gioitinh);
        return row;
    }
}
