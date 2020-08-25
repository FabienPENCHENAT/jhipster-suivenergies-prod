package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD017ConsommationNeuf.
 */
@Entity
@Table(name = "td_017_consommation_neuf")
public class TD017ConsommationNeuf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_004_type_energie")
    private String tr004TypeEnergie;

    @Column(name = "tr_006_type_usage")
    private String tr006TypeUsage;

    @Column(name = "tv_044_conversion_kwh_energies_relevees")
    private String tv044ConversionKwhEnergiesRelevees;

    @Column(name = "tv_045_conversion_kwh_co_2")
    private String tv045ConversionKwhCo2;

    @Column(name = "tv_046_evaluation_contenu_co_2_reseaux")
    private String tv046EvaluationContenuCo2Reseaux;

    @Column(name = "consommation_energie_finale")
    private Double consommationEnergieFinale;

    @Column(name = "consommation_energie_primaire")
    private Double consommationEnergiePrimaire;

    @Column(name = "frais_annuels_energie")
    private Double fraisAnnuelsEnergie;

    @OneToOne(mappedBy = "td017ConsommationNeuf")
    @JsonIgnore
    private TD001DPE td001DPE;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTr004TypeEnergie() {
        return tr004TypeEnergie;
    }

    public TD017ConsommationNeuf tr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
        return this;
    }

    public void setTr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
    }

    public String getTr006TypeUsage() {
        return tr006TypeUsage;
    }

    public TD017ConsommationNeuf tr006TypeUsage(String tr006TypeUsage) {
        this.tr006TypeUsage = tr006TypeUsage;
        return this;
    }

    public void setTr006TypeUsage(String tr006TypeUsage) {
        this.tr006TypeUsage = tr006TypeUsage;
    }

    public String getTv044ConversionKwhEnergiesRelevees() {
        return tv044ConversionKwhEnergiesRelevees;
    }

    public TD017ConsommationNeuf tv044ConversionKwhEnergiesRelevees(String tv044ConversionKwhEnergiesRelevees) {
        this.tv044ConversionKwhEnergiesRelevees = tv044ConversionKwhEnergiesRelevees;
        return this;
    }

    public void setTv044ConversionKwhEnergiesRelevees(String tv044ConversionKwhEnergiesRelevees) {
        this.tv044ConversionKwhEnergiesRelevees = tv044ConversionKwhEnergiesRelevees;
    }

    public String getTv045ConversionKwhCo2() {
        return tv045ConversionKwhCo2;
    }

    public TD017ConsommationNeuf tv045ConversionKwhCo2(String tv045ConversionKwhCo2) {
        this.tv045ConversionKwhCo2 = tv045ConversionKwhCo2;
        return this;
    }

    public void setTv045ConversionKwhCo2(String tv045ConversionKwhCo2) {
        this.tv045ConversionKwhCo2 = tv045ConversionKwhCo2;
    }

    public String getTv046EvaluationContenuCo2Reseaux() {
        return tv046EvaluationContenuCo2Reseaux;
    }

    public TD017ConsommationNeuf tv046EvaluationContenuCo2Reseaux(String tv046EvaluationContenuCo2Reseaux) {
        this.tv046EvaluationContenuCo2Reseaux = tv046EvaluationContenuCo2Reseaux;
        return this;
    }

    public void setTv046EvaluationContenuCo2Reseaux(String tv046EvaluationContenuCo2Reseaux) {
        this.tv046EvaluationContenuCo2Reseaux = tv046EvaluationContenuCo2Reseaux;
    }

    public Double getConsommationEnergieFinale() {
        return consommationEnergieFinale;
    }

    public TD017ConsommationNeuf consommationEnergieFinale(Double consommationEnergieFinale) {
        this.consommationEnergieFinale = consommationEnergieFinale;
        return this;
    }

    public void setConsommationEnergieFinale(Double consommationEnergieFinale) {
        this.consommationEnergieFinale = consommationEnergieFinale;
    }

    public Double getConsommationEnergiePrimaire() {
        return consommationEnergiePrimaire;
    }

    public TD017ConsommationNeuf consommationEnergiePrimaire(Double consommationEnergiePrimaire) {
        this.consommationEnergiePrimaire = consommationEnergiePrimaire;
        return this;
    }

    public void setConsommationEnergiePrimaire(Double consommationEnergiePrimaire) {
        this.consommationEnergiePrimaire = consommationEnergiePrimaire;
    }

    public Double getFraisAnnuelsEnergie() {
        return fraisAnnuelsEnergie;
    }

    public TD017ConsommationNeuf fraisAnnuelsEnergie(Double fraisAnnuelsEnergie) {
        this.fraisAnnuelsEnergie = fraisAnnuelsEnergie;
        return this;
    }

    public void setFraisAnnuelsEnergie(Double fraisAnnuelsEnergie) {
        this.fraisAnnuelsEnergie = fraisAnnuelsEnergie;
    }

    public TD001DPE getTd001DPE() {
        return td001DPE;
    }

    public TD017ConsommationNeuf td001DPE(TD001DPE tD001DPE) {
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
        if (!(o instanceof TD017ConsommationNeuf)) {
            return false;
        }
        return id != null && id.equals(((TD017ConsommationNeuf) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD017ConsommationNeuf{" +
            "id=" + getId() +
            ", tr004TypeEnergie='" + getTr004TypeEnergie() + "'" +
            ", tr006TypeUsage='" + getTr006TypeUsage() + "'" +
            ", tv044ConversionKwhEnergiesRelevees='" + getTv044ConversionKwhEnergiesRelevees() + "'" +
            ", tv045ConversionKwhCo2='" + getTv045ConversionKwhCo2() + "'" +
            ", tv046EvaluationContenuCo2Reseaux='" + getTv046EvaluationContenuCo2Reseaux() + "'" +
            ", consommationEnergieFinale=" + getConsommationEnergieFinale() +
            ", consommationEnergiePrimaire=" + getConsommationEnergiePrimaire() +
            ", fraisAnnuelsEnergie=" + getFraisAnnuelsEnergie() +
            "}";
    }
}
