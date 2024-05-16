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
            banDTOList.add(ban);
            cs.moveToNext();
        }
        return banDTOList;
    }
    public boolean ThemBanAn()
    {
        ContentValues values = new ContentValues();
        values.put(DataHelper.BAN_TINHTRNAG, "Trống");

        long kt = db.insert(DataHelper.TB_BAN, null,values);
        if (kt != 0)
            return true;
        else
            return false;
    }
}
