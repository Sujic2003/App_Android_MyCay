package com.example.my_cay_uname;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_cay_uname.Adapter.Adapter_LoaiMonAn;
import com.example.my_cay_uname.fragment.Fragment_MonAn;

import java.util.List;

import DAO.LoaiMonAnDAO;
import DAO.MonAnDAO;
import DTO.LoaiMonAnDTO;

public class ThemMonAn extends AppCompatActivity {
    public static int Request_code_themloaitd = 112;
    public static int Request_code_themhinh = 113;
    String DuongDanHinh;
    EditText editTen, editGia;
    Button btnThem, btnThoat;
    ImageView imgMonAn;
    ImageButton imgbtnThemLoai;
    Spinner spnLoai;
    Fragment_MonAn fragmentMonAn;
    MonAnDAO monAnDAO;
    LoaiMonAnDAO loaiMonAnDAO;
    // Danh sach loai mon an
    List<LoaiMonAnDTO> list_loaimon;
    Adapter_LoaiMonAn adtLoaiMon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themmonan);
        imgMonAn = (ImageView) findViewById(R.id.anhMA);
        editTen = (EditText) findViewById(R.id.TenMA);
        editGia = (EditText) findViewById(R.id.GiaTienMA);
        spnLoai = (Spinner) findViewById(R.id.spnLMA);
        imgbtnThemLoai = (ImageButton) findViewById(R.id.imgBtnThemLoai);
        btnThem = (Button) findViewById(R.id.btnThemMA);
        btnThoat = (Button) findViewById(R.id.btnThoatMA);

        //Hiển thị loại món ăn
        HienThiLoaiMonAn();
        monAnDAO = new MonAnDAO(this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void HienThiLoaiMonAn() {
        list_loaimon = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adtLoaiMon = new Adapter_LoaiMonAn(ThemMonAn.this, R.layout.spiner_loaithucdon, list_loaimon);
        spnLoai.setAdapter(adtLoaiMon);
        adtLoaiMon.notifyDataSetChanged();

    }


}
