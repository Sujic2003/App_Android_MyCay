package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.MonAnDTO;
import Database.DataHelper;

public class MonAnDAO {
    SQLiteDatabase db;

    public MonAnDAO(Context context) {
        DataHelper dbHeler = new DataHelper(context);
        db = dbHeler.getWritableDatabase(); // Cho phép ghi dữ liệu
    }

    // Thêm món ăn
    public boolean ThemMonAn(MonAnDTO monan) {
        ContentValues values = new ContentValues();
        values.put(DataHelper.MA_TENMON, monan.getTENMON());
        values.put(DataHelper.MA_GIATIEN, monan.getGIATIEN());
        values.put(DataHelper.MA_MALOAI, monan.getMALOAI());
        values.put(DataHelper.MA_HINHANH, monan.getHINHANH());

        long check = db.insert(DataHelper.TB_MONAN, null, values);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    // Cập nhật món ăn
    public boolean CapNhatMonAn(MonAnDTO monan) {
        ContentValues values = new ContentValues();
        values.put(DataHelper.MA_TENMON, monan.getTENMON());
        values.put(DataHelper.MA_GIATIEN, monan.getGIATIEN());
        values.put(DataHelper.MA_MALOAI, monan.getMALOAI());

        int check = db.update(DataHelper.TB_MONAN, values, DataHelper.MA_MAMONAN + " = ?", new String[]{String.valueOf(monan.getMALOAI())});
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    // Xoá món ăn
    public boolean XoaMonAn(int mamonan) {
        String query = "DELETE FROM " + DataHelper.TB_MONAN + " WHERE " + DataHelper.MA_MAMONAN + " = " + mamonan;
        try {
            db.execSQL(query);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu xóa thất bại
        }
    }
    // Xoá món ăn theo loại món ăn
    public boolean XoaMonAnTheoLoaiMon (int maloai) {
        String query = "DELETE FROM " + DataHelper.TB_MONAN + " WHERE " + DataHelper.MA_MALOAI + " = " + maloai;
        try {
            db.execSQL(query);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu xóa thất bại
        }
    }

    // Hiển thị món ăn
    public List<MonAnDTO> LayDanhSachMonAn() {
        List<MonAnDTO> listMonAn = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM" + DataHelper.TB_MONAN, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            MonAnDTO monan = new MonAnDTO();
            monan.setMAMONAN(cs.getInt(cs.getColumnIndex(DataHelper.MA_MAMONAN)));
            monan.setTENMON(cs.getString(cs.getColumnIndex(DataHelper.MA_TENMON)));
            monan.setGIATIEN(cs.getInt(cs.getColumnIndex(DataHelper.MA_GIATIEN)));
            monan.setMALOAI(cs.getInt(cs.getColumnIndex(DataHelper.MA_MALOAI)));
            listMonAn.add(monan);
            cs.moveToNext();
        }
        return listMonAn;
    }
}
