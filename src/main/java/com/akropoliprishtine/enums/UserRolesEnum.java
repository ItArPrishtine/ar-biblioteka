package com.akropoliprishtine.enums;

public enum UserRolesEnum {
    PG_BIBLIOTEKA("PG tek Biblioteka"),
    ND_BIBLIOTEKA("PG tek Biblioteka"),
    PG_EKONOMIA("PG tek Ekonomia"),
    ND_EKONOMIA("ND tek Ekonomia"),
    PGS_PISHTARI("PGS PISHTARI"),
    ADMIN("ADMIN"),
    KF("KF"),
    KK("KK");


    public final String label;

    private UserRolesEnum(String label) {
        this.label = label;
    }
}



