package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


import DTO.NhanVienDTO;
import Database.DataHelper;

public class NhanVienDAO {
    //-------
    private  static SQLiteDatabase db; //Tạo biến thực hiện trên CSDL
    private DataHelper dbHelper;// Tạo và hỗ trợ cập nhật dữ liệu qua CSDL
    private Context context; //Lưu trữ và truy cập ngữ cảnh ứng dụng

    public NhanVienDAO(Context context) {
        this.context = context;
        dbHelper = new DataHelper(context);
        db = dbHelper.getWritableDatabase(); // Cho phép ghi dữ liệu vào database
    }
    //------- Thêm Nhân Viên
    //Khởi tạo dữ liệu mặc định nhân viên
    private void insertNhanVien(NhanVienDTO nv) {
        ContentValues values = new ContentValues();
        // Thêm dữ liệu mặt định
        values.put("TENNV", nv.getTENNV());
        values.put("TENDN",nv.getTENDN());
        values.put("MATKHAU",nv.getMATKHAU());
        values.put("GIOITINH",nv.getGIOITINH());
        values.put("NGAYSINH",nv.getNGAYSINH());
        db.insert("NHANVIEN",null,values);

    }
    //------- Xoá Nhân Viên

    //------- Sửa Nhân Viên

    //------- Hiển thị Nhân Viên
    public static List<NhanVienDTO> getNhanVien() {
        List<NhanVienDTO> list = new ArrayList<>(); //Tạo danh sách rỗng
        // Lấy dữ liệu
        Cursor cs = db.rawQuery("SELECT * FROM NHANVIEN", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            NhanVienDTO nv = new NhanVienDTO();// Tạo đối tượng
            nv.setMANV(cs.getInt(0));
            nv.setTENNV(cs.getString(1));
            nv.setTENDN(cs.getString(2));
            nv.setMATKHAU(cs.getString(3));
            nv.setGIOITINH(cs.getString(4));
            nv.setNGAYSINH(cs.getString(5));
            list.add(nv);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }


}
