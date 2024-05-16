package com.example.my_cay_uname.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.Adapter_Table;
import com.example.my_cay_uname.Menu;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.Table;
import com.example.my_cay_uname.Tang1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import DAO.BanDAO;
import DTO.BanDTO;

public class TrangChuFregment extends Fragment {
    FloatingActionButton floatingActionButton;
    GridView gridView;
    List<BanDTO> banDTOList;
    BanDAO banDAO;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu,container,false);

        gridView = (GridView) view.findViewById(R.id.gv_Ban);
        banDAO = new BanDAO(getActivity());
        banDTOList = banDAO.getAll_Table();
        Adapter_Table adapterTable = new Adapter_Table(getActivity(), R.layout.layout_item_table, banDTOList);
        gridView.setAdapter(adapterTable);
        adapterTable.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTable = String.valueOf(banDTOList.get(position));
                Intent intent = new Intent(getActivity(), Menu.class);
                intent.putExtra("selectedTable", selectedTable);
                startActivity(intent);
            }
        });
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.btnTHemBan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Tạo một bàn mới
                BanDTO newTable = new BanDTO();

                // Thêm bàn mới vào cơ sở dữ liệu
                boolean result = banDAO.ThemBanAn();
                if (result) {
                    // Nếu thêm thành công, cập nhật danh sách bàn và cập nhật giao diện người dùng
                    banDTOList.clear();
                    banDTOList.addAll(banDAO.getAll_Table());
                    adapterTable.notifyDataSetChanged();
                } else {
                    // Xử lý khi thêm bàn không thành công (nếu cần)
                    Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;

    }
}
