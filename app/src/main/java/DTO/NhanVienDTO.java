package DTO;

public class NhanVienDTO {
    private int MANV;
    private String TENNV;
    private String TENDN;
    private String MATKHAU;
    private String GIOITINH;
    private String NGAYSINH;

    // Hàm khởi tạo không tham số
    public NhanVienDTO() {
    }

    // Hàm khởi tạo có tham số
    public NhanVienDTO(int MANV, String TENNV, String TENDN, String MATKHAU, String GIOITINH, String NGAYSINH) {
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.TENDN = TENDN;
        this.MATKHAU = MATKHAU;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }

    public String getTENDN() {
        return TENDN;
    }

    public void setTENDN(String TENDN) {
        this.TENDN = TENDN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }
}
