package com.suivenergies.app.domain.enumeration;

/**
 * The EnergiesFacture enumeration.
 */
public enum EnergiesFacture {
    ELEC("Electricité (kWh"),
    GAZ("Gaz (kWh"),
    FIOUL("Fioul (l"),
    BOIS("Bois (m3");

    private final String value;


    EnergiesFacture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
