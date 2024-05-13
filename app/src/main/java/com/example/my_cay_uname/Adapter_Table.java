package com.example.my_cay_uname;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import DTO.BanDTO;

public class Adapter_Table extends BaseAdapter {
    List<BanDTO> banDTOList ;
    int  intLayout;
    Context context;
    ViewHolderBan viewHolderBan;

    public Adapter_Table(Context context, int intLayout, List<BanDTO> banDTOList) {
        this.banDTOList = banDTOList;
        this.intLayout = intLayout;
        this.context = context;
    }

    @Override
    // Điểm số lượng bàn
    public int getCount() {
        return banDTOList == null ? 0 : banDTOList.size();
    }

    @Override
    // Trả về vị trí mã bàn
    public Object getItem(int position) {
        return banDTOList.get(position);
    }

    @Override
    // Trả về id mã bàn
    public long getItemId(int position) {
        return banDTOList.get(position).getMABAN();
    }

    public class ViewHolderBan  {
        TextView txtMaBan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Khai báo view đang hiển thị
        View view = convertView;
        // Nếu không có view thì hiển thị
        if(view == null) {
            // Tạo layout chứa
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // Khởi tạo đối tượng
            viewHolderBan = new ViewHolderBan();
            // Lấy và hiển thi layout custom
            view = inflater.inflate(R.layout.layout_item_table, parent, false);
            viewHolderBan.txtMaBan = (TextView) view.findViewById(R.id.txtMaBan);
            // Lưu giá trị
            view.setTag(viewHolderBan);
        } else {
            // Gán giá trị
            viewHolderBan = (ViewHolderBan) view.getTag();
        }
        // Lấy từng giá trị bàn
        BanDTO banDTO = banDTOList.get(position);
        viewHolderBan.txtMaBan.setText("BAN" + String.valueOf(banDTO.getMABAN()));
        return view;
    }
}
