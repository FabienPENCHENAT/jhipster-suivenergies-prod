package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD007ParoiOpaque.
 */
@Entity
@Table(name = "td_007_paroi_opaque")
public class TD007ParoiOpaque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_014_type_parois_opaque")
    private String tr014TypeParoisOpaque;

    @Column(name = "reference")
    private String reference;

    @Column(name = "deperdition_thermique")
    private Double deperditionThermique;

    @Column(name = "coefficient_transmission_thermique_paroi")
    private Double coefficientTransmissionThermiqueParoi;

    @Column(name = "resistance_thermique_isolation")
    private Double resistanceThermiqueIsolation;

    @Column(name = "epaisseur_isolation")
    private Double epaisseurIsolation;

    @Column(name = "surface_paroi")
    private Double surfaceParoi;

    @OneToOne
    @JoinColumn(unique = true)
    private TD008Baie td008Baie;

    @OneToOne(mappedBy = "td007ParoiOpaque")
    @JsonIgnore
    private TD006Batiment td006Batiment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTr014TypeParoisOpaque() {
        return tr014TypeParoisOpaque;
    }

    public TD007ParoiOpaque tr014TypeParoisOpaque(String tr014TypeParoisOpaque) {
        this.tr014TypeParoisOpaque = tr014TypeParoisOpaque;
        return this;
    }

    public void setTr014TypeParoisOpaque(String tr014TypeParoisOpaque) {
        this.tr014TypeParoisOpaque = tr014TypeParoisOpaque;
    }

    public String getReference() {
        return reference;
    }

    public TD007ParoiOpaque reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getDeperditionThermique() {
        return deperditionThermique;
    }

    public TD007ParoiOpaque deperditionThermique(Double deperditionThermique) {
        this.deperditionThermique = deperditionThermique;
        return this;
    }

    public void setDeperditionThermique(Double deperditionThermique) {
        this.deperditionThermique = deperditionThermique;
    }

    public Double getCoefficientTransmissionThermiqueParoi() {
        return coefficientTransmissionThermiqueParoi;
    }

    public TD007ParoiOpaque coefficientTransmissionThermiqueParoi(Double coefficientTransmissionThermiqueParoi) {
        this.coefficientTransmissionThermiqueParoi = coefficientTransmissionThermiqueParoi;
        return this;
    }

    public void setCoefficientTransmissionThermiqueParoi(Double coefficientTransmissionThermiqueParoi) {
        this.coefficientTransmissionThermiqueParoi = coefficientTransmissionThermiqueParoi;
    }

    public Double getResistanceThermiqueIsolation() {
        return resistanceThermiqueIsolation;
    }

    public TD007ParoiOpaque resistanceThermiqueIsolation(Double resistanceThermiqueIsolation) {
        this.resistanceThermiqueIsolation = resistanceThermiqueIsolation;
        return this;
    }

    public void setResistanceThermiqueIsolation(Double resistanceThermiqueIsolation) {
        this.resistanceThermiqueIsolation = resistanceThermiqueIsolation;
    }

    public Double getEpaisseurIsolation() {
        return epaisseurIsolation;
    }

    public TD007ParoiOpaque epaisseurIsolation(Double epaisseurIsolation) {
        this.epaisseurIsolation = epaisseurIsolation;
        return this;
    }

    public void setEpaisseurIsolation(Double epaisseurIsolation) {
        this.epaisseurIsolation = epaisseurIsolation;
    }

    public Double getSurfaceParoi() {
        return surfaceParoi;
    }

    public TD007ParoiOpaque surfaceParoi(Double surfaceParoi) {
        this.surfaceParoi = surfaceParoi;
        return this;
    }

    public void setSurfaceParoi(Double surfaceParoi) {
        this.surfaceParoi = surfaceParoi;
    }

    public TD008Baie getTd008Baie() {
        return td008Baie;
    }

    public TD007ParoiOpaque td008Baie(TD008Baie tD008Baie) {
        this.td008Baie = tD008Baie;
        return this;
    }

    public void setTd008Baie(TD008Baie tD008Baie) {
        this.td008Baie = tD008Baie;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD007ParoiOpaque td006Batiment(TD006Batiment tD006Batiment) {
        this.td006Batiment = tD006Batiment;
        return this;
    }

    public void setTd006Batiment(TD006Batiment tD006Batiment) {
        this.td006Batiment = tD006Batiment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD007ParoiOpaque)) {
            return false;
        }
        return id != null && id.equals(((TD007ParoiOpaque) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD007ParoiOpaque{" +
            "id=" + getId() +
            ", tr014TypeParoisOpaque='" + getTr014TypeParoisOpaque() + "'" +
            ", reference='" + getReference() + "'" +
            ", deperditionThermique=" + getDeperditionThermique() +
            ", coefficientTransmissionThermiqueParoi=" + getCoefficientTransmissionThermiqueParoi() +
            ", resistanceThermiqueIsolation=" + getResistanceThermiqueIsolation() +
            ", epaisseurIsolation=" + getEpaisseurIsolation() +
            ", surfaceParoi=" + getSurfaceParoi() +
            "}";
    }
}
