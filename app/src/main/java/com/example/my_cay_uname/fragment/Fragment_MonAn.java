package com.example.my_cay_uname.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.Adapter.Adapter_Fragment_MonAn;
import com.example.my_cay_uname.Adapter.Adapter_LoaiMonAn;
import com.example.my_cay_uname.Adapter.Adapter_MonAn;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.ThemMonAn;
import com.example.my_cay_uname.TrangChu;

import java.util.ArrayList;
import java.util.List;

import DAO.LoaiMonAnDAO;
import DAO.MonAnDAO;
import DTO.LoaiMonAnDTO;
import DTO.MonAnDTO;

public class Fragment_MonAn extends Fragment {
    public static int Request_code_them = 111;

    Button btnLuuMon, btnThoatMon;
    EditText editTenMon, editGia;
    Spinner spnLoaiMon;
    ListView listView_MonAn;
    //----Món ăn
    List<MonAnDTO> listMonAnDTO;
    Adapter_Fragment_MonAn adapterMonAn;
    MonAnDAO monAnDAO;
    MonAnDTO monAnDTO;
    //-----Loại món ăn
    LoaiMonAnDAO loaiMonAnDAO;
    Adapter_LoaiMonAn adapterLoaiMonAn;
    List<LoaiMonAnDTO> listLoai;

    //
    int mamonan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monan, container, false);
        setHasOptionsMenu(true);
        //Đặt tiêu đề cho fragment
        ((TrangChu)getActivity()).getSupportActionBar().setTitle("MÓN ĂN");
        // Gọi ánh xạ
        editTenMon = (EditText) view.findViewById(R.id.TT_editTenMA);
        editGia = (EditText) view.findViewById(R.id.TT_editGiaTien);
        spnLoaiMon = (Spinner) view.findViewById(R.id.TT_spnLoaiMA);
        listView_MonAn = (ListView) view.findViewById(R.id.lvMonAn);
        btnLuuMon = (Button) view.findViewById(R.id.TT_btnLuuMon);
        btnThoatMon = (Button) view.findViewById(R.id.TT_btnThoatThemMon);

        // Khởi tạo
        monAnDAO = new MonAnDAO(getActivity());
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());

        // Hiển thị danh sách dữ liệu lên list view và spiner
        HienThiMonAn();
        HienThiLoaiMonAn();

        //Chọn listview
        listView_MonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiện thị lên edit khi click vào listview
                MonAnDTO selectMonAn = listMonAnDTO.get(position);
                editTenMon.setText(selectMonAn.getTENMON());
                editGia.setText(String.valueOf(selectMonAn.getGIATIEN())); // Ép kiểu
                // Lưu mã món ăn khi chon
                mamonan = selectMonAn.getMAMONAN();
                //
                listLoai = loaiMonAnDAO.LayDanhSachLoaiMonAn();
                //Tìm địa chỉ mã loại
                int startSpiner = -1;
                for (int i = 0; i < listLoai.size(); i++) {
                    if(listLoai.get(i).getMALOAI() == selectMonAn.getMALOAI()) {
                        startSpiner = i;
                        break;
                    }
                }
                if(startSpiner != -1)
                    spnLoaiMon.setSelection(startSpiner);

            }
        });

        // Nhấn giữ để xoá món ăn
        listView_MonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MonAnDTO laytenmon = listMonAnDTO.get(position);
                //
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.question);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xoá món " + laytenmon.getTENMON());
                builder.setCancelable(true);
                //
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    // Lấy đối tượng tuơng ứng
                        MonAnDTO selectMonAn = listMonAnDTO.get(position);
                        //
                        int mamon = selectMonAn.getMAMONAN();
                        //
                        boolean check = monAnDAO.XoaMonAn(mamon);
                        if(check) {
                            HienThiMonAn();
                            Toast.makeText(getActivity(), "Bạn đã xoá thành công món " + selectMonAn.getTENMON(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Bạn không xoá được món ăn!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });

        btnLuuMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin nhập
                String tenMon = editTenMon.getText().toString();
                String donGia = editGia.getText().toString();
                LoaiMonAnDTO spnLoai = (LoaiMonAnDTO) spnLoaiMon.getSelectedItem();
                // Kiểm tra
                if(tenMon.equals("") || donGia.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng không bỏ trống thông tin!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Tạo đối tượng mới
                MonAnDTO capnhatmon = new MonAnDTO();
                capnhatmon.setMAMONAN(mamonan); //mã món được chọn
                capnhatmon.setTENMON(tenMon);
                capnhatmon.setGIATIEN(Integer.parseInt(donGia));
                capnhatmon.setMALOAI(spnLoai.getMALOAI());

                // kiểm tra
                boolean check = monAnDAO.CapNhatMonAn(capnhatmon);
                if(check) {
                    // Cập nhật lại danh sách món và giao diện
                    HienThiMonAn();
                    Toast.makeText(getActivity(), "Cập nhật món " + capnhatmon.getTENMON() + " thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Cập nhật món thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnThoatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTenMon.setText("");
                editGia.setText("");
                mamonan = -1;
                Toast.makeText(getActivity(), "Đã huỷ thông tin!!!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem iThemMon = menu.add(1, R.id.item_ThemMonAn, 1,  "Thêm món ăn");
        iThemMon.setIcon(R.drawable.baseline_add_circle_24);
        iThemMon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    //Sự kiện clicl vào item
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_ThemMonAn:
                Intent intent_Themmon = new Intent(getActivity(), ThemMonAn.class);
                startActivityForResult(intent_Themmon, Request_code_them);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_code_them && resultCode == getActivity().RESULT_OK) {
            HienThiMonAn(); // Cập nhật lại danh sách
        }
    }

    // Hiển thị loại món ăn
    private void HienThiMonAn() {
        listMonAnDTO = monAnDAO.LayDanhSachMonAn();
        adapterMonAn = new Adapter_Fragment_MonAn(getActivity(), R.layout.layout_item_monan, listMonAnDTO);
        listView_MonAn.setAdapter(adapterMonAn);
        adapterMonAn.notifyDataSetChanged();
    }
    // Hiển thị danh sách lên spiner
    private void HienThiLoaiMonAn() {
        listLoai = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapterLoaiMonAn = new Adapter_LoaiMonAn(getActivity(), R.layout.spiner_loaimonan, listLoai);
        spnLoaiMon.setAdapter(adapterLoaiMonAn);
        adapterLoaiMonAn.notifyDataSetChanged();
    }
}
