package com.example.my_cay_uname.fragment;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.my_cay_uname.Adapter.Adapter_NhanVien;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.ThemNhanVien;

import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import Database.DataHelper;

public class NhanvienFragment extends Fragment {
    ListView listView;
    List<NhanVienDTO> list_nv;
    NhanVienDAO nvDAO;
    Adapter_NhanVien myadapter;
    EditText editTen, edtNgaysinh;
    RadioButton rdNam, rdNu;
    Button btnLuu, btnHuy, btnChon;
    int manv;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_nhanvien, container, false);
        listView = (ListView) view.findViewById(R.id.listview_nhanvien);

        rdNam = (RadioButton) view.findViewById(R.id.rd_Nam);
        rdNu = (RadioButton) view.findViewById(R.id.rd_Nu);

        editTen = (EditText) view.findViewById(R.id.ten);
        edtNgaysinh = (EditText) view.findViewById(R.id.edt_NgaySinh);;
        btnLuu =  (Button) view.findViewById(R.id.btn_Luu);
        btnHuy = (Button) view.findViewById(R.id.btn_Huy);
        btnChon = (Button) view.findViewById(R.id.btn_ChonNS);

        nvDAO = new NhanVienDAO(getActivity());
        if (nvDAO == null) {
            Toast.makeText(getActivity(), "Không thể khởi tạo NhanVienDAO.", Toast.LENGTH_LONG).show();
            return view;
        }
        HienThiNhanVien();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVienDTO nv =list_nv.get(position);
                editTen.setText(nv.getTENNV());
                edtNgaysinh.setText(nv.getNGAYSINH());
                if(nv.getGIOITINH().equals("Nam"))
                {
                    rdNam.setChecked(true);
                }
                else {
                    rdNu.setChecked(true);
                }
                manv = nv.getMANV();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có chắc muốn xóa nhân viên?");
                builder.setCancelable(true);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NhanVienDTO nv = list_nv.get(position);
                        manv = nv.getMANV();
                        boolean kt = nvDAO.deleteNhanVien(manv);
                        if(kt){
                            HienThiNhanVien();
                            Toast.makeText(getActivity(), "Đã xóa nhân viên.", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getActivity(), "Không thể xóa nhân viên.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ten = editTen.getText().toString();
                String NgaySinh = edtNgaysinh.getText().toString();
                String GioiTinh;
                if (rdNam.isChecked() == true) {
                    GioiTinh = rdNam.getText().toString();
                } else
                {
                    GioiTinh = rdNu.getText().toString();
                }
                if (Ten.isEmpty() || NgaySinh.isEmpty()) {
                    Toast.makeText(getActivity(), "Không để trống các thông tin.", Toast.LENGTH_LONG).show();
                } else {

                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMANV(manv);
                    nv.setTENNV(Ten);
                    nv.setNGAYSINH(NgaySinh);
                    nv.setGIOITINH(GioiTinh);
                    boolean kt = nvDAO.updateNhanVien(nv);
                    if(kt){
                        HienThiNhanVien();
                        Toast.makeText(getActivity(), "Cập nhật nhân viên thành công.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Cập nhật nhân viên không thành công.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTen.setText("");
                edtNgaysinh.setText("");
                rdNam.setChecked(true);
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
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

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemNV =menu.add(1, R.id.item_ThemNhanVien,1, "Thêm nhân viên");
        itThemNV.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_ThemNhanVien:
               Intent intent = new Intent(getActivity(), ThemNhanVien.class);
               startActivity(intent);
               break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void HienThiNhanVien() {
        if (nvDAO != null) {
        list_nv = nvDAO.getNhanVien();
        myadapter = new Adapter_NhanVien(getActivity(), R.layout.layout_listview_nhanvien, list_nv);
        listView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
        } else {
            if (getActivity() != null) {
                Toast.makeText(getActivity(), "Không thể hiển thị nhân viên.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
