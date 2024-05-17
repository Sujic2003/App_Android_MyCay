package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Database.DataHelper;

public class LoaiMonAnDAO {
    SQLiteDatabase db;
    DataHelper dbHelper;

    public LoaiMonAnDAO(Context context) {
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    // Thêm loại thức ăn
    public boolean ThemLoaiMonAn(String tenloai) {
        ContentValues  values = new ContentValues();
        values.put(DataHelper.LMA_TENLOAI, tenloai);
        // kiểm tra xem insert
        long check = db.insert(DataHelper.TB_LOAIMONAN, null, values);

        if(check != 0) {
            return true;
        } else {
            return false;
        }
    }

    // Cập nhật loại thức ăn

    // Xoá loại thức ăn
    // Lấy tên ứng với mã loại

    // Hiển thị danh sách

}
