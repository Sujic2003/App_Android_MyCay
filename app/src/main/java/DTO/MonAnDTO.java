package DTO;

public class MonAnDTO {
    private int MAMONAN;
    private String TENMON;
    private int GIATIEN;
    private int MALOAI;
    private String HINHANH;

    public MonAnDTO(int MAMONAN, String TENMON, int GIATIEN, int MALOAI, String HINHANH) {
        this.MAMONAN = MAMONAN;
        this.TENMON = TENMON;
        this.GIATIEN = GIATIEN;
        this.MALOAI = MALOAI;
        this.HINHANH = HINHANH;
    }

    public MonAnDTO() {
    }

    public int getMAMONAN() {
        return MAMONAN;
    }

    public void setMAMONAN(int MAMONAN) {
        this.MAMONAN = MAMONAN;
    }

    public String getTENMON() {
        return TENMON;
    }

    public void setTENMON(String TENMON) {
        this.TENMON = TENMON;
    }

    public int getGIATIEN() {
        return GIATIEN;
    }

    public void setGIATIEN(int GIATIEN) {
        this.GIATIEN = GIATIEN;
    }

    public int getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(int MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
}
