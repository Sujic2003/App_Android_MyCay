package com.example.my_cay_uname;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    int image1[] = {R.drawable.kiemchiga,R.drawable.kimchibachtuot, R.drawable.kimchibo,R.drawable.kimchicavien,R.drawable.kimchihaisan};
    String name1[]={"Kim chi gà","Kim bạch tuột","Kim chi bò","Kim chi cá viên","Kim chi hải sản",};
    int price1[]={55000,55000,55000,55000,55000};
    int image2[] = {R.drawable.soda_dao,R.drawable.sodablue, R.drawable.sodadau,R.drawable.trasuatruyenthong,R.drawable.tratraicay};
    String name2[]={"Soda đào","Soda Blue","Soda dâu","Trà sửa truyền thống","Trà trái cây",};
    int price2[]={35000,35000,35000,45000,55000};
    String type[]={"Mỳ cay",  "Món ăn thêm","Nước uống"};
    GridView gv_mycay, gv_nuocuong, gv_monanthem;
    ListView ls_loai;
    TextView hienthi;
    ArrayList<MonAn> monan = new ArrayList<>();
    ArrayList<MonAn> monanthem = new ArrayList<>();
    ArrayList<MonAn> nuocuong = new ArrayList<>();
    Adapter_MonAn myadapter = null;
    ArrayAdapter<String> adapter_loai;

    int soluong =0;
    int tongtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        hienthi = (TextView) findViewById(R.id.HienThi);
        //Khai báo ánh xạ toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if(intent!=null)
        {
            String selectedTable = intent.getStringExtra("selectedTable");
            if(selectedTable!=null)
            {
                getSupportActionBar().setTitle(selectedTable);
            }
        }
        //Khai báo ánh xạ hiển thị loại món
        ls_loai =(ListView) findViewById(R.id.listview_loai);
        adapter_loai = new ArrayAdapter<>(Menu.this, android.R.layout.simple_list_item_1, type);
        ls_loai.setAdapter(adapter_loai);
        //Khai báo ánh xạ hiêển thị menu
        //Mỳ cay
        for(int i =0 ; i<name1.length;i++)
        {
            monan.add(new MonAn(name1[i],image1[i],price1[i]));
        }
        myadapter = new Adapter_MonAn(Menu.this, R.layout.layout_tt_monan, monan);
        gv_mycay = (GridView) findViewById(R.id.gridvew_Mycay);
        gv_mycay.setAdapter(myadapter);
        //Món ăn thêm
        for(int i =0 ; i<name1.length;i++)
        {
            monanthem.add(new MonAn(name1[i],image1[i],price1[i]));
        }
        myadapter = new Adapter_MonAn(Menu.this, R.layout.layout_tt_monan, monanthem);
        gv_monanthem = (GridView) findViewById(R.id.gridvew_monanthem);
        gv_monanthem.setAdapter(myadapter);
        //Nước uống
        for(int i =0 ; i<name1.length;i++)
        {
            nuocuong.add(new MonAn(name2[i],image2[i],price2[i]));
        }
        myadapter = new Adapter_MonAn(Menu.this, R.layout.layout_tt_monan, nuocuong);
        gv_nuocuong = (GridView) findViewById(R.id.gridvew_nuocuong);
        gv_nuocuong.setAdapter(myadapter);


        ls_loai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    gv_mycay.setVisibility(View.VISIBLE);
                    gv_monanthem.setVisibility(View.GONE);
                    gv_nuocuong.setVisibility(View.GONE);


                }
                if(position==1)
                {
                    gv_monanthem.setVisibility(View.VISIBLE);
                    gv_nuocuong.setVisibility(View.GONE);
                    gv_mycay.setVisibility(View.GONE);


                }
                if(position==2) {
                    gv_nuocuong.setVisibility(View.VISIBLE);
                    gv_monanthem.setVisibility(View.GONE);
                    gv_mycay.setVisibility(View.GONE);

                }
            }
        });
        gv_monanthem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn selectedMonAn = monanthem.get(position);
                soluong++;
                tongtien+= selectedMonAn.getPrice();
                update();
            }
        });
        gv_mycay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn selected = monan.get(position);
                soluong++;
                tongtien+=selected.getPrice();
                update();
            }
        });
        gv_nuocuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn selected = nuocuong.get(position);
                soluong++;
                tongtien += selected.getPrice();
                update();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        //Xử lý sự kiện nút quay lại
        if(id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //cập nật thông tin
    private void update()
    {
        hienthi.setText(soluong +"  |   "+ tongtien +" VND");
    }
    //Hàm lấy giá từ chuỗi *(Nếu cần)
    private int getPrice(String gia)
    {
        String giakochuoi = gia.replace(" VND", "").replace(",","");
        return Integer.parseInt(giakochuoi);
    }

}