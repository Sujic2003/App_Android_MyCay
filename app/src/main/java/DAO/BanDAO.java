package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.BanDTO;
import Database.DataHelper;

public class BanDAO {
    private static SQLiteDatabase db; //Tạo biến thực hiện trên CSDL
    private DataHelper dbHelper;// Tạo và hỗ trợ cập nhật dữ liệu qua CSDL
    private Context context; //Lưu trữ và truy cập ngữ cảnh ứng dụng

    public BanDAO(Context context) {
        this.context = context;
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase(); // hoặc dbHelper.getReadableDatabase();
    }

    public List<BanDTO> getAll_Table(){
        List<BanDTO> banDTOList = new ArrayList<>();//Tạo danh sách rỗng
        //Khởi tạo con trỏ
        Cursor cs =db.rawQuery("SELECT * FROM " + DataHelper.TB_BAN , null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            //
            BanDTO ban = new BanDTO();
            ban.setMABAN(cs.getInt(0));
            ban.setTINHTRANG(cs.getString(1));
            banDTOList.add(ban);
            cs.moveToNext();
        }
        cs.close();
        dbHelper.close();
        return banDTOList;
    }
}
