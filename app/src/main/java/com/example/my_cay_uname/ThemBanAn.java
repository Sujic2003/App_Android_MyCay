package com.example.my_cay_uname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import DAO.BanDAO;

public class ThemBanAn extends AppCompatActivity {
    Button btnThem;
    EditText editTen;
    BanDAO banDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_ban_an);
        editTen = (EditText) findViewById(R.id.edit_Tenban);
        btnThem = (Button) findViewById(R.id.btn_ThemBan);

        banDAO = new BanDAO(this);
        editTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTen.setText("");
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenban= editTen.getText().toString();
                if(tenban != null && !tenban.equals(""))
                {
                    boolean kt = banDAO.addBanAn(tenban);
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK,intent);//Truyền inten vào setResult
                    if (kt) {
                        Toast.makeText(getApplication(), "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                    Toast.makeText(getApplication(), "Thêm bàn không thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    }
                }
                else
                {
                    Toast.makeText(ThemBanAn.this, "Hãy nhập tên bàn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}