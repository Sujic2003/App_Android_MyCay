package com.example.my_cay_uname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnHD, btnT1, btnT2,btnMV;
    boolean isColorChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnT1 = (Button) findViewById(R.id.btn_T1);
        btnT2 = (Button) findViewById(R.id.btn_T2);
        btnMV = (Button) findViewById(R.id.btn_MV) ;
        btnHD = (Button) findViewById(R.id.btn_HoaDon);
        //tạo sự kiện click cho các button


        btnT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), Tang1.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }

            }
        });
        btnT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), Tang2.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
        btnMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplication(), MangVe.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}