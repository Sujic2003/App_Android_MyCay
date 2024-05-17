package com.example.my_cay_uname;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class DangKy extends AppCompatActivity {

    Button btnChon, btnDK, btnDN;
    EditText edtHoTen, edtNgaySinh, edtTenDK, edtMK, edtXNMK;
    RadioButton rdNam, rdNu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangky);

        btnChon = (Button) findViewById(R.id.btn_ChonNS);
        btnDK = (Button) findViewById(R.id.btn_DK);
        btnDN = (Button) findViewById(R.id.btn_DN);
        edtHoTen = (EditText) findViewById(R.id.edt_HoTen);
        edtNgaySinh = (EditText) findViewById(R.id.edt_NgaySinh);
        edtTenDK = (EditText) findViewById(R.id.edt_TenDK);
        edtMK = (EditText) findViewById(R.id.edt_MatKhau);
        edtXNMK = (EditText) findViewById(R.id.edt_XNMK);
        rdNam = (RadioButton) findViewById(R.id.rd_Nam);
        rdNu = (RadioButton) findViewById(R.id.rd_Nu);

        NhanVienDAO dao_nv = new NhanVienDAO(this);

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DangKy.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtNgaySinh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();

            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString();
                String ngaysinh = edtNgaySinh.getText().toString();
                String tendn = edtTenDK.getText().toString();
                String mk = edtMK.getText().toString();
                String xnmk = edtXNMK.getText().toString();
                String gioitinh;
                if(rdNam.isChecked())
                    gioitinh = rdNam.getText().toString();
                else
                    gioitinh = rdNu.getText().toString();

                if (hoten.isEmpty()||ngaysinh.isEmpty()||tendn.isEmpty()||mk.isEmpty()||xnmk.isEmpty()){
                    Toast.makeText(DangKy.this, "Không để trống thông tin.", Toast.LENGTH_LONG).show();
                } else {
                    if(!mk.equals(xnmk)){
                        Toast.makeText(DangKy.this, "Mật khẩu xác nhận không đúng.", Toast.LENGTH_LONG).show();
                    } else if (dao_nv.ktraNguoiDung(tendn)) {
                        Toast.makeText(DangKy.this, "Tên người dùng đã tồn tại.", Toast.LENGTH_LONG).show();
                    } else {
                        NhanVienDTO nv = new NhanVienDTO();
                        nv.setTENNV(hoten);
                        nv.setNGAYSINH(ngaysinh);
                        nv.setGIOITINH(gioitinh);
                        nv.setTENDN(tendn);
                        nv.setMATKHAU(mk);
                        boolean kt = dao_nv.addNhanVien(nv);
                        if (kt){
                            Toast.makeText(DangKy.this, "Đăng kí thành công.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(DangKy.this, "Đăng kí thất bại.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        //Button đăng nhập
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}
