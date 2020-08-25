package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD014GenerateurECS.
 */
@Entity
@Table(name = "td_014_generateur_ecs")
public class TD014GenerateurECS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "td_013_installation_ecs")
    private String td013InstallationEcs;

    @Column(name = "tr_004_type_energie")
    private String tr004TypeEnergie;

    @Column(name = "volume_stockage")
    private Double volumeStockage;

    @OneToOne(mappedBy = "td014GenerateurECS")
    @JsonIgnore
    private TD013InstalationECS td013InstalationECS;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTd013InstallationEcs() {
        return td013InstallationEcs;
    }

    public TD014GenerateurECS td013InstallationEcs(String td013InstallationEcs) {
        this.td013InstallationEcs = td013InstallationEcs;
        return this;
    }

    public void setTd013InstallationEcs(String td013InstallationEcs) {
        this.td013InstallationEcs = td013InstallationEcs;
    }

    public String getTr004TypeEnergie() {
        return tr004TypeEnergie;
    }

    public TD014GenerateurECS tr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
        return this;
    }

    public void setTr004TypeEnergie(String tr004TypeEnergie) {
        this.tr004TypeEnergie = tr004TypeEnergie;
    }

    public Double getVolumeStockage() {
        return volumeStockage;
    }

    public TD014GenerateurECS volumeStockage(Double volumeStockage) {
        this.volumeStockage = volumeStockage;
        return this;
    }

    public void setVolumeStockage(Double volumeStockage) {
        this.volumeStockage = volumeStockage;
    }

    public TD013InstalationECS getTd013InstalationECS() {
        return td013InstalationECS;
    }

    public TD014GenerateurECS td013InstalationECS(TD013InstalationECS tD013InstalationECS) {
        this.td013InstalationECS = tD013InstalationECS;
        return this;
    }

    public void setTd013InstalationECS(TD013InstalationECS tD013InstalationECS) {
        this.td013InstalationECS = tD013InstalationECS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD014GenerateurECS)) {
            return false;
        }
        return id != null && id.equals(((TD014GenerateurECS) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD014GenerateurECS{" +
            "id=" + getId() +
            ", td013InstallationEcs='" + getTd013InstallationEcs() + "'" +
            ", tr004TypeEnergie='" + getTr004TypeEnergie() + "'" +
            ", volumeStockage=" + getVolumeStockage() +
            "}";
    }
}
