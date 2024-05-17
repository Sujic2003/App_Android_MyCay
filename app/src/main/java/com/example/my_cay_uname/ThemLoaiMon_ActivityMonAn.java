package com.example.my_cay_uname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import DAO.LoaiMonAnDAO;

public class ThemLoaiMon_ActivityMonAn extends AppCompatActivity {
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
                String tenLoai = editLoaiMon.getText().toString().trim();
                if (tenLoai != null && !tenLoai.equals("")) {
                    boolean check = loaiMonAnDAO.ThemLoaiMonAn(tenLoai);

                    // Trả về kết quả
                    Intent iLoai = new Intent();
                    iLoai.putExtra("kiemtraloai", check);
                    setResult(Activity.RESULT_OK, iLoai);
                    // Kết thúc activity
                    finish();
                } else {
                    Toast.makeText(ThemLoaiMon_ActivityMonAn.this, "Bạn vui lòng nhập tên loại món", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
