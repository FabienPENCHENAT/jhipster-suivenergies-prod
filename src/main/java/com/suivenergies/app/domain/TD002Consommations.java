package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD002Consommations.
 */
@Entity
@Table(name = "td_002_consommations")
public class TD002Consommations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_006_type_usage")
    private String tr006TypeUsage;

    @Column(name = "tr_004_typ_energie")
    private String tr004TypEnergie;

    @Column(name = "tv_042_tarif_energie")
    private Double tv042TarifEnergie;

    @Column(name = "consommation_energie_finale")
    private Double consommationEnergieFinale;

    @Column(name = "consommation_energie_primaire")
    private Double consommationEnergiePrimaire;

    @Column(name = "frais_annuels_energie")
    private Double fraisAnnuelsEnergie;

    @Column(name = "est_efface")
    private Boolean estEfface;

    @OneToOne(mappedBy = "td002Consommations")
    @JsonIgnore
    private TD001DPE td001DPE;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTr006TypeUsage() {
        return tr006TypeUsage;
    }

    public TD002Consommations tr006TypeUsage(String tr006TypeUsage) {
        this.tr006TypeUsage = tr006TypeUsage;
        return this;
    }

    public void setTr006TypeUsage(String tr006TypeUsage) {
        this.tr006TypeUsage = tr006TypeUsage;
    }

    public String getTr004TypEnergie() {
        return tr004TypEnergie;
    }

    public TD002Consommations tr004TypEnergie(String tr004TypEnergie) {
        this.tr004TypEnergie = tr004TypEnergie;
        return this;
    }

    public void setTr004TypEnergie(String tr004TypEnergie) {
        this.tr004TypEnergie = tr004TypEnergie;
    }

    public Double getTv042TarifEnergie() {
        return tv042TarifEnergie;
    }

    public TD002Consommations tv042TarifEnergie(Double tv042TarifEnergie) {
        this.tv042TarifEnergie = tv042TarifEnergie;
        return this;
    }

    public void setTv042TarifEnergie(Double tv042TarifEnergie) {
        this.tv042TarifEnergie = tv042TarifEnergie;
    }

    public Double getConsommationEnergieFinale() {
        return consommationEnergieFinale;
    }

    public TD002Consommations consommationEnergieFinale(Double consommationEnergieFinale) {
        this.consommationEnergieFinale = consommationEnergieFinale;
        return this;
    }

    public void setConsommationEnergieFinale(Double consommationEnergieFinale) {
        this.consommationEnergieFinale = consommationEnergieFinale;
    }

    public Double getConsommationEnergiePrimaire() {
        return consommationEnergiePrimaire;
    }

    public TD002Consommations consommationEnergiePrimaire(Double consommationEnergiePrimaire) {
        this.consommationEnergiePrimaire = consommationEnergiePrimaire;
        return this;
    }

    public void setConsommationEnergiePrimaire(Double consommationEnergiePrimaire) {
        this.consommationEnergiePrimaire = consommationEnergiePrimaire;
    }

    public Double getFraisAnnuelsEnergie() {
        return fraisAnnuelsEnergie;
    }

    public TD002Consommations fraisAnnuelsEnergie(Double fraisAnnuelsEnergie) {
        this.fraisAnnuelsEnergie = fraisAnnuelsEnergie;
        return this;
    }

    public void setFraisAnnuelsEnergie(Double fraisAnnuelsEnergie) {
        this.fraisAnnuelsEnergie = fraisAnnuelsEnergie;
    }

    public Boolean isEstEfface() {
        return estEfface;
    }

    public TD002Consommations estEfface(Boolean estEfface) {
        this.estEfface = estEfface;
        return this;
    }

    public void setEstEfface(Boolean estEfface) {
        this.estEfface = estEfface;
    }

    public TD001DPE getTd001DPE() {
        return td001DPE;
    }

    public TD002Consommations td001DPE(TD001DPE tD001DPE) {
        this.td001DPE = tD001DPE;
        return this;
    }

    public void setTd001DPE(TD001DPE tD001DPE) {
        this.td001DPE = tD001DPE;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD002Consommations)) {
            return false;
        }
        return id != null && id.equals(((TD002Consommations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD002Consommations{" +
            "id=" + getId() +
            ", tr006TypeUsage='" + getTr006TypeUsage() + "'" +
            ", tr004TypEnergie='" + getTr004TypEnergie() + "'" +
            ", tv042TarifEnergie=" + getTv042TarifEnergie() +
            ", consommationEnergieFinale=" + getConsommationEnergieFinale() +
            ", consommationEnergiePrimaire=" + getConsommationEnergiePrimaire() +
            ", fraisAnnuelsEnergie=" + getFraisAnnuelsEnergie() +
            ", estEfface='" + isEstEfface() + "'" +
            "}";
    }
}
