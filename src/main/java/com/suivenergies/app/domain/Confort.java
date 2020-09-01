package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A Confort.
 */
@Entity
@Table(name = "confort")
public class Confort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installation_electrique")
    private Boolean installationElectrique;

    @Column(name = "installation_gaz")
    private Boolean installationGaz;

    @Column(name = "installation_humidite")
    private Boolean installationHumidite;

    @Column(name = "installation_portes_fenetres")
    private Boolean installationPortesFenetres;

    @Column(name = "chauffage_hiver")
    private Boolean chauffageHiver;

    @Column(name = "surface_chauffee")
    private Double surfaceChauffee;

    @Column(name = "temperature_hiver_sejour")
    private Double temperatureHiverSejour;

    @Column(name = "temperature_hiver_chambres")
    private Double temperatureHiverChambres;

    @Column(name = "clim_ete")
    private Boolean climEte;

    @Column(name = "temperature_ete_sejour")
    private Double temperatureEteSejour;

    @Column(name = "temperature_ete_chambres")
    private Double temperatureEteChambres;

    @ManyToMany
    @JoinTable(
        name = "confort_electromenager",
        joinColumns = @JoinColumn(name = "confort_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "electromenager_id", referencedColumnName = "id")
    )
    private Set<Electromenager> electromenagers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "conforts", allowSetters = true)
    private Client client;

    public Confort(Client client, List<Electromenager> electromenagers) {
        this.client = client;
        this.electromenagers.addAll(electromenagers);
    }

    public Confort() {
        // TODO Auto-generated constructor stub
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isInstallationElectrique() {
        return installationElectrique;
    }

    public Confort installationElectrique(Boolean installationElectrique) {
        this.installationElectrique = installationElectrique;
        return this;
    }

    public void setInstallationElectrique(Boolean installationElectrique) {
        this.installationElectrique = installationElectrique;
    }

    public Boolean isInstallationGaz() {
        return installationGaz;
    }

    public Confort installationGaz(Boolean installationGaz) {
        this.installationGaz = installationGaz;
        return this;
    }

    public void setInstallationGaz(Boolean installationGaz) {
        this.installationGaz = installationGaz;
    }

    public Boolean isInstallationHumidite() {
        return installationHumidite;
    }

    public Confort installationHumidite(Boolean installationHumidite) {
        this.installationHumidite = installationHumidite;
        return this;
    }

    public void setInstallationHumidite(Boolean installationHumidite) {
        this.installationHumidite = installationHumidite;
    }

    public Boolean isInstallationPortesFenetres() {
        return installationPortesFenetres;
    }

    public Confort installationPortesFenetres(Boolean installationPortesFenetres) {
        this.installationPortesFenetres = installationPortesFenetres;
        return this;
    }

    public void setInstallationPortesFenetres(Boolean installationPortesFenetres) {
        this.installationPortesFenetres = installationPortesFenetres;
    }

    public Boolean isChauffageHiver() {
        return chauffageHiver;
    }

    public Confort chauffageHiver(Boolean chauffageHiver) {
        this.chauffageHiver = chauffageHiver;
        return this;
    }

    public void setChauffageHiver(Boolean chauffageHiver) {
        this.chauffageHiver = chauffageHiver;
    }

    public Double getSurfaceChauffee() {
        return surfaceChauffee;
    }

    public Confort surfaceChauffee(Double surfaceChauffee) {
        this.surfaceChauffee = surfaceChauffee;
        return this;
    }

    public void setSurfaceChauffee(Double surfaceChauffee) {
        this.surfaceChauffee = surfaceChauffee;
    }

    public Double getTemperatureHiverSejour() {
        return temperatureHiverSejour;
    }

    public Confort temperatureHiverSejour(Double temperatureHiverSejour) {
        this.temperatureHiverSejour = temperatureHiverSejour;
        return this;
    }

    public void setTemperatureHiverSejour(Double temperatureHiverSejour) {
        this.temperatureHiverSejour = temperatureHiverSejour;
    }

    public Double getTemperatureHiverChambres() {
        return temperatureHiverChambres;
    }

    public Confort temperatureHiverChambres(Double temperatureHiverChambres) {
        this.temperatureHiverChambres = temperatureHiverChambres;
        return this;
    }

    public void setTemperatureHiverChambres(Double temperatureHiverChambres) {
        this.temperatureHiverChambres = temperatureHiverChambres;
    }

    public Boolean isClimEte() {
        return climEte;
    }

    public Confort climEte(Boolean climEte) {
        this.climEte = climEte;
        return this;
    }

    public void setClimEte(Boolean climEte) {
        this.climEte = climEte;
    }

    public Double getTemperatureEteSejour() {
        return temperatureEteSejour;
    }

    public Confort temperatureEteSejour(Double temperatureEteSejour) {
        this.temperatureEteSejour = temperatureEteSejour;
        return this;
    }

    public void setTemperatureEteSejour(Double temperatureEteSejour) {
        this.temperatureEteSejour = temperatureEteSejour;
    }

    public Double getTemperatureEteChambres() {
        return temperatureEteChambres;
    }

    public Confort temperatureEteChambres(Double temperatureEteChambres) {
        this.temperatureEteChambres = temperatureEteChambres;
        return this;
    }

    public void setTemperatureEteChambres(Double temperatureEteChambres) {
        this.temperatureEteChambres = temperatureEteChambres;
    }

    public Set<Electromenager> getElectromenagers() {
        return electromenagers;
    }

    public Confort electromenagers(Set<Electromenager> electromenagers) {
        this.electromenagers = electromenagers;
        return this;
    }

    public Confort addElectromenager(Electromenager electromenager) {
        this.electromenagers.add(electromenager);
        electromenager.getConforts().add(this);
        return this;
    }

    public Confort removeElectromenager(Electromenager electromenager) {
        this.electromenagers.remove(electromenager);
        electromenager.getConforts().remove(this);
        return this;
    }

    public void setElectromenagers(Set<Electromenager> electromenagers) {
        this.electromenagers = electromenagers;
    }

    public Client getClient() {
        return client;
    }

    public Confort client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Confort)) {
            return false;
        }
        return id != null && id.equals(((Confort) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Confort{" +
            "id=" + getId() +
            ", installationElectrique='" + isInstallationElectrique() + "'" +
            ", installationGaz='" + isInstallationGaz() + "'" +
            ", installationHumidite='" + isInstallationHumidite() + "'" +
            ", installationPortesFenetres='" + isInstallationPortesFenetres() + "'" +
            ", chauffageHiver='" + isChauffageHiver() + "'" +
            ", surfaceChauffee=" + getSurfaceChauffee() +
            ", temperatureHiverSejour=" + getTemperatureHiverSejour() +
            ", temperatureHiverChambres=" + getTemperatureHiverChambres() +
            ", climEte='" + isClimEte() + "'" +
            ", temperatureEteSejour=" + getTemperatureEteSejour() +
            ", temperatureEteChambres=" + getTemperatureEteChambres() +
            "}";
    }
}
