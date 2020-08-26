package com.suivenergies.app.domain;

import com.suivenergies.app.domain.enumeration.EnergiesFacture;
import java.io.Serializable;
import java.util.List;

public class FacturesByType implements Serializable {
    private EnergiesFacture type;
    private List<Facture> factures;

    public FacturesByType(EnergiesFacture type, List<Facture> factures) {
        this.type = type;
        this.factures = factures;
    }

    /**
     * @return the type
     */
    public EnergiesFacture getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EnergiesFacture type) {
        this.type = type;
    }

    /**
     * @return the factures
     */
    public List<Facture> getFactures() {
        return factures;
    }

    /**
     * @param factures the factures to set
     */
    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
}
