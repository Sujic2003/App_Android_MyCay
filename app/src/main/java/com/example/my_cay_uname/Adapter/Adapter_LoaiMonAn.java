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

public class Adapter_LoaiMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> list_Loai;

    ViewHolderLoai viewHolderLoai;

    public Adapter_LoaiMonAn(Context context, int layout, List<LoaiMonAnDTO> list_Loai) {
        this.context = context;
        this.layout = layout;
        this.list_Loai = list_Loai;
    }

    @Override
    public int getCount() {
        return list_Loai.size();
    }

    @Override
    public Object getItem(int position) {
        return list_Loai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_Loai.get(position).getMALOAI();
    }

    public class ViewHolderLoai {
        TextView txtTenLoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        if(view == null) {
            viewHolderLoai = new ViewHolderLoai();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // set layout tên loại
            view = inflater.inflate(R.layout.spiner_loaithucdon, parent, false);
            viewHolderLoai.txtTenLoai = (TextView) view.findViewById(R.id.spinTenLoai);

            // Lưu
            view.setTag(viewHolderLoai);
        } else {
            viewHolderLoai = (ViewHolderLoai) view.getTag();
        }
        LoaiMonAnDTO loai = list_Loai.get(position);
        viewHolderLoai.txtTenLoai.setText(loai.getMALOAI());
        viewHolderLoai.txtTenLoai.setText(loai.getTENLOAI());
        return view;
    }
}
