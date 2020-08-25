package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD012GenerateurChauffage.
 */
@Entity
@Table(name = "td_012_generateur_chauffage")
public class TD012GenerateurChauffage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "systeme_chauffage_cogeneration")
    private String systemeChauffageCogeneration;

    @Column(name = "td_011_installation_chauffage")
    private String td011InstallationChauffage;

    @Column(name = "tr_004_type_energie")
    private String tr004TypeEnergie;

    @Column(name = "consommation_chauffage")
    private Double consommationChauffage;

    @OneToOne(mappedBy = "td012GenerateurChauffage")
    @JsonIgnore
    private TD011InstalationChauffage td011InstalationChauffage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemeChauffageCogeneration() {
        return systemeChauffageCogeneration;
    }

    public TD012GenerateurChauffage systemeChauffageCogeneration(String systemeChauffageCogeneration) {
        this.systemeChauffageCogeneration = systemeChauffageCogeneration;
        return this;
    }

    public void setSystemeChauffageCogeneration(String systemeChauffageCogeneration) {
        this.systemeChauffageCogeneration = systemeChauffageCogeneration;
    }

    public String getTd011InstallationChauffage() {
        return td011InstallationChauffage;
    }

    public TD012GenerateurChauffage td011InstallationChauffage(String td011InstallationChauffage) {
        this.td011InstallationChauffage = td011InstallationChauffage;
        return this;
    }

    public void setTd011InstallationChauffage(String td011InstallationChauffage) {
        this.td011InstallationChauffage = td011InstallationChauffage;
    }

    public String getTr004TypeEnergie() {
        return tr004TypeEnergie;
    }

    public TD012GenerateurChauffage tr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
        return this;
    }

    public void setTr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
    }

    public Double getConsommationChauffage() {
        return consommationChauffage;
    }

    public TD012GenerateurChauffage consommationChauffage(Double consommationChauffage) {
        this.consommationChauffage = consommationChauffage;
        return this;
    }

    public void setConsommationChauffage(Double consommationChauffage) {
        this.consommationChauffage = consommationChauffage;
    }

    public TD011InstalationChauffage getTd011InstalationChauffage() {
        return td011InstalationChauffage;
    }

    public TD012GenerateurChauffage td011InstalationChauffage(TD011InstalationChauffage tD011InstalationChauffage) {
        this.td011InstalationChauffage = tD011InstalationChauffage;
        return this;
    }

    public void setTd011InstalationChauffage(TD011InstalationChauffage tD011InstalationChauffage) {
        this.td011InstalationChauffage = tD011InstalationChauffage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD012GenerateurChauffage)) {
            return false;
        }
        return id != null && id.equals(((TD012GenerateurChauffage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD012GenerateurChauffage{" +
            "id=" + getId() +
            ", systemeChauffageCogeneration='" + getSystemeChauffageCogeneration() + "'" +
            ", td011InstallationChauffage='" + getTd011InstallationChauffage() + "'" +
            ", tr004TypeEnergie='" + getTr004TypeEnergie() + "'" +
            ", consommationChauffage=" + getConsommationChauffage() +
            "}";
    }
}
