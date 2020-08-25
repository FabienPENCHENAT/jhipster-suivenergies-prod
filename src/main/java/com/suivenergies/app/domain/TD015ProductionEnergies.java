package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD015ProductionEnergies.
 */
@Entity
@Table(name = "td_015_production_energies")
public class TD015ProductionEnergies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_004_type_energie")
    private String tr004TypeEnergie;

    @Column(name = "production_electricite_capteurs_photovoltaiques")
    private Double productionElectriciteCapteursPhotovoltaiques;

    @Column(name = "productionle_ectricite_micro_eolienne")
    private Boolean productionleEctriciteMicroEolienne;

    @Column(name = "production_cogeneration")
    private Double productionCogeneration;

    @OneToOne(mappedBy = "td015ProductionEnergies")
    @JsonIgnore
    private TD006Batiment td006Batiment;

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

    public TD015ProductionEnergies tr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
        return this;
    }

    public void setTr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
    }

    public Double getProductionElectriciteCapteursPhotovoltaiques() {
        return productionElectriciteCapteursPhotovoltaiques;
    }

    public TD015ProductionEnergies productionElectriciteCapteursPhotovoltaiques(Double productionElectriciteCapteursPhotovoltaiques) {
        this.productionElectriciteCapteursPhotovoltaiques = productionElectriciteCapteursPhotovoltaiques;
        return this;
    }

    public void setProductionElectriciteCapteursPhotovoltaiques(Double productionElectriciteCapteursPhotovoltaiques) {
        this.productionElectriciteCapteursPhotovoltaiques = productionElectriciteCapteursPhotovoltaiques;
    }

    public Boolean isProductionleEctriciteMicroEolienne() {
        return productionleEctriciteMicroEolienne;
    }

    public TD015ProductionEnergies productionleEctriciteMicroEolienne(Boolean productionleEctriciteMicroEolienne) {
        this.productionleEctriciteMicroEolienne = productionleEctriciteMicroEolienne;
        return this;
    }

    public void setProductionleEctriciteMicroEolienne(Boolean productionleEctriciteMicroEolienne) {
        this.productionleEctriciteMicroEolienne = productionleEctriciteMicroEolienne;
    }

    public Double getProductionCogeneration() {
        return productionCogeneration;
    }

    public TD015ProductionEnergies productionCogeneration(Double productionCogeneration) {
        this.productionCogeneration = productionCogeneration;
        return this;
    }

    public void setProductionCogeneration(Double productionCogeneration) {
        this.productionCogeneration = productionCogeneration;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD015ProductionEnergies td006Batiment(TD006Batiment tD006Batiment) {
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
        if (!(o instanceof TD015ProductionEnergies)) {
            return false;
        }
        return id != null && id.equals(((TD015ProductionEnergies) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD015ProductionEnergies{" +
            "id=" + getId() +
            ", tr004TypeEnergie='" + getTr004TypeEnergie() + "'" +
            ", productionElectriciteCapteursPhotovoltaiques=" + getProductionElectriciteCapteursPhotovoltaiques() +
            ", productionleEctriciteMicroEolienne='" + isProductionleEctriciteMicroEolienne() + "'" +
            ", productionCogeneration=" + getProductionCogeneration() +
            "}";
    }
}
