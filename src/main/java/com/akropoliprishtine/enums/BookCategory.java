package com.akropoliprishtine.enums;

public enum BookCategory {
    HISTORI("H"),
    ANGLEZE("A"),
    FILOZOFIK("F"),
    PSIKOLOGJIK("P"),
    BIOGRAFI("B"),
    AKROPOLI("A.R"),
    LETERSI_HUAJ("L.H"),
    LETERSI_SHQIPE("L.SH"),
    MITOLOGJI("M"),
    EF("E.F"),
    K("K");

    public final String label;

    private BookCategory(String label) {
        this.label = label;
    }
}

