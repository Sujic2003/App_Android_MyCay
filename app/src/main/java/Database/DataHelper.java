package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    //----- Tên database
    private static final String DATABASE_NAME = "MYCAY.db";
    private static final int DATABASE_VERSION = 1;

    //----- Tạo các tên bảng
    public static String TB_NHANVIEN = "NHANVIEN";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BAN = "BAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CHITIET_GOIMON = "CHITIET_GOIMON";
    //----- Tên bảng hoá đơn
    public static String TB_HOADON = "HOADON";

    //----- Tên các trường TB_NHANVIEN
    public static String NV_MANV = "MANV";
    public static String NV_TENNV = "TENNV";
    public static String NV_TENDN = "TENDN";
    public static String NV_MATKHAU = "MATKHAU";
    public static String NV_GIOITINH = "GIOITINH";
    public static String NV_NGAYSINH = "NGAYSINH";

    //----- Tên các trường TB_MONAN
    public static String MA_MAMONAN = "MAMONAN";
    public static String MA_TENMON = "TENMON";
    public static String MA_GIATIEN = "GIATIEN";
    public static String MA_MALOAI = "MALOAI";
    public static String MA_HINHANH = "HINHANH";

    //----- Tên các trường TB_LOAIMONAN
    public static String LMA_MALOAI = "MALOAI";
    public static String LMA_TENLOAI = "TENLOAI";

    //----- Tên các trường TB_BAN
    public static String BAN_MABAN = "MABAN";
    public static String BAN_TENBAN = "TENBAN";
    public static String BAN_TINHTRNAG = "TINHTRANG";

    //----- Tên các trường TB_GOIMON
    public static String GM_MAGOIMON = "MAGOIMON";
    public static String GM_MANV = "MANV";
    public static String GM_NGAYGOI = "NGAYGOI";
    public static String GM_MABAN = "MABAN";

    //----- Tên các trường TB_CHITIET_GOIMON
    public static String CTGM_MAGOIMON = "MAGOIMON";
    public static String CTGM_MAMONAN = "MANMONAN";
    public static String CTGM_SOLUONG = "SOLUONG";


    //----- Tên các trường TB_HOADON
    public static String HD_MAHOADON = "MAHOADON";
    public static String HD_NGAYTAO = "NGAYTAO";
    public static String HD_TONGTIEN = "TONGTIEN";
    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNHANVIEN = "CREATE TABLE " + TB_NHANVIEN + " ( " +
                NV_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NV_TENNV + " TEXT NOT NULL, " +
                NV_TENDN + " TEXT NOT NULL, " +
                NV_MATKHAU + " TEXT NOT NULL, " +
                NV_GIOITINH + " TEXT NOT NULL, " +
                NV_NGAYSINH + " TEXT  NOT NULL ) ";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( " +
                MA_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MA_TENMON + " TEXT NOT NULL, " +
                MA_GIATIEN + " INTEGER NOT NULL, " +
                MA_MALOAI + " INTEGER NOT NULL, " +
                MA_HINHANH + " TEXT NOT NULL) " ;

        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + " ( " +
                LMA_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LMA_TENLOAI + " TEXT NOT NULL )";

        String tbBAN = "CREATE TABLE " + TB_BAN + " ( " +
                BAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BAN_TENBAN + " TEXT NOT NULL, " +
                BAN_TINHTRNAG + " TEXT NOT NULL ) " ;

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( " +
                GM_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GM_MABAN + " INTEGER NOT NULL, " +
                GM_MANV + " INTEGER NOT NULL, " +
                GM_NGAYGOI + " TEXT NOT NULL ) " ;

        String tbCHITIETGOIMON = "CREATE TABLE " + TB_CHITIET_GOIMON + " ( " +
                CTGM_MAGOIMON + " INTEGER , " +
                CTGM_MAMONAN + " INTEGER NOT NULL, " +
                CTGM_SOLUONG + " INTEGER NOT NULL,  " +
                " PRIMARY KEY ( " + CTGM_MAGOIMON + " , " + CTGM_MAMONAN + " ))";
        // Thêm dữ liệu mặc định vào bảng Bàn

        String tbHOADON = "CREATE TABLE " + TB_HOADON + " ( " +
                HD_MAHOADON + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HD_NGAYTAO + " TEXT NOT NULL, " +
                HD_TONGTIEN + " INTEGER NOT NULL) ";





        // Thực thi câu lệnh
        db.execSQL(tbNHANVIEN);
        db.execSQL(tbBAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITIETGOIMON);
        db.execSQL(tbHOADON);

        // Thêm dữ liệu mặc định vào bảng Nhân viên
        create_dataNhanVien(db);
        // Thêm dữ liệu mặc định vào bảng Hoá dơn
        create_dataHoaDon(db);
        // Thêm dữ liệu mặc định vào bảng Món ăn
        create_dataMonAn(db);
        // Thêm dữ liệu mặc định vào bảng Loại món ăn
        create_dataLoaiMonAn(db);
        // Thêm dữ liệu mặc định vào bảng Bàn
        create_dataBan(db);
        // Thêm dữ liệu mặc định vào bảng Gọi món
        create_dataGoiMon( db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TB_NHANVIEN");
        db.execSQL("DROP TABLE IF EXISTS TB_BAN");
        db.execSQL("DROP TABLE IF EXISTS TB_MONAN");
        db.execSQL("DROP TABLE IF EXISTS TB_LOAIMONAN");
        db.execSQL("DROP TABLE IF EXISTS TB_GOIMON");
        db.execSQL("DROP TABLE IF EXISTS TB_CHITIET_GOIMON");
        db.execSQL("DROP TABLE IF EXISTS TB_HOADON");
        onCreate(db);
    }

    //Khởi tạo dữ liệu mặc định nhân viên
    private void create_dataNhanVien(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // Thêm dữ liệu mặt định
        values.put(NV_TENNV, "Thành Phong");
        values.put(NV_TENDN, "phong");
        values.put(NV_MATKHAU, "phong");
        values.put(NV_GIOITINH, "Nam");
        values.put(NV_NGAYSINH, "2003-10-31");
        db.insert(TB_NHANVIEN,null,values);
        values.clear();

        // Thêm dữ liệu mặt định
        values.put(NV_TENNV, "Nhựt Tiến");
        values.put(NV_TENDN, "tien");
        values.put(NV_MATKHAU, "tien");
        values.put(NV_GIOITINH, "Nam");
        values.put(NV_NGAYSINH, "2003-01-01");
        db.insert(TB_NHANVIEN,null,values);

        // Thêm dữ liệu mặt định
        values.put(NV_TENNV, "Phước Tuy");
        values.put(NV_TENDN, "tuy");
        values.put(NV_MATKHAU, "tuy");
        values.put(NV_GIOITINH, "Nam");
        values.put(NV_NGAYSINH, "2003-01-01");
        db.insert(TB_NHANVIEN,null,values);

    }

    // Phương thức để thêm dữ liệu mặc định vào bảng Bàn
    private void create_dataBan(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // Thêm dữ liệu mặc định cho bảng Bàn
        values.put(BAN_TENBAN, "Bàn 1");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null  , values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 2");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 3");
        values.put(BAN_TINHTRNAG, "Đầy");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 4");
        values.put(BAN_TINHTRNAG, "Đầy");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 5");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 6");
        values.put(BAN_TINHTRNAG, "Đầy");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 7");
        values.put(BAN_TINHTRNAG, "Đầy");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 8");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 9");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null, values);
        //
        values.clear();
        values.put(BAN_TENBAN, "Bàn 10");
        values.put(BAN_TINHTRNAG, "Trống");
        db.insert(TB_BAN, null, values);


    }

    // Phương thức để thêm dữ liệu mặc định vào bảng Loại món ăn
    private void create_dataLoaiMonAn(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Thêm dữ liệu mặc định cho bảng Loại món ăn
        values.put(LMA_TENLOAI, "Mỳ cay");
        db.insert(TB_LOAIMONAN, null, values);

        values.clear();
        values.put(LMA_TENLOAI, "Nước Ngọt");
        db.insert(TB_LOAIMONAN, null, values);

        values.clear();
        values.put(LMA_TENLOAI, "Xúc xích");
        db.insert(TB_LOAIMONAN, null, values);


    }
    private void create_dataMonAn(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Thêm dữ liệu mặc định cho bảng Món ăn
        values.put(MA_TENMON, "Mỳ Cay");
        values.put(MA_GIATIEN, 35000); // Giá tiền là VND
        values.put(MA_MALOAI, 1); // Ví dụ, mã loại món phở là 1
        values.put(MA_HINHANH, "mycaybo.jpg"); //
        db.insert(TB_MONAN, null, values);

        values.clear();
        values.put(MA_TENMON, "Nước");
        values.put(MA_GIATIEN, 35000);
        values.put(MA_MALOAI, 2); //
        values.put(MA_HINHANH, "mycayhaisan.jpg");
        db.insert(TB_MONAN, null, values);

        values.clear();
        values.put(MA_TENMON, "Chả Cá");
        values.put(MA_GIATIEN, 20000);
        values.put(MA_MALOAI, 3); // Ví dụ, mã loại món bún bò cũng là 1
        values.put(MA_HINHANH, "mycayhaisan.jpg");
        db.insert(TB_MONAN, null, values);

        values.clear();
        values.put(MA_TENMON, "Hàu");
        values.put(MA_GIATIEN, 15000);
        values.put(MA_MALOAI, 3); // Ví dụ, mã loại món bún bò cũng là 1
        values.put(MA_HINHANH, "mycayhaisan.jpg");
        db.insert(TB_MONAN, null, values);

    }
    private void create_dataGoiMon(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Thêm dữ liệu mặc định cho bảng Gọi món
        values.put(GM_MANV, 1); // Ví dụ, mã nhân viên là 1
        values.put(GM_MABAN, 1); // Ví dụ, mã bàn là 1
        values.put(GM_NGAYGOI, "2024-05-11"); // Ngày gọi món

        long maGoiMon = db.insert(TB_GOIMON, null, values); // Lấy mã gọi món vừa thêm

        // Thêm dữ liệu cho bảng Chi tiết gọi món
        ContentValues ctValues = new ContentValues();
        ctValues.put(CTGM_MAGOIMON, maGoiMon); // Mã gọi món đã thêm
        ctValues.put(CTGM_MAMONAN, 1); // Ví dụ, mã món ăn là 1
        ctValues.put(CTGM_SOLUONG, 2); // Số lượng món

        db.insert(TB_CHITIET_GOIMON, null, ctValues);


    }



    private void create_dataHoaDon(SQLiteDatabase db){
        ContentValues values = new ContentValues();

        // Thêm dữ liệu mặc định cho bảng Hóa đơn
        values.put(HD_NGAYTAO, "2024-05-11"); // Ngày tạo hóa đơn
        values.put(HD_TONGTIEN, 75000); // Tổng tiền hóa đơn
        db.insert(TB_HOADON, null, values);


        // Thêm dữ liệu mặc định cho bảng Hóa đơn
        values.put(HD_NGAYTAO, "2024-05-11"); // Ngày tạo hóa đơn
        values.put(HD_TONGTIEN, 150000); // Tổng tiền hóa đơn
        db.insert(TB_HOADON, null, values);

        // Thêm dữ liệu mặc định cho bảng Hóa đơn
        values.put(HD_NGAYTAO, "2024-05-11"); // Ngày tạo hóa đơn
        values.put(HD_TONGTIEN, 50000); // Tổng tiền hóa đơn
        db.insert(TB_HOADON, null, values);

        // Thêm dữ liệu mặc định cho bảng Hóa đơn
        values.put(HD_NGAYTAO, "2024-05-11"); // Ngày tạo hóa đơn
        values.put(HD_TONGTIEN, 100000); // Tổng tiền hóa đơn
        db.insert(TB_HOADON, null, values);

        // Thêm dữ liệu mặc định cho bảng Hóa đơn
        values.put(HD_NGAYTAO, "2024-05-11"); // Ngày tạo hóa đơn
        values.put(HD_TONGTIEN, 250000); // Tổng tiền hóa đơn
        db.insert(TB_HOADON, null, values);

    }
}
