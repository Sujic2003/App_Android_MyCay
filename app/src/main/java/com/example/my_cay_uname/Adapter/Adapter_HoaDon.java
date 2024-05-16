package com.example.my_cay_uname.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_cay_uname.R;

import java.util.List;

import DTO.HoaDonDTO;

public class Adapter_HoaDon extends BaseAdapter {
    List<HoaDonDTO> hoaDonDTOList;
    int layout;
    Context context;
    ViewHolderHoaDon viewHolder;

    public Adapter_HoaDon(Context context, int layout, List<HoaDonDTO> hoaDonDTOList) {
        this.context = context;
        this.layout = layout;
        this.hoaDonDTOList = hoaDonDTOList;
    }

    @Override
    // Đếm số lượng hoá đơn
    public int getCount() {
        return hoaDonDTOList == null ? 0 : hoaDonDTOList.size();
    }

    @Override
    // Trả về vị trí hoá đơn
    public Object getItem(int position) {

        return hoaDonDTOList.get(position);
    }

    @Override
    // Trả về id là mã hoá đơn
    public long getItemId(int position) {
        return hoaDonDTOList.get(position).getMAHOADON();
    }

    public class ViewHolderHoaDon{
        TextView txtMa, txtTien, txtNgay;
    }
    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            // Nếu view đang hiên thị bằng null thì khởi tạo
            if(view==null) {
                // Tạo layout chứa
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                viewHolder = new ViewHolderHoaDon();
                // Có layout custom
                view = inflater.inflate(R.layout.layout_item_hoadon,parent, false);
                viewHolder.txtMa = (TextView) view.findViewById(R.id.txtmahoadon);
                viewHolder.txtTien = (TextView) view.findViewById(R.id.txttongtien);
                viewHolder.txtNgay = (TextView) view.findViewById(R.id.txtngaytao);
                //Lưu giá trị
                view.setTag(viewHolder);
        } else {
            //Gán giá trị
            viewHolder =(ViewHolderHoaDon) view.getTag();
        }
        // Lấy vị trí của hoá đơn từng cái
        HoaDonDTO hoadon = hoaDonDTOList.get(position);
        viewHolder.txtMa.setText("HOÁ ĐƠN " + String.valueOf(hoadon.getMAHOADON()));
        viewHolder.txtTien.setText("TỔNG: " + String.valueOf(hoadon.getTONGTIEN()) + " Đ");
        viewHolder.txtNgay.setText("NGÀY: "+ hoadon.getNGAYTAO());
        return  view;
    }
}
