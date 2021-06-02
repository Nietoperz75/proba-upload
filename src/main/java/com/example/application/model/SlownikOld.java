package com.example.application.model;

public class SlownikOld {

    private Integer pierwszy = -1;
    private Integer drugi =  -1;
    private Integer trzeci=  -1;
    private Integer czwarty=  -1;
    private Integer piaty=  -1;
    private Integer szosty=  -1;

    public SlownikOld() {
    }

    public SlownikOld(Integer pierwszy, Integer drugi, Integer trzeci, Integer czwarty, Integer piaty, Integer szosty) {
        this.pierwszy = pierwszy;
        this.drugi = drugi;
        this.trzeci = trzeci;
        this.czwarty = czwarty;
        this.piaty = piaty;
        this.szosty = szosty;
    }

    public Integer getPierwszy() {
        return pierwszy;
    }

    public void setPierwszy(Integer pierwszy) {
        this.pierwszy = pierwszy;
    }

    public Integer getDrugi() {
        return drugi;
    }

    public void setDrugi(Integer drugi) {
        this.drugi = drugi;
    }

    public Integer getTrzeci() {
        return trzeci;
    }

    public void setTrzeci(Integer trzeci) {
        this.trzeci = trzeci;
    }

    public Integer getCzwarty() {
        return czwarty;
    }

    public void setCzwarty(Integer czwarty) {
        this.czwarty = czwarty;
    }

    public Integer getPiaty() {
        return piaty;
    }

    public void setPiaty(Integer piaty) {
        this.piaty = piaty;
    }

    public Integer getSzosty() {
        return szosty;
    }

    public void setSzosty(Integer szosty) {
        this.szosty = szosty;
    }

    @Override
    public String toString() {
        return "Slownik{" +
                "pierwszy=" + pierwszy +
                ", drugi=" + drugi +
                ", trzeci=" + trzeci +
                ", czwarty=" + czwarty +
                ", piaty=" + piaty +
                ", szosty=" + szosty +
                '}';
    }
}
