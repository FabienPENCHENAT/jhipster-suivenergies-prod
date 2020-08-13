package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A InfoDPE.
 */
@Entity
@Table(name = "info_dpe")
public class InfoDPE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "type_batiment")
    private String typeBatiment;

    @Column(name = "annee_construction")
    private Long anneeConstruction;

    @Column(name = "surface_habitable")
    private Long surfaceHabitable;

    @Column(name = "energie_chauffage")
    private String energieChauffage;

    @Column(name = "energie_eau_chaude")
    private String energieEauChaude;

    @Column(name = "energie_photovoltaique")
    private Long energiePhotovoltaique;

    @Column(name = "date_dpe")
    private LocalDate dateDPE;

    @Column(name = "classe_conso_energie")
    private String classeConsoEnergie;

    @Lob
    @Column(name = "dpe_json")
    private byte[] dpeJson;

    @Column(name = "dpe_json_content_type")
    private String dpeJsonContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "infoDpes", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public InfoDPE numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public InfoDPE adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeBatiment() {
        return typeBatiment;
    }

    public InfoDPE typeBatiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
        return this;
    }

    public void setTypeBatiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    public Long getAnneeConstruction() {
        return anneeConstruction;
    }

    public InfoDPE anneeConstruction(Long anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
        return this;
    }

    public void setAnneeConstruction(Long anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public Long getSurfaceHabitable() {
        return surfaceHabitable;
    }

    public InfoDPE surfaceHabitable(Long surfaceHabitable) {
        this.surfaceHabitable = surfaceHabitable;
        return this;
    }

    public void setSurfaceHabitable(Long surfaceHabitable) {
        this.surfaceHabitable = surfaceHabitable;
    }

    public String getEnergieChauffage() {
        return energieChauffage;
    }

    public InfoDPE energieChauffage(String energieChauffage) {
        this.energieChauffage = energieChauffage;
        return this;
    }

    public void setEnergieChauffage(String energieChauffage) {
        this.energieChauffage = energieChauffage;
    }

    public String getEnergieEauChaude() {
        return energieEauChaude;
    }

    public InfoDPE energieEauChaude(String energieEauChaude) {
        this.energieEauChaude = energieEauChaude;
        return this;
    }

    public void setEnergieEauChaude(String energieEauChaude) {
        this.energieEauChaude = energieEauChaude;
    }

    public Long getEnergiePhotovoltaique() {
        return energiePhotovoltaique;
    }

    public InfoDPE energiePhotovoltaique(Long energiePhotovoltaique) {
        this.energiePhotovoltaique = energiePhotovoltaique;
        return this;
    }

    public void setEnergiePhotovoltaique(Long energiePhotovoltaique) {
        this.energiePhotovoltaique = energiePhotovoltaique;
    }

    public LocalDate getDateDPE() {
        return dateDPE;
    }

    public InfoDPE dateDPE(LocalDate dateDPE) {
        this.dateDPE = dateDPE;
        return this;
    }

    public void setDateDPE(LocalDate dateDPE) {
        this.dateDPE = dateDPE;
    }

    public String getClasseConsoEnergie() {
        return classeConsoEnergie;
    }

    public InfoDPE classeConsoEnergie(String classeConsoEnergie) {
        this.classeConsoEnergie = classeConsoEnergie;
        return this;
    }

    public void setClasseConsoEnergie(String classeConsoEnergie) {
        this.classeConsoEnergie = classeConsoEnergie;
    }

    public byte[] getDpeJson() {
        return dpeJson;
    }

    public InfoDPE dpeJson(byte[] dpeJson) {
        this.dpeJson = dpeJson;
        return this;
    }

    public void setDpeJson(byte[] dpeJson) {
        this.dpeJson = dpeJson;
    }

    public String getDpeJsonContentType() {
        return dpeJsonContentType;
    }

    public InfoDPE dpeJsonContentType(String dpeJsonContentType) {
        this.dpeJsonContentType = dpeJsonContentType;
        return this;
    }

    public void setDpeJsonContentType(String dpeJsonContentType) {
        this.dpeJsonContentType = dpeJsonContentType;
    }

    public Client getClient() {
        return client;
    }

    public InfoDPE client(Client client) {
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
        if (!(o instanceof InfoDPE)) {
            return false;
        }
        return id != null && id.equals(((InfoDPE) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoDPE{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", typeBatiment='" + getTypeBatiment() + "'" +
            ", anneeConstruction=" + getAnneeConstruction() +
            ", surfaceHabitable=" + getSurfaceHabitable() +
            ", energieChauffage='" + getEnergieChauffage() + "'" +
            ", energieEauChaude='" + getEnergieEauChaude() + "'" +
            ", energiePhotovoltaique=" + getEnergiePhotovoltaique() +
            ", dateDPE='" + getDateDPE() + "'" +
            ", classeConsoEnergie='" + getClasseConsoEnergie() + "'" +
            ", dpeJson='" + getDpeJson() + "'" +
            ", dpeJsonContentType='" + getDpeJsonContentType() + "'" +
            "}";
    }
}
