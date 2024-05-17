package com.example.my_cay_uname;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_cay_uname.Adapter.Adapter_LoaiMonAn;
import com.example.my_cay_uname.fragment.Fragment_MonAn;

import java.util.List;

import DAO.LoaiMonAnDAO;
import DAO.MonAnDAO;
import DTO.LoaiMonAnDTO;
import DTO.MonAnDTO;

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

        //Khởi tạo
        monAnDAO = new MonAnDAO(this);
        loaiMonAnDAO = new LoaiMonAnDAO(this);

        //Hiển thị loại món ăn
        HienThiLoaiMonAn();


        //Thêm hình ảnh
        imgMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anh = new Intent();
                anh.setType("image/*");//
                anh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(anh, "Chọn ảnh món ăn!!!"), Request_code_themhinh);
            }
        });
        //
        imgbtnThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iloai = new Intent(ThemMonAn.this, ThemLoaiMon_ActivityMonAn.class);
                startActivityForResult(iloai, Request_code_themloaitd);
            }
        });

        // Thêm món ăn vào database
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vitriLoai = spnLoai.getSelectedItemPosition();
                int maloai = list_loaimon.get(vitriLoai).getMALOAI();
                String tenMon = editTen.getText().toString();
                String donGia = editGia.getText().toString();

                //
                if(tenMon != null && donGia !=null && !tenMon.trim().equals("") && !donGia.equals("")) {
                    //Khoi tao
                    MonAnDTO themmon = new MonAnDTO();
                    themmon.setTENMON(tenMon);
                    themmon.setGIATIEN(Integer.parseInt(donGia));
                    themmon.setMALOAI(maloai);
                    themmon.setHINHANH(DuongDanHinh);

                    // Them vao database
                    boolean check = monAnDAO.ThemMonAn(themmon);
                    if (check) {
                        Toast.makeText(ThemMonAn.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(ThemMonAn.this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplication(), "Bạn vui lòng nhập đầy đủ thông tin!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Thoát sự kiện thêm
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void HienThiLoaiMonAn() {
        list_loaimon = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adtLoaiMon = new Adapter_LoaiMonAn(ThemMonAn.this, R.layout.spiner_loaimonan, list_loaimon);
        spnLoai.setAdapter(adtLoaiMon);
        adtLoaiMon.notifyDataSetChanged();
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Request_code_themloaitd) {
            if(resultCode == Activity.RESULT_OK && data != null) {
                Intent i = data;
                boolean check = data.getBooleanExtra("kiemtraloai", false);
                if(check)
                {
                    Toast.makeText(this, "Bạn đã thêm món ăn thành công!!!", Toast.LENGTH_SHORT).show();
                    HienThiLoaiMonAn();
                }
                else
                {
                    Toast.makeText(this, "Thêm món ăn thất bại!!!", Toast.LENGTH_SHORT).show();

                }
            }
        }
        else if (requestCode == Request_code_themhinh)
        {
            if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                DuongDanHinh = imageUri.toString();
                imgMonAn.setImageURI(imageUri);
            }
        }
    }
}
