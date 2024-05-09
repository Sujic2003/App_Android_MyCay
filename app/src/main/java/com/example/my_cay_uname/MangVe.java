package com.example.my_cay_uname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MangVe extends AppCompatActivity {
    String table[]= {"MV01","MV02","MV03","MV04","MV05","MV06","MV07","MV08","MV09","MV10",};
    ArrayList<Table> list;
    MyArrayAdapter myadapter;
    GridView Grid_table;
    Button btnHD, btnT1, btnT2, btnMV;
    boolean isColorChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangve);
        Grid_table = (GridView) findViewById(R.id.gridView_table);
        list = new ArrayList<>();//Tạo mới mảng
        for(int i = 0; i< table.length; i++)
        {
            list.add(new Table(table[i]));
        }
        myadapter = new MyArrayAdapter(MangVe.this,R.layout.activity_table, list);
        Grid_table.setAdapter(myadapter);
        btnHD = (Button) findViewById(R.id.btn_HoaDon);
        btnT1 = (Button) findViewById(R.id.btn_T1);
        btnT2 = (Button) findViewById(R.id.btn_T2);
        btnMV = (Button) findViewById(R.id.btn_MV);


        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(getApplication(), MainActivity.class);
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
                    String selectedTable = table[position];
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