package com.example.my_cay_uname;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.my_cay_uname.fragment.DoiMatKhauFragment;
import com.example.my_cay_uname.fragment.Fragment_MonAn;
import com.example.my_cay_uname.fragment.NhanvienFragment;
import com.example.my_cay_uname.fragment.TrangChuFregment;
import com.google.android.material.navigation.NavigationView;

public class TrangChu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawelayout;

    // Khai báo fragment
    FragmentManager fragmentManager;
    private NavigationView navigationView;
    private Menu menu;
    private String sTendn="";

    public String getsTendn() {
        return sTendn;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String tendn = intent.getStringExtra("TenDN");
        sTendn = tendn;
        mDrawelayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawelayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawelayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Lấy id của sự kiện click trên navigation
        int id = item.getItemId();
        switch (id){
            case R.id.nav_TrangChu:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new TrangChuFregment()).commit();
                break;

            case R.id.nav_nhanvien:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new NhanvienFragment()).commit();
                break;
            case R.id.nav_monan:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Fragment_MonAn()).commit();
                break;
            case R.id.nav_doimatkhau:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DoiMatKhauFragment()).commit();
                break;
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