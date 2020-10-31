package com.akropoliprishtine.entities.book;
import java.util.Date;

public class RegjistriDTO {
    private int numri;
    private String titulli;
    private String emriAutorit;
    private String kategoria;
    private String shtepiaBotuese;
    private String vitiProdhimit;

    public int getNumri() {
        return numri;
    }

    public void setNumri(int numri) {
        this.numri = numri;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }

    public String getEmriAutorit() {
        return emriAutorit;
    }

    public void setEmriAutorit(String emriAutorit) {
        this.emriAutorit = emriAutorit;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getShtepiaBotuese() {
        return shtepiaBotuese;
    }

    public void setShtepiaBotuese(String shtepiaBotuese) {
        this.shtepiaBotuese = shtepiaBotuese;
    }

    public String getVitiProdhimit() {
        return vitiProdhimit;
    }

    public void setVitiProdhimit(String vitiProdhimit) {
        this.vitiProdhimit = vitiProdhimit;
    }
}