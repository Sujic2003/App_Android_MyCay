package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import DTO.LoaiMonAnDTO;
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
    public boolean CapNhatLoaiMonAn(LoaiMonAnDTO loai) {
        ContentValues values = new ContentValues();
        values.put(DataHelper.LMA_TENLOAI, loai.getTENLOAI());
        // Kiểm tra xem câu lệnh đã thực hiện thành công
        long check = db.update(DataHelper.TB_LOAIMONAN, values, DataHelper.LMA_MALOAI + " = ?",
                new String[]{String.valueOf(loai.getMALOAI())});

        if(check != 0) {
            return true;
        } else {
            return false;
        }
    }
    // Xoá loại thức ăn
    public boolean XoaLoaiMonAn (int MaLoai) {
        String queryDelete ="DELETE FROM" + DataHelper.TB_LOAIMONAN + " WHERE " + DataHelper.LMA_MALOAI + " = " + MaLoai;
        try {
            // Thực hiên câu lênh xoá
            db.execSQL(queryDelete);
            // Trả về true nếu xoá thành công
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu không xoá thành công
            return false;
        }
    }
    // Lấy tên ứng với mã loại


    // Hiển thị danh sách
    public List<LoaiMonAnDTO> LayDanhSachLoaiMonAn() {
        List<LoaiMonAnDTO> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM " + DataHelper.TB_LOAIMONAN , null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            LoaiMonAnDTO loai = new LoaiMonAnDTO();
            loai.setMALOAI(cs.getInt(cs.getColumnIndex(DataHelper.LMA_MALOAI)));
            loai.setTENLOAI(cs.getString(cs.getColumnIndex(DataHelper.LMA_TENLOAI)));
            list.add(loai);
            cs.moveToNext();
        }
        return list;
    }

}
