package com.example.my_cay_uname.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_cay_uname.R;

import java.util.List;

import DAO.LoaiMonAnDAO;
import DTO.MonAnDTO;

public class Adapter_Fragment_MonAn extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO> listMonAnDTO;

    public Adapter_Fragment_MonAn(Context context, int layout, List<MonAnDTO> listMonAnDTO) {
        this.context = context;
        this.layout = layout;
        this.listMonAnDTO = listMonAnDTO;
    }


    @Override
    public int getCount() {
        return listMonAnDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return listMonAnDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMonAnDTO.get(position).getMALOAI();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Đổ layout món ăn lên listview
        view = inflater.inflate(R.layout.layout_item_monan, parent, false);

        // Lấy id của các text
        TextView tenmon = (TextView) view.findViewById(R.id.item_txtTenMonAn);
        TextView giatien = (TextView) view.findViewById(R.id.item_txtGiaTen);

        // Đặt giá trị cho các text
        tenmon.setText(listMonAnDTO.get(position).getTENMON());
        giatien.setText("Đơn giá: " + listMonAnDTO.get(position).getGIATIEN());
        return view;
    }

    // Lấy tên loại từ mã loai
    public String LayTenLoai(int maloai) {
        LoaiMonAnDAO loaiMonAnDAO = new LoaiMonAnDAO(context);
        String tenloai = loaiMonAnDAO.LayTenUngVoiMaLoai(maloai);
        if (tenloai != null) {
            return tenloai;
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
