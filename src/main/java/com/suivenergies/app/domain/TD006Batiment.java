package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD006Batiment.
 */
@Entity
@Table(name = "td_006_batiment")
public class TD006Batiment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "besoin_chauffage")
    private Double besoinChauffage;

    @Column(name = "deperdition_enveloppe")
    private Double deperditionEnveloppe;

    @Column(name = "deperdition_renouvellement_air")
    private Double deperditionRenouvellementAir;

    @Column(name = "altitude")
    private Double altitude;

    @Column(name = "nombre_niveau")
    private Double nombreNiveau;

    @Column(name = "hsp_moyenne")
    private Double hspMoyenne;

    @OneToOne
    @JoinColumn(unique = true)
    private TD007ParoiOpaque td007ParoiOpaque;

    @OneToOne
    @JoinColumn(unique = true)
    private TD010PontsThermiques td010PontsThermiques;

    @OneToOne
    @JoinColumn(unique = true)
    private TD011InstalationChauffage td011InstalationChauffage;

    @OneToOne
    @JoinColumn(unique = true)
    private TD013InstalationECS td013InstalationECS;

    @OneToOne
    @JoinColumn(unique = true)
    private TD015ProductionEnergies td015ProductionEnergies;

    @OneToOne(mappedBy = "td006Batiment")
    @JsonIgnore
    private TD001DPE td001DPE;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBesoinChauffage() {
        return besoinChauffage;
    }

    public TD006Batiment besoinChauffage(Double besoinChauffage) {
        this.besoinChauffage = besoinChauffage;
        return this;
    }

    public void setBesoinChauffage(Double besoinChauffage) {
        this.besoinChauffage = besoinChauffage;
    }

    public Double getDeperditionEnveloppe() {
        return deperditionEnveloppe;
    }

    public TD006Batiment deperditionEnveloppe(Double deperditionEnveloppe) {
        this.deperditionEnveloppe = deperditionEnveloppe;
        return this;
    }

    public void setDeperditionEnveloppe(Double deperditionEnveloppe) {
        this.deperditionEnveloppe = deperditionEnveloppe;
    }

    public Double getDeperditionRenouvellementAir() {
        return deperditionRenouvellementAir;
    }

    public TD006Batiment deperditionRenouvellementAir(Double deperditionRenouvellementAir) {
        this.deperditionRenouvellementAir = deperditionRenouvellementAir;
        return this;
    }

    public void setDeperditionRenouvellementAir(Double deperditionRenouvellementAir) {
        this.deperditionRenouvellementAir = deperditionRenouvellementAir;
    }

    public Double getAltitude() {
        return altitude;
    }

    public TD006Batiment altitude(Double altitude) {
        this.altitude = altitude;
        return this;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getNombreNiveau() {
        return nombreNiveau;
    }

    public TD006Batiment nombreNiveau(Double nombreNiveau) {
        this.nombreNiveau = nombreNiveau;
        return this;
    }

    public void setNombreNiveau(Double nombreNiveau) {
        this.nombreNiveau = nombreNiveau;
    }

    public Double getHspMoyenne() {
        return hspMoyenne;
    }

    public TD006Batiment hspMoyenne(Double hspMoyenne) {
        this.hspMoyenne = hspMoyenne;
        return this;
    }

    public void setHspMoyenne(Double hspMoyenne) {
        this.hspMoyenne = hspMoyenne;
    }

    public TD007ParoiOpaque getTd007ParoiOpaque() {
        return td007ParoiOpaque;
    }

    public TD006Batiment td007ParoiOpaque(TD007ParoiOpaque tD007ParoiOpaque) {
        this.td007ParoiOpaque = tD007ParoiOpaque;
        return this;
    }

    public void setTd007ParoiOpaque(TD007ParoiOpaque tD007ParoiOpaque) {
        this.td007ParoiOpaque = tD007ParoiOpaque;
    }

    public TD010PontsThermiques getTd010PontsThermiques() {
        return td010PontsThermiques;
    }

    public TD006Batiment td010PontsThermiques(TD010PontsThermiques tD010PontsThermiques) {
        this.td010PontsThermiques = tD010PontsThermiques;
        return this;
    }

    public void setTd010PontsThermiques(TD010PontsThermiques tD010PontsThermiques) {
        this.td010PontsThermiques = tD010PontsThermiques;
    }

    public TD011InstalationChauffage getTd011InstalationChauffage() {
        return td011InstalationChauffage;
    }

    public TD006Batiment td011InstalationChauffage(TD011InstalationChauffage tD011InstalationChauffage) {
        this.td011InstalationChauffage = tD011InstalationChauffage;
        return this;
    }

    public void setTd011InstalationChauffage(TD011InstalationChauffage tD011InstalationChauffage) {
        this.td011InstalationChauffage = tD011InstalationChauffage;
    }

    public TD013InstalationECS getTd013InstalationECS() {
        return td013InstalationECS;
    }

    public TD006Batiment td013InstalationECS(TD013InstalationECS tD013InstalationECS) {
        this.td013InstalationECS = tD013InstalationECS;
        return this;
    }

    public void setTd013InstalationECS(TD013InstalationECS tD013InstalationECS) {
        this.td013InstalationECS = tD013InstalationECS;
    }

    public TD015ProductionEnergies getTd015ProductionEnergies() {
        return td015ProductionEnergies;
    }

    public TD006Batiment td015ProductionEnergies(TD015ProductionEnergies tD015ProductionEnergies) {
        this.td015ProductionEnergies = tD015ProductionEnergies;
        return this;
    }

    public void setTd015ProductionEnergies(TD015ProductionEnergies tD015ProductionEnergies) {
        this.td015ProductionEnergies = tD015ProductionEnergies;
    }

    public TD001DPE getTd001DPE() {
        return td001DPE;
    }

    public TD006Batiment td001DPE(TD001DPE tD001DPE) {
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
        if (!(o instanceof TD006Batiment)) {
            return false;
        }
        return id != null && id.equals(((TD006Batiment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD006Batiment{" +
            "id=" + getId() +
            ", besoinChauffage=" + getBesoinChauffage() +
            ", deperditionEnveloppe=" + getDeperditionEnveloppe() +
            ", deperditionRenouvellementAir=" + getDeperditionRenouvellementAir() +
            ", altitude=" + getAltitude() +
            ", nombreNiveau=" + getNombreNiveau() +
            ", hspMoyenne=" + getHspMoyenne() +
            "}";
    }
}
