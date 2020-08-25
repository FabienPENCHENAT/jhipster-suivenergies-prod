package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD008Baie.
 */
@Entity
@Table(name = "td_008_baie")
public class TD008Baie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "td_008_baie")
    private String td008Baie;

    @Column(name = "deperdition")
    private Double deperdition;

    @Column(name = "tv_009_coefficient_transmission_thermique_vitrage")
    private Double tv009CoefficientTransmissionThermiqueVitrage;

    @Column(name = "tv_012_coef_transmission_thermique_baie_protection_solaire")
    private Double tv012CoefTransmissionThermiqueBaieProtectionSolaire;

    @Column(name = "surface")
    private Double surface;

    @Column(name = "perimetre")
    private Double perimetre;

    @Column(name = "tv_013_valeur_pont_thermique")
    private Double tv013ValeurPontThermique;

    @OneToOne(mappedBy = "td008Baie")
    @JsonIgnore
    private TD007ParoiOpaque td007ParoiOpaque;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public TD008Baie reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTd008Baie() {
        return td008Baie;
    }

    public TD008Baie td008Baie(String td008Baie) {
        this.td008Baie = td008Baie;
        return this;
    }

    public void setTd008Baie(String td008Baie) {
        this.td008Baie = td008Baie;
    }

    public Double getDeperdition() {
        return deperdition;
    }

    public TD008Baie deperdition(Double deperdition) {
        this.deperdition = deperdition;
        return this;
    }

    public void setDeperdition(Double deperdition) {
        this.deperdition = deperdition;
    }

    public Double getTv009CoefficientTransmissionThermiqueVitrage() {
        return tv009CoefficientTransmissionThermiqueVitrage;
    }

    public TD008Baie tv009CoefficientTransmissionThermiqueVitrage(Double tv009CoefficientTransmissionThermiqueVitrage) {
        this.tv009CoefficientTransmissionThermiqueVitrage = tv009CoefficientTransmissionThermiqueVitrage;
        return this;
    }

    public void setTv009CoefficientTransmissionThermiqueVitrage(Double tv009CoefficientTransmissionThermiqueVitrage) {
        this.tv009CoefficientTransmissionThermiqueVitrage = tv009CoefficientTransmissionThermiqueVitrage;
    }

    public Double getTv012CoefTransmissionThermiqueBaieProtectionSolaire() {
        return tv012CoefTransmissionThermiqueBaieProtectionSolaire;
    }

    public TD008Baie tv012CoefTransmissionThermiqueBaieProtectionSolaire(Double tv012CoefTransmissionThermiqueBaieProtectionSolaire) {
        this.tv012CoefTransmissionThermiqueBaieProtectionSolaire = tv012CoefTransmissionThermiqueBaieProtectionSolaire;
        return this;
    }

    public void setTv012CoefTransmissionThermiqueBaieProtectionSolaire(Double tv012CoefTransmissionThermiqueBaieProtectionSolaire) {
        this.tv012CoefTransmissionThermiqueBaieProtectionSolaire = tv012CoefTransmissionThermiqueBaieProtectionSolaire;
    }

    public Double getSurface() {
        return surface;
    }

    public TD008Baie surface(Double surface) {
        this.surface = surface;
        return this;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPerimetre() {
        return perimetre;
    }

    public TD008Baie perimetre(Double perimetre) {
        this.perimetre = perimetre;
        return this;
    }

    public void setPerimetre(Double perimetre) {
        this.perimetre = perimetre;
    }

    public Double getTv013ValeurPontThermique() {
        return tv013ValeurPontThermique;
    }

    public TD008Baie tv013ValeurPontThermique(Double tv013ValeurPontThermique) {
        this.tv013ValeurPontThermique = tv013ValeurPontThermique;
        return this;
    }

    public void setTv013ValeurPontThermique(Double tv013ValeurPontThermique) {
        this.tv013ValeurPontThermique = tv013ValeurPontThermique;
    }

    public TD007ParoiOpaque getTd007ParoiOpaque() {
        return td007ParoiOpaque;
    }

    public TD008Baie td007ParoiOpaque(TD007ParoiOpaque tD007ParoiOpaque) {
        this.td007ParoiOpaque = tD007ParoiOpaque;
        return this;
    }

    public void setTd007ParoiOpaque(TD007ParoiOpaque tD007ParoiOpaque) {
        this.td007ParoiOpaque = tD007ParoiOpaque;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD008Baie)) {
            return false;
        }
        return id != null && id.equals(((TD008Baie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD008Baie{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", td008Baie='" + getTd008Baie() + "'" +
            ", deperdition=" + getDeperdition() +
            ", tv009CoefficientTransmissionThermiqueVitrage=" + getTv009CoefficientTransmissionThermiqueVitrage() +
            ", tv012CoefTransmissionThermiqueBaieProtectionSolaire=" + getTv012CoefTransmissionThermiqueBaieProtectionSolaire() +
            ", surface=" + getSurface() +
            ", perimetre=" + getPerimetre() +
            ", tv013ValeurPontThermique=" + getTv013ValeurPontThermique() +
            "}";
    }
}
