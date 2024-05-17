package com.example.my_cay_uname.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.Adapter.Adapter_MonAn;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.ThemMonAn;
import com.example.my_cay_uname.TrangChu;

import java.util.List;

import DAO.MonAnDAO;
import DTO.MonAnDTO;

public class Fragment_MonAn extends Fragment {
    public static int Request_code_them = 111;
    List<MonAnDTO> listMonAn;
    Adapter_MonAn adtMonAn;
    MonAnDTO monAnDTO;
    MonAnDAO monAnDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monan, container, false);
        setHasOptionsMenu(true);
        //Đặt tiêu đề cho fragment
        ((TrangChu)getActivity()).getSupportActionBar().setTitle("MÓN ĂN");
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
        listMonAn = monAnDAO.
    }
}
