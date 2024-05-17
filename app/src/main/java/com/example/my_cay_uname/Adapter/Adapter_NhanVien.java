package com.example.my_cay_uname.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.my_cay_uname.R;
import java.util.List;
import DTO.NhanVienDTO;


public class Adapter_NhanVien extends BaseAdapter {
    Context context;
    int layout;
    List<NhanVienDTO> list;

    public Adapter_NhanVien(Context context, int layout, List<NhanVienDTO> list) {
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
        return list.get(position).getMANV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_listview_nhanvien, null);

        TextView txtid = (TextView) row.findViewById(R.id.id);
        TextView ten = (TextView) row.findViewById(R.id.name);
        TextView gioitinh = (TextView) row.findViewById(R.id.sex);
        TextView ngaysinh = (TextView) row.findViewById(R.id.Date);
        txtid.setText(String.valueOf(list.get(position).getMANV()));
        ten.setText(list.get(position).getTENNV());
        ngaysinh.setText(list.get(position).getNGAYSINH());
        gioitinh.setText(list.get(position).getGIOITINH());

        return row;
    }
}
