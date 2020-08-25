package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD011InstalationChauffage.
 */
@Entity
@Table(name = "td_011_instalation_chauffage")
public class TD011InstalationChauffage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_003_type_installation_chauffage")
    private String tr003TypeInstallationChauffage;

    @Column(name = "surface_chauffee")
    private Double surfaceChauffee;

    @OneToOne
    @JoinColumn(unique = true)
    private TD012GenerateurChauffage td012GenerateurChauffage;

    @OneToOne(mappedBy = "td011InstalationChauffage")
    @JsonIgnore
    private TD006Batiment td006Batiment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTr003TypeInstallationChauffage() {
        return tr003TypeInstallationChauffage;
    }

    public TD011InstalationChauffage tr003TypeInstallationChauffage(String tr003TypeInstallationChauffage) {
        this.tr003TypeInstallationChauffage = tr003TypeInstallationChauffage;
        return this;
    }

    public void setTr003TypeInstallationChauffage(String tr003TypeInstallationChauffage) {
        this.tr003TypeInstallationChauffage = tr003TypeInstallationChauffage;
    }

    public Double getSurfaceChauffee() {
        return surfaceChauffee;
    }

    public TD011InstalationChauffage surfaceChauffee(Double surfaceChauffee) {
        this.surfaceChauffee = surfaceChauffee;
        return this;
    }

    public void setSurfaceChauffee(Double surfaceChauffee) {
        this.surfaceChauffee = surfaceChauffee;
    }

    public TD012GenerateurChauffage getTd012GenerateurChauffage() {
        return td012GenerateurChauffage;
    }

    public TD011InstalationChauffage td012GenerateurChauffage(TD012GenerateurChauffage tD012GenerateurChauffage) {
        this.td012GenerateurChauffage = tD012GenerateurChauffage;
        return this;
    }

    public void setTd012GenerateurChauffage(TD012GenerateurChauffage tD012GenerateurChauffage) {
        this.td012GenerateurChauffage = tD012GenerateurChauffage;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD011InstalationChauffage td006Batiment(TD006Batiment tD006Batiment) {
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
        if (!(o instanceof TD011InstalationChauffage)) {
            return false;
        }
        return id != null && id.equals(((TD011InstalationChauffage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD011InstalationChauffage{" +
            "id=" + getId() +
            ", tr003TypeInstallationChauffage='" + getTr003TypeInstallationChauffage() + "'" +
            ", surfaceChauffee=" + getSurfaceChauffee() +
            "}";
    }
}
