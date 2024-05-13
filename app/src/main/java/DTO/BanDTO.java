package DTO;

public class BanDTO {
    private  int MABAN;
    private String TINHTRANG;

    public BanDTO(int MABAN, String TINHTRANG) {
        this.MABAN = MABAN;
        this.TINHTRANG = TINHTRANG;
    }

    public BanDTO() {
    }

    public int getMABAN() {
        return MABAN;
    }

    public void setMABAN(int MABAN) {
        this.MABAN = MABAN;
    }

    public String getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(String TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }
}
