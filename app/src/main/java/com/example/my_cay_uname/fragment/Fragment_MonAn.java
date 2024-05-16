package com.example.my_cay_uname.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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
        // Tao layout
        View view = inflater.inflate(R.layout.layout_hienthi_monan,container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
