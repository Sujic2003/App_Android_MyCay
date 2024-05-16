package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Database.DataHelper;

public class MonAnDAO {
    SQLiteDatabase db;

    public MonAnDAO(Context context) {
        DataHelper dbHeler = new DataHelper(context);
        db = dbHeler.getWritableDatabase(); // Cho phép ghi dữ liệu
    }


}
