package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import Database.DataHelper;

public class HoaDonDAO {
    private  static SQLiteDatabase db; //Tạo biến thực hiện trên CSDL
    private DataHelper dbHelper;// Tạo và hỗ trợ cập nhật dữ liệu qua CSDL
    private Context context; //Lưu trữ và truy cập ngữ cảnh ứng dụng

    public HoaDonDAO(Context context) {
        this.context = context;
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    // Danh sach hoá đơn
    public List<HoaDonDTO> LayTatCaHoaDon() {
        //Khởi tạo list
        List<HoaDonDTO> HoaDonList = new ArrayList<>();
        //  Truy vấn vào cơ sở dữ liệu
        String queryBill = "SELECT * FROM " + DataHelper.TB_HOADON;
        // Tạo con trỏ bản
        Cursor cs = db.rawQuery(queryBill, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            HoaDonDTO hoadon = new HoaDonDTO();
            hoadon.setMAHOADON(cs.getInt(0));
            hoadon.setNGAYTAO(cs.getString(1));
            hoadon.setTONGTIEN(cs.getInt(2));
            HoaDonList.add(hoadon);
            cs.moveToNext(); //Di chuyển đển vị trí tiếp theo
        }
        cs.close(); // Đóng con trỏ
        dbHelper.close();
        return  HoaDonList;
    }
}
