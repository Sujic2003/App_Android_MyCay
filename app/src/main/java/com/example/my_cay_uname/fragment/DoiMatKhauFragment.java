package com.example.my_cay_uname.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.R;
import com.example.my_cay_uname.TrangChu;

import DAO.NhanVienDAO;

public class DoiMatKhauFragment extends Fragment {
    EditText edtMKcu, edtMKmoi, edtXNMK;
    Button btnThayDoi;
    TrangChu m;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);

        edtMKcu = (EditText) view.findViewById(R.id.edt_MKCu);
        edtMKmoi = (EditText) view.findViewById(R.id.edt_MKMoi);
        edtXNMK = (EditText) view.findViewById(R.id.edt_XNMK);
        btnThayDoi = (Button) view.findViewById(R.id.btn_DoiMK);

        m = (TrangChu) getActivity();

        NhanVienDAO nv = new NhanVienDAO(getActivity());

        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkcu = edtMKcu.getText().toString();
                String mkmoi = edtMKmoi.getText().toString();
                String xnmk = edtXNMK.getText().toString();

                if(mkcu.isEmpty()||mkmoi.isEmpty()||xnmk.isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else if(!nv.KiemTraDN(m.getsTendn(), mkcu)){
                    Toast.makeText(getActivity(), "Mật khẩu cũ không chính xác.", Toast.LENGTH_SHORT).show();
                } else if (!mkmoi.equals(xnmk)){
                    Toast.makeText(getActivity(), "Mật khẩu xác nhận không trùng khớp.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean kt = nv.updateMatKhau(m.getsTendn(), mkmoi);
                    if(kt)
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
