package DTO;

public class HoaDonDTO {
    private int MAHOADON;
    private String NGAYTAO;
    private int TONGTIEN;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int MAHOADON, String NGAYTAO, int TONGTIEN) {
        this.MAHOADON = MAHOADON;
        this.NGAYTAO = NGAYTAO;
        this.TONGTIEN = TONGTIEN;
    }

    public int getMAHOADON() {
        return MAHOADON;
    }

    public void setMAHOADON(int MAHOADON) {
        this.MAHOADON = MAHOADON;
    }

    public String getNGAYTAO() {
        return NGAYTAO;
    }

    public void setNGAYTAO(String NGAYTAO) {
        this.NGAYTAO = NGAYTAO;
    }

    public int getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(int TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }
}
