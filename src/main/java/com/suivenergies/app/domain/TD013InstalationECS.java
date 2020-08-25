package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD013InstalationECS.
 */
@Entity
@Table(name = "td_013_instalation_ecs")
public class TD013InstalationECS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_005_type_installation_ecs")
    private String tr005TypeInstallationEcs;

    @Column(name = "becs")
    private Double becs;

    @Column(name = "tv_039_formule_becs")
    private String tv039FormuleBecs;

    @Column(name = "surface_alimentee")
    private Double surfaceAlimentee;

    @OneToOne
    @JoinColumn(unique = true)
    private TD014GenerateurECS td014GenerateurECS;

    @OneToOne(mappedBy = "td013InstalationECS")
    @JsonIgnore
    private TD006Batiment td006Batiment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTr005TypeInstallationEcs() {
        return tr005TypeInstallationEcs;
    }

    public TD013InstalationECS tr005TypeInstallationEcs(String tr005TypeInstallationEcs) {
        this.tr005TypeInstallationEcs = tr005TypeInstallationEcs;
        return this;
    }

    public void setTr005TypeInstallationEcs(String tr005TypeInstallationEcs) {
        this.tr005TypeInstallationEcs = tr005TypeInstallationEcs;
    }

    public Double getBecs() {
        return becs;
    }

    public TD013InstalationECS becs(Double becs) {
        this.becs = becs;
        return this;
    }

    public void setBecs(Double becs) {
        this.becs = becs;
    }

    public String getTv039FormuleBecs() {
        return tv039FormuleBecs;
    }

    public TD013InstalationECS tv039FormuleBecs(String tv039FormuleBecs) {
        this.tv039FormuleBecs = tv039FormuleBecs;
        return this;
    }

    public void setTv039FormuleBecs(String tv039FormuleBecs) {
        this.tv039FormuleBecs = tv039FormuleBecs;
    }

    public Double getSurfaceAlimentee() {
        return surfaceAlimentee;
    }

    public TD013InstalationECS surfaceAlimentee(Double surfaceAlimentee) {
        this.surfaceAlimentee = surfaceAlimentee;
        return this;
    }

    public void setSurfaceAlimentee(Double surfaceAlimentee) {
        this.surfaceAlimentee = surfaceAlimentee;
    }

    public TD014GenerateurECS getTd014GenerateurECS() {
        return td014GenerateurECS;
    }

    public TD013InstalationECS td014GenerateurECS(TD014GenerateurECS tD014GenerateurECS) {
        this.td014GenerateurECS = tD014GenerateurECS;
        return this;
    }

    public void setTd014GenerateurECS(TD014GenerateurECS tD014GenerateurECS) {
        this.td014GenerateurECS = tD014GenerateurECS;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD013InstalationECS td006Batiment(TD006Batiment tD006Batiment) {
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
        if (!(o instanceof TD013InstalationECS)) {
            return false;
        }
        return id != null && id.equals(((TD013InstalationECS) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD013InstalationECS{" +
            "id=" + getId() +
            ", tr005TypeInstallationEcs='" + getTr005TypeInstallationEcs() + "'" +
            ", becs=" + getBecs() +
            ", tv039FormuleBecs='" + getTv039FormuleBecs() + "'" +
            ", surfaceAlimentee=" + getSurfaceAlimentee() +
            "}";
    }
}
