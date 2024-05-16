package com.example.my_cay_uname;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Slide;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.my_cay_uname.fragment.Fragment_MonAn;
import com.google.android.material.navigation.NavigationView;

public class TrangChu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawelayout;
    // Khai báo fragment
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawelayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawelayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawelayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Lấy id của sự kiện click trên navigation
        int id = item.getItemId();
        if(id == R.id.nav_nhanvien )
        {
            // Tạo fragment mới thay thế fragment cũ
            FragmentTransaction tran_HienThiMonAn = fragmentManager.beginTransaction();
            // Khai baó đối tượng hiển thị
            Fragment_MonAn fragmentMonAn = new Fragment_MonAn();
            tran_HienThiMonAn.replace(R.id.content_frame, fragmentMonAn);
            tran_HienThiMonAn.commit();
            //Đóng draw khi hiển thị fragment
            item.setChecked(true);
            mDrawelayout.closeDrawers();
        }
        else if(id == R.id.nav_monan) {

        }
        else if (id == R.id.nav_hoadon)
        {

        }
        else if(id == R.id.nav_dangxuat)
        {

        }
        mDrawelayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawelayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawelayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}