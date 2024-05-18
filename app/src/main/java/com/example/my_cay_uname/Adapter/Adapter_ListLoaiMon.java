package com.example.my_cay_uname.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_cay_uname.R;

import java.util.List;

import DTO.LoaiMonAnDTO;

public class Adapter_ListLoaiMon extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> list;

    public Adapter_ListLoaiMon(Context context, int layout, List<LoaiMonAnDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMALOAI();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tạo layout
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_item_loaimonan, parent, false);
        // Tạo biến lưu giá trị dữ liệu
        TextView tenLoai = (TextView) view.findViewById(R.id.txt_tenLoaiMon);
        tenLoai.setText(list.get(position).getTENLOAI());

        return view;
    }
}
