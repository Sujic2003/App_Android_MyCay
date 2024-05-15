package com.example.my_cay_uname;

public class NhanVien {
    public int id;
    public String ten;
    public String tenDN;
    public String matkhau;
    public String gioitinh;
    public String ngaysinh;

    public NhanVien(int id, String ten, String tenDN, String matkhau, String gioitinh, String ngaysinh) {
        this.id = id;
        this.ten = ten;
        this.tenDN = tenDN;
        this.matkhau = matkhau;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
