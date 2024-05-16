package com.example.my_cay_uname;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        TextView txtMaBan, txtTenBan;
        ImageView imBan, im_TinhTrang;
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
            viewHolderBan.txtTenBan = (TextView) view.findViewById(R.id.txtTenBan);
            viewHolderBan.imBan = (ImageView) view.findViewById(R.id.imBan);
            viewHolderBan.im_TinhTrang = (ImageView) view.findViewById(R.id.im_icon_TinhTrang);
            // Lưu giá trị
            view.setTag(viewHolderBan);
        } else {
            // Gán giá trị
            viewHolderBan = (ViewHolderBan) view.getTag();
        }
        // Lấy từng giá trị bàn
        BanDTO banDTO = banDTOList.get(position);
        viewHolderBan.txtTenBan.setText(banDTO.getTENBAN());

        // Kiểm tra tình trạng của bàn để hiển thị hoặc ẩn ảnh img_TinhTrang
        if (banDTO.getTINHTRANG().equals("Đầy")) {
            viewHolderBan.im_TinhTrang.setVisibility(View.VISIBLE);
        } else {
            viewHolderBan.im_TinhTrang.setVisibility(View.INVISIBLE);
        }
        return view;
    }

}
