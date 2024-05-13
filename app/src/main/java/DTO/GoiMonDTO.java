package DTO;

public class GoiMonDTO {
    private int MAGOIMON;
    private int MANV;
    private String NGAYGOI;
    private int MABAN;

    public GoiMonDTO() {
    }

    public GoiMonDTO(int MAGOIMON, int MANV, String NGAYGOI, int MABAN) {
        this.MAGOIMON = MAGOIMON;
        this.MANV = MANV;
        this.NGAYGOI = NGAYGOI;
        this.MABAN = MABAN;
    }

    public int getMAGOIMON() {
        return MAGOIMON;
    }

    public void setMAGOIMON(int MAGOIMON) {
        this.MAGOIMON = MAGOIMON;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getNGAYGOI() {
        return NGAYGOI;
    }

    public void setNGAYGOI(String NGAYGOI) {
        this.NGAYGOI = NGAYGOI;
    }

    public int getMABAN() {
        return MABAN;
    }

    public void setMABAN(int MABAN) {
        this.MABAN = MABAN;
    }
}
