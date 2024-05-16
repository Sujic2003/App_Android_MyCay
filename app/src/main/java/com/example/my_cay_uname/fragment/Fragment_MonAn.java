package com.example.my_cay_uname.fragment;

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

import com.example.my_cay_uname.R;

public class Fragment_MonAn extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Tạo menu cho layout món ăn
        setHasOptionsMenu(true);
        // Tao layout
        View view = inflater.inflate(R.layout.layout_hienthi_monan,container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1,R.id.item_ThemMonAn, 1,"Thêm Món Ăn");
        itemThemBanAn.setIcon(R.drawable.baseline_add_circle_24);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    //Sự kiện clicl vào item
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
