package com.example.my_cay_uname;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDon extends AppCompatActivity {
    Button btnHD, btnT1, btnT2,btnMV;
    GridView gridView_hoadon;
    Adapter_HoaDon adapter;
    HoaDonDAO hoaDonDAO;
    List<HoaDonDTO> bills;

    boolean isColorChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);

        gridView_hoadon = (GridView) findViewById(R.id.gridView_HoaDon);
        hoaDonDAO = new HoaDonDAO(this);
        // Lấy danh sách hoá đơn
        bills = hoaDonDAO.LayTatCaHoaDon();
        adapter = new Adapter_HoaDon(this, R.layout.layout_item_hoadon,bills);
        gridView_hoadon.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        btnT1 = (Button) findViewById(R.id.btn_T1);
        btnT2 = (Button) findViewById(R.id.btn_T2);
        btnMV = (Button) findViewById(R.id.btn_MV) ;
        btnHD = (Button) findViewById(R.id.btn_HoaDon);
        //tạo sự kiện click cho các button
        btnT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), Tang1.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }

            }
        });
        btnT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), Tang2.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
        btnMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), MangVe.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });

        // Button Hoá Đơn
        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // Chuyển sang Hoá Đơn
                    Intent intent = new Intent(getApplication(), this.getClass());
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }

            }
        });
        gridView_hoadon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Không thể thực hiện!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}