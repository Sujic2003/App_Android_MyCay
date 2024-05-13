package com.example.my_cay_uname;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import DAO.BanDAO;
import DTO.BanDTO;

public class MangVe extends AppCompatActivity {
    Button btnHD, btnT1, btnT2, btnMV;
    Adapter_Table myadapter;
    GridView Grid_table;
    BanDAO banDAO ;
    List<BanDTO> tables;

    boolean isColorChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangve);
        Grid_table = (GridView) findViewById(R.id.gridView_table);
        // Khởi tạo
        banDAO = new BanDAO(this);
        // Lấy danh sách bàn
        tables = banDAO.getAll_Table();
        myadapter = new Adapter_Table(MangVe.this,R.layout.layout_item_table, tables);
        Grid_table.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();


        btnHD = (Button) findViewById(R.id.btn_HoaDon);
        btnT1 = (Button) findViewById(R.id.btn_T1);
        btnT2 = (Button) findViewById(R.id.btn_T2);
        btnMV = (Button) findViewById(R.id.btn_MV);


        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(getApplication(), HoaDon.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
        btnT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
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

                try {
                    Intent intent = new Intent(getApplication(), Tang2.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
        Grid_table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    String selectedTable = String.valueOf(tables.get(position));
                    Intent intent = new Intent(getApplication(), Menu.class);
                    intent.putExtra("selectedTable", selectedTable);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}