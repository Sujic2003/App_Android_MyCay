package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.BanDTO;
import Database.DataHelper;

public class BanDAO {
    SQLiteDatabase db; //Tạo biến thực hiện trên CSDL
    DataHelper dbHelper;// Tạo và hỗ trợ cập nhật dữ liệu qua CSDL
    Context context; //Lưu trữ và truy cập ngữ cảnh ứng dụng

    public BanDAO(Context context) {
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase(); // hoặc dbHelper.getReadableDatabase();
    }

    public List<BanDTO> getAll_Table(){
        List<BanDTO> banDTOList = new ArrayList<BanDTO>();//Tạo danh sách rỗng
        //Truy vấn
        String truyvan = "SELECT * FROM " + DataHelper.TB_BAN;
        //Khởi tạo con trỏ
        Cursor cs =db.rawQuery(truyvan , null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            //
            BanDTO ban = new BanDTO();
            ban.setMABAN(cs.getInt(cs.getColumnIndex(DataHelper.BAN_MABAN)));
            ban.setTENBAN(cs.getString(cs.getColumnIndex(DataHelper.BAN_TENBAN)));
            ban.setTINHTRANG(cs.getString(cs.getColumnIndex(DataHelper.BAN_TINHTRNAG)));
            banDTOList.add(ban);
            cs.moveToNext();
        }
        return banDTOList;
    }
    public boolean addBanAn(String tenban)
    {
        ContentValues values = new ContentValues();
        values.put(DataHelper.BAN_TENBAN, tenban);
        values.put(DataHelper.BAN_TINHTRNAG, "Trống");

        long kt = db.insert(DataHelper.TB_BAN, null,values);
        if (kt != 0)
            return true;
        else
            return false;
    }
    public boolean updateBan(BanDTO ban)
    {
        ContentValues values = new ContentValues();
        values.put(DataHelper.BAN_MABAN, ban.getMABAN());
        values.put(DataHelper.BAN_TENBAN, ban.getTENBAN());
        values.put(DataHelper.BAN_TINHTRNAG, ban.getTINHTRANG());

        int kt = db.update(DataHelper.TB_BAN, values, DataHelper.BAN_MABAN + " = ?", new String[]{String.valueOf(ban.getMABAN())});

        if(kt != 0)
        {
            return true;
        }
        return false;
    }
    public boolean deleteBan(int maban)
    {
        String sTruyVan = "DELETE FROM " + DataHelper.TB_BAN + " WHERE " + DataHelper.BAN_MABAN + " = " + maban;

        try {
            db.execSQL(sTruyVan);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu xóa thất bại
        }
    }
}
