package DTO;

public class ChiTietGoiMon {
    private int MAGOIMON;
    private int MANMONAN;
    private int SOLUONG;

    public ChiTietGoiMon(int MAGOIMON, int MANMONAN, int SOLUONG) {
        this.MAGOIMON = MAGOIMON;
        this.MANMONAN = MANMONAN;
        this.SOLUONG = SOLUONG;
    }

    public ChiTietGoiMon() {
    }

    public int getMAGOIMON() {
        return MAGOIMON;
    }

    public void setMAGOIMON(int MAGOIMON) {
        this.MAGOIMON = MAGOIMON;
    }

    public int getMANMONAN() {
        return MANMONAN;
    }

    public void setMANMONAN(int MANMONAN) {
        this.MANMONAN = MANMONAN;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }
}
