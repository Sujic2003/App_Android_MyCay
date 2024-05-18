package com.example.my_cay_uname;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.MyViewHolder> {
    Context context;
    List<GioHang> gioHangList;
    private MonAn monan;

    public Adapter_GioHang(List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gio_hang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        GioHang gioHang = gioHangList.get(position);
//        holder.item_giohang_tensp.setText(gioHang.getTensp());
//        holder.item_giohang_soluong.setText(gioHang.getSoluong());
//        Glide.with(context).load(gioHang.getSoluong()).into(holder.item_giohang_image);
//        DecimalFormat decimalFormat = new DecimalFormat("###, ###, ###");
//        holder.item_giohang_gia.setText(decimalFormat.format((gioHang.getGiasp()))+ "Đ");
//        long gia = gioHang.getSoluong() * gioHang.getGiasp();
//        holder.item_giohang_gia.setText();

    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_giohang_image;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
        }
    }

}
