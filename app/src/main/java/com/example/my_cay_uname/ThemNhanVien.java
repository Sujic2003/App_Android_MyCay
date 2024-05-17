package com.example.my_cay_uname;

import android.app.DatePickerDialog;
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

import com.example.my_cay_uname.fragment.NhanvienFragment;

import java.util.Calendar;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class ThemNhanVien extends AppCompatActivity {

    EditText edtten, edtNgaysinh, edtTenDN, edtMK;
    RadioButton rdNam, rdNu;
    Button btnChon, btnThem, btnHuy;

    NhanVienDAO nvDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        edtten = (EditText) findViewById(R.id.edt_themTen);
        edtNgaysinh = (EditText) findViewById(R.id.edt_themNgaySinh);
        edtTenDN = (EditText) findViewById(R.id.edt_themTenDN);
        edtMK = (EditText) findViewById(R.id.edt_themMatKhau);
        rdNam =(RadioButton) findViewById(R.id.rd_themNam);
        rdNu = (RadioButton) findViewById(R.id.rd_themNu);
        btnChon = (Button) findViewById(R.id.btn_themChonNS);
        btnThem = (Button) findViewById(R.id.btn_themThemNV);
        btnHuy = (Button) findViewById(R.id.btn_themHuy);

        // Khởi tạo NhanVienDAO
        nvDAO = new NhanVienDAO(this);
        if (nvDAO == null) {
            Toast.makeText(this, "Không thể khởi tạo NhanVienDAO.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvDAO = new NhanVienDAO(ThemNhanVien.this);
                String Ten = edtten.getText().toString();
                String NgaySinh = edtNgaysinh.getText().toString();
                String TenDN = edtTenDN.getText().toString();
                String MK = edtMK.getText().toString();
                String GioiTinh;
                if(rdNam.isChecked())
                {
                    GioiTinh = rdNam.getText().toString();
                }
                else
                {
                    GioiTinh = rdNu.getText().toString();
                }
                if(Ten.isEmpty()||NgaySinh.isEmpty()||TenDN.isEmpty()||MK.isEmpty()){
                    Toast.makeText(ThemNhanVien.this, "Không để trống các thông tin.", Toast.LENGTH_LONG).show();
                } else {
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setTENNV(Ten);
                    nv.setNGAYSINH(NgaySinh);
                    nv.setGIOITINH(GioiTinh);
                    nv.setTENDN(TenDN);
                    nv.setMATKHAU(MK);

                    boolean kt = nvDAO.addNhanVIen(nv);
                    if(kt){
                        Toast.makeText(getApplication(), "Thêm nhân viên thành công.", Toast.LENGTH_SHORT).show();
                        NhanvienFragment qlnv = new NhanvienFragment();
                        qlnv.HienThiNhanVien();
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "Thêm nhân viên không thành công.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThemNhanVien.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtNgaysinh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
    }
}