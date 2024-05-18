package com.example.my_cay_uname;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import DAO.LoaiMonAnDAO;

public class ThemLoaiMonAn extends AppCompatActivity {
    EditText editLoaiMon;
    Button btnThemLoaiMon;
    LoaiMonAnDAO loaiMonAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themloaimonan);
        editLoaiMon = (EditText) findViewById(R.id.editThemLoai);
        btnThemLoaiMon = (Button) findViewById(R.id.btnThemLoaiMonAn);
        loaiMonAnDAO = new LoaiMonAnDAO(this);

        btnThemLoaiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiMonAn = editLoaiMon.getText().toString();
                if(tenLoaiMonAn != null && !tenLoaiMonAn.equals("")) {
                    // Thêm loại món ăn
                    boolean check = loaiMonAnDAO.ThemLoaiMonAn(tenLoaiMonAn);
                    //Kiểm tra thêm loại
                    if(check) {
                        Toast.makeText(getApplication(), "Bạn đã thêm loại món ăn thành công!", Toast.LENGTH_SHORT).show();
                        // Trả kết quả về Fragment_LoaiMonAn
                        setResult(RESULT_OK);
                        // Kết thúc activity
                        finish();
                    }else {
                        Toast.makeText(getApplication(), "Bạn thêm loại món ăn không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThemLoaiMonAn.this, "Bạn vui lòng nhập loại món!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
