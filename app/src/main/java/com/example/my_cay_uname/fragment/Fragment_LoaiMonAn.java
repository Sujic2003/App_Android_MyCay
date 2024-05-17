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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.Adapter.Adapter_ListLoaiMon;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.ThemLoaiMonAn;
import com.example.my_cay_uname.TrangChu;

import java.util.List;

import DAO.LoaiMonAnDAO;
import DAO.MonAnDAO;
import DTO.LoaiMonAnDTO;

public class Fragment_LoaiMonAn extends Fragment {
    public static int Request_code_themLoai = 222;
    EditText editLoaiMon;
    Button btnLuuLoaiMon, btnThoatLoaiMon;
    ListView lvLoaiMon;

    List<LoaiMonAnDTO> listLoaiMon;
    Adapter_ListLoaiMon adtLoaiMon;

    LoaiMonAnDAO loaiMonAnDAO;
    LoaiMonAnDTO loaiMonAnDTO;
    MonAnDAO monAnDAO;
    int maloai;


    //Tạo giao diện hiển thị trong fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaimonan, container , false);
        //Đặt tiêu đề cho fragment
        ((TrangChu)getActivity()).getSupportActionBar().setTitle("LOẠI MÓN ĂN");
        // Đặt thanh menu bên trái
        setHasOptionsMenu(true);

        //
        lvLoaiMon =(ListView) view.findViewById(R.id.TT_lvLoaiMonAn);
        editLoaiMon = (EditText) view.findViewById(R.id.TT_editLoaiMA);
        btnLuuLoaiMon = (Button) view.findViewById(R.id.TT_btnLuuLoaiMonAn);
        btnThoatLoaiMon = (Button) view.findViewById(R.id.TT_btnThoatLoaiMonAn);

        //Hiển thị loại món ăn lên list view
        monAnDAO = new MonAnDAO(getContext());
        loaiMonAnDAO = new LoaiMonAnDAO(getContext());
        HienThiLoaiMon();

        //Sự kiện click hiển thị lên edit loại món
        lvLoaiMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoaiMonAnDTO loaiMonAnDTO = listLoaiMon.get(position);
                editLoaiMon.setText(loaiMonAnDTO.getTENLOAI());
                maloai = loaiMonAnDTO.getMALOAI();
            }
        });

        //Xoá loại món ăn trong listview khi nhấn lâu
        lvLoaiMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 loaiMonAnDTO = listLoaiMon.get(position);
                // Xây dựng Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.question);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xoá loại món: " + loaiMonAnDTO.getTENLOAI());
                builder.setCancelable(true);
                //
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Lấy mã loại
                        int maloai = loaiMonAnDTO.getMALOAI();
                        // Thực hiện xoá loại theo mã
                        boolean check = loaiMonAnDAO.XoaLoaiMonAn(maloai);
                        if(check) {
                            HienThiLoaiMon();
                            Toast.makeText(getActivity(), "Bạn đã xoá loại món ăn \"" + loaiMonAnDTO.getTENLOAI() + "\" thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Bạn đã xoá loại không thành công!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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


        // Các cập nhật và tải lại loại món ăn
        btnLuuLoaiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = editLoaiMon.getText().toString();
                if(tenloai.isEmpty()) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập loại món ăn!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Đặt giá trị dữ liệu
                    loaiMonAnDTO = new LoaiMonAnDTO();
                    loaiMonAnDTO.setMALOAI(maloai);
                    loaiMonAnDTO.setTENLOAI(tenloai);
                    //Kiểm tra có cập nhật thành công haykhông
                    boolean check = loaiMonAnDAO.CapNhatLoaiMonAn(loaiMonAnDTO);
                    if(check){
                        Toast.makeText(getActivity(), "Đã cập nhật loại món.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Cập nhật loại món không thành công.", Toast.LENGTH_SHORT).show();
                    }
                }
                // Tải lại trang
                HienThiLoaiMon();
            }
        });

        // Thoat
        btnThoatLoaiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLoaiMon.setText("");
                maloai = -1;
                Toast.makeText(getActivity(), "Đã huỷ.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    // Đặt opiton
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item_ThemLoai = menu.add(1, R.id.item_ThemLoaiMonAn,1, "Thêm Loại Món");
        item_ThemLoai.setIcon(R.drawable.row);
        item_ThemLoai.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    // Đặt sự kiện click menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_ThemLoaiMonAn:
                Intent intent_ThemLoai = new Intent(getActivity(), ThemLoaiMonAn.class);
                //
                startActivityForResult(intent_ThemLoai, Request_code_themLoai); // Thay đổi ở đây
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //  Kết quả trả về của thêm loại món ăn
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_code_themLoai && resultCode == getActivity().RESULT_OK) {
            HienThiLoaiMon(); // Cập nhật lại danh sách
        }
    }

    private void HienThiLoaiMon() {
        listLoaiMon = loaiMonAnDAO.LayDanhSachLoaiMonAn();

        adtLoaiMon = new Adapter_ListLoaiMon(getActivity(), R.layout.layout_item_loaimonan, listLoaiMon);
        lvLoaiMon.setAdapter(adtLoaiMon);
        adtLoaiMon.notifyDataSetChanged();
    }
}
