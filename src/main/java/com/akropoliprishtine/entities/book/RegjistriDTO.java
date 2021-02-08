package com.akropoliprishtine.entities.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegjistriDTO {
    private int numri;
    private String titulli;
    private String emriAutorit;
    private String kategoria;
    private String shtepiaBotuese;
    private String vitiProdhimit;
}