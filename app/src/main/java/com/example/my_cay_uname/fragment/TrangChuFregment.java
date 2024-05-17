package com.example.my_cay_uname.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.my_cay_uname.Adapter.Adapter_Table;
import com.example.my_cay_uname.Menu;
import com.example.my_cay_uname.R;
import com.example.my_cay_uname.Table;
import com.example.my_cay_uname.Tang1;
import com.example.my_cay_uname.ThemBanAn;
import com.example.my_cay_uname.ThemNhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import DAO.BanDAO;
import DTO.BanDTO;
import DTO.NhanVienDTO;

public class TrangChuFregment extends Fragment {
    FloatingActionButton floatingActionButton;
    GridView gridView;
    List<BanDTO> banDTOList;
    Adapter_Table adapterTable;
    public static int Request_code_them = 1;

    BanDAO banDAO;
    int maban;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu,container,false);
        setHasOptionsMenu(true);
        gridView = (GridView) view.findViewById(R.id.gv_Ban);
        banDAO = new BanDAO(getActivity());
        banDTOList = banDAO.getAll_Table();
        HienThiBan();

        adapterTable = new Adapter_Table(getActivity(), R.layout.layout_item_table, banDTOList);
        gridView.setAdapter(adapterTable);
        adapterTable.notifyDataSetChanged();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BanDTO selectedBan = banDTOList.get(position);
                String selectedTableName = selectedBan.getTENBAN(); // Lấy tên bàn từ đối tượng BanDTO
                Intent intent = new Intent(getActivity(), Menu.class);
                intent.putExtra("selectedTableName", selectedTableName);
                startActivity(intent);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có chắc muốn xóa bàn?");
                builder.setCancelable(true);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BanDTO nv = banDTOList.get(position);
                        maban = nv.getMABAN();
                        boolean kt = banDAO.deleteBan(maban);
                        if(kt){
                            HienThiBan();
                            Toast.makeText(getActivity(), "Đã xóa bàn.", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getActivity(), "Không thể xóa bàn.", Toast.LENGTH_LONG).show();
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
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull android.view.Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBan =menu.add(1, R.id.item_ThemBan,1, "Thêm bàn");
        itThemBan.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_ThemBan:
                Intent intent = new Intent(getActivity(), ThemBanAn.class);
                startActivityForResult(intent, Request_code_them);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void HienThiBan()
    {
        banDTOList = banDAO.getAll_Table();
        adapterTable = new Adapter_Table(getActivity(), R.layout.layout_item_table, banDTOList);
        gridView.setAdapter(adapterTable);
        adapterTable.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_code_them) {
            HienThiBan();
        }
    }
}
