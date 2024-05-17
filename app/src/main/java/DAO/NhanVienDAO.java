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
    SQLiteDatabase db; //Tạo biến thực hiện trên CSDL
     DataHelper dbHelper;// Tạo và hỗ trợ cập nhật dữ liệu qua CSDL
     Context context; //Lưu trữ và truy cập ngữ cảnh ứng dụng

    public NhanVienDAO(Context context) {
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
    public List<NhanVienDTO> getNhanVien() {
        List<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
        // Lấy dữ liệu
        Cursor cs = db.rawQuery("SELECT * FROM "+DataHelper.TB_NHANVIEN, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            NhanVienDTO nv = new NhanVienDTO();// Tạo đối tượng
            nv.setMANV(cs.getInt(cs.getColumnIndex(DataHelper.NV_MANV)));
            nv.setTENNV(cs.getString(cs.getColumnIndex(DataHelper.NV_TENNV)));
            nv.setTENDN(cs.getString(cs.getColumnIndex(DataHelper.NV_TENDN)));
            nv.setMATKHAU(cs.getString(cs.getColumnIndex(DataHelper.NV_MATKHAU)));
            nv.setGIOITINH(cs.getString(cs.getColumnIndex(DataHelper.NV_GIOITINH)));
            nv.setNGAYSINH(cs.getString(cs.getColumnIndex(DataHelper.NV_NGAYSINH)));
            list.add(nv);
            cs.moveToNext();
        }
        return list;
    }
    public boolean addNhanVIen(NhanVienDTO nv)
    {
        ContentValues values = new ContentValues();
        values.put(DataHelper.NV_TENNV, nv.getTENNV());
        values.put(DataHelper.NV_NGAYSINH, nv.getNGAYSINH());
        values.put(DataHelper.NV_GIOITINH, nv.getGIOITINH());
        values.put(DataHelper.NV_TENDN, nv.getTENDN());
        values.put(DataHelper.NV_MATKHAU, nv.getMATKHAU());

        long kt = db.insert(DataHelper.TB_NHANVIEN, null, values);
        if(kt ==-1)
        {
            return false;
        }
        else {
            return true;
        }

    }
    public  boolean updateNhanVien(NhanVienDTO nv){
        ContentValues values = new ContentValues();
        values.put(DataHelper.NV_TENNV, nv.getTENNV());
        values.put(DataHelper.NV_NGAYSINH, nv.getNGAYSINH());
        values.put(DataHelper.NV_GIOITINH, nv.getGIOITINH());
        long kt = db.update(DataHelper.TB_NHANVIEN, values, DataHelper.NV_MANV+"=?", new String[]{String.valueOf((nv.getMANV()))});
        if(kt==-1)
        {
            return false;
        }
        else {
            return true;
        }

    }
    public boolean deleteNhanVien(int manv){
        long kt = db.delete(DataHelper.TB_NHANVIEN, DataHelper.NV_MANV+"=?", new String[]{String.valueOf(manv)});
        if(kt==-1)
            return false;
        else
            return true;
    }
    public boolean KiemTraDN(String tendn, String mk)
    {
        String sTruyVan = "SELECT * FROM " + DataHelper.TB_NHANVIEN
                + " WHERE " + DataHelper.NV_TENDN + " = '" + tendn
                + "' AND " + DataHelper.NV_MATKHAU + " = '" + mk + "'";

        Cursor c = db.rawQuery(sTruyVan, null);
        if (c.getCount() != 0)
            return true;
        else
            return false;
    }
    public boolean updateMatKhau(String tendn, String mk){
        ContentValues values = new ContentValues();
        values.put(DataHelper.NV_MATKHAU, mk);
        long kt = db.update(DataHelper.TB_NHANVIEN, values,DataHelper.NV_TENDN+"=?", new String[]{String.valueOf(tendn)});
        if(kt==-1)
            return false;
        else
            return true;
    }

}
