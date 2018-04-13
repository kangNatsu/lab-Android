package edu.android.lec43_xmlparser02;


/**
 * MODEl 클래스
 * 데이터 정의 클래스
 *
 */
public class Subway {
    private String line;
    private String staitionName;
    private String adres;
    private String rdnmadr;
    private String telNo;

    public Subway(){}

    public Subway(String line, String staitionName, String adres, String rdnmadr, String telNo) {
        this.line = line;
        this.staitionName = staitionName;
        this.adres = adres;
        this.rdnmadr = rdnmadr;
        this.telNo = telNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStaitionName() {
        return staitionName;
    }

    public void setStaitionName(String staitionName) {
        this.staitionName = staitionName;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getRdnmadr() {
        return rdnmadr;
    }

    public void setRdnmadr(String rdnmadr) {
        this.rdnmadr = rdnmadr;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Override
    public String toString() {
        return "Line :: " + line + "Station Name :: " + staitionName +
                "Adress :: "+ adres + "Station Adress :: " + rdnmadr +
                "Station Tell :: "+ telNo;
    }
}
