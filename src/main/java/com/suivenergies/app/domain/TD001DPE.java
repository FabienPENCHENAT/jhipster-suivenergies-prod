package com.suivenergies.app.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TD001DPE.
 */
@Entity
@Table(name = "td_001_dpe")
public class TD001DPE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_dpe")
    private String numeroDpe;

    @Column(name = "tr_001_modele_dpe")
    private String tr001ModeleDpe;

    @Column(name = "nom_methode_dpe")
    private String nomMethodeDpe;

    @Column(name = "consommation_energie")
    private Long consommationEnergie;

    @Column(name = "classe_consommation_energie")
    private String classeConsommationEnergie;

    @Column(name = "estimation_ges")
    private Long estimationGes;

    @Column(name = "classe_estimation_ges")
    private String classeEstimationGes;

    @Column(name = "tr_002_type_batiment")
    private String tr002TypeBatiment;

    @Column(name = "annee_construction")
    private Long anneeConstruction;

    @Column(name = "surface_habitable")
    private Double surfaceHabitable;

    @Column(name = "tv_016_departement")
    private String tv016Departement;

    @Column(name = "commune")
    private String commune;

    @Column(name = "arrondissement")
    private String arrondissement;

    @Column(name = "type_voie")
    private String typeVoie;

    @Column(name = "nom_rue")
    private String nomRue;

    @Column(name = "numero_rue")
    private String numeroRue;

    @Column(name = "batiment")
    private String batiment;

    @Column(name = "escalier")
    private String escalier;

    @Column(name = "etage")
    private String etage;

    @Column(name = "porte")
    private String porte;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "code_insee_commune")
    private String codeInseeCommune;

    @Column(name = "code_insee_commune_actualise")
    private String codeInseeCommuneActualise;

    @Column(name = "code_insee_commune_corrige")
    private String codeInseeCommuneCorrige;

    @Column(name = "numero_lot")
    private String numeroLot;

    @Column(name = "date_reception_dpe")
    private LocalDate dateReceptionDpe;

    @OneToOne
    @JoinColumn(unique = true)
    private TD002Consommations td002Consommations;

    @OneToOne
    @JoinColumn(unique = true)
    private TD006Batiment td006Batiment;

    @OneToOne
    @JoinColumn(unique = true)
    private TD017ConsommationNeuf td017ConsommationNeuf;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDpe() {
        return numeroDpe;
    }

    public TD001DPE numeroDpe(String numeroDpe) {
        this.numeroDpe = numeroDpe;
        return this;
    }

    public void setNumeroDpe(String numeroDpe) {
        this.numeroDpe = numeroDpe;
    }

    public String getTr001ModeleDpe() {
        return tr001ModeleDpe;
    }

    public TD001DPE tr001ModeleDpe(String tr001ModeleDpe) {
        this.tr001ModeleDpe = tr001ModeleDpe;
        return this;
    }

    public void setTr001ModeleDpe(String tr001ModeleDpe) {
        this.tr001ModeleDpe = tr001ModeleDpe;
    }

    public String getNomMethodeDpe() {
        return nomMethodeDpe;
    }

    public TD001DPE nomMethodeDpe(String nomMethodeDpe) {
        this.nomMethodeDpe = nomMethodeDpe;
        return this;
    }

    public void setNomMethodeDpe(String nomMethodeDpe) {
        this.nomMethodeDpe = nomMethodeDpe;
    }

    public Long getConsommationEnergie() {
        return consommationEnergie;
    }

    public TD001DPE consommationEnergie(Long consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
        return this;
    }

    public void setConsommationEnergie(Long consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    public String getClasseConsommationEnergie() {
        return classeConsommationEnergie;
    }

    public TD001DPE classeConsommationEnergie(String classeConsommationEnergie) {
        this.classeConsommationEnergie = classeConsommationEnergie;
        return this;
    }

    public void setClasseConsommationEnergie(String classeConsommationEnergie) {
        this.classeConsommationEnergie = classeConsommationEnergie;
    }

    public Long getEstimationGes() {
        return estimationGes;
    }

    public TD001DPE estimationGes(Long estimationGes) {
        this.estimationGes = estimationGes;
        return this;
    }

    public void setEstimationGes(Long estimationGes) {
        this.estimationGes = estimationGes;
    }

    public String getClasseEstimationGes() {
        return classeEstimationGes;
    }

    public TD001DPE classeEstimationGes(String classeEstimationGes) {
        this.classeEstimationGes = classeEstimationGes;
        return this;
    }

    public void setClasseEstimationGes(String classeEstimationGes) {
        this.classeEstimationGes = classeEstimationGes;
    }

    public String getTr002TypeBatiment() {
        return tr002TypeBatiment;
    }

    public TD001DPE tr002TypeBatiment(String tr002TypeBatiment) {
        this.tr002TypeBatiment = tr002TypeBatiment;
        return this;
    }

    public void setTr002TypeBatiment(String tr002TypeBatiment) {
        this.tr002TypeBatiment = tr002TypeBatiment;
    }

    public Long getAnneeConstruction() {
        return anneeConstruction;
    }

    public TD001DPE anneeConstruction(Long anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
        return this;
    }

    public void setAnneeConstruction(Long anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public Double getSurfaceHabitable() {
        return surfaceHabitable;
    }

    public TD001DPE surfaceHabitable(Double surfaceHabitable) {
        this.surfaceHabitable = surfaceHabitable;
        return this;
    }

    public void setSurfaceHabitable(Double surfaceHabitable) {
        this.surfaceHabitable = surfaceHabitable;
    }

    public String getTv016Departement() {
        return tv016Departement;
    }

    public TD001DPE tv016Departement(String tv016Departement) {
        this.tv016Departement = tv016Departement;
        return this;
    }

    public void setTv016Departement(String tv016Departement) {
        this.tv016Departement = tv016Departement;
    }

    public String getCommune() {
        return commune;
    }

    public TD001DPE commune(String commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public TD001DPE arrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
        return this;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public TD001DPE typeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
        return this;
    }

    public void setTypeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
    }

    public String getNomRue() {
        return nomRue;
    }

    public TD001DPE nomRue(String nomRue) {
        this.nomRue = nomRue;
        return this;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public TD001DPE numeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
        return this;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getBatiment() {
        return batiment;
    }

    public TD001DPE batiment(String batiment) {
        this.batiment = batiment;
        return this;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public String getEscalier() {
        return escalier;
    }

    public TD001DPE escalier(String escalier) {
        this.escalier = escalier;
        return this;
    }

    public void setEscalier(String escalier) {
        this.escalier = escalier;
    }

    public String getEtage() {
        return etage;
    }

    public TD001DPE etage(String etage) {
        this.etage = etage;
        return this;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getPorte() {
        return porte;
    }

    public TD001DPE porte(String porte) {
        this.porte = porte;
        return this;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public TD001DPE codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCodeInseeCommune() {
        return codeInseeCommune;
    }

    public TD001DPE codeInseeCommune(String codeInseeCommune) {
        this.codeInseeCommune = codeInseeCommune;
        return this;
    }

    public void setCodeInseeCommune(String codeInseeCommune) {
        this.codeInseeCommune = codeInseeCommune;
    }

    public String getCodeInseeCommuneActualise() {
        return codeInseeCommuneActualise;
    }

    public TD001DPE codeInseeCommuneActualise(String codeInseeCommuneActualise) {
        this.codeInseeCommuneActualise = codeInseeCommuneActualise;
        return this;
    }

    public void setCodeInseeCommuneActualise(String codeInseeCommuneActualise) {
        this.codeInseeCommuneActualise = codeInseeCommuneActualise;
    }

    public String getCodeInseeCommuneCorrige() {
        return codeInseeCommuneCorrige;
    }

    public TD001DPE codeInseeCommuneCorrige(String codeInseeCommuneCorrige) {
        this.codeInseeCommuneCorrige = codeInseeCommuneCorrige;
        return this;
    }

    public void setCodeInseeCommuneCorrige(String codeInseeCommuneCorrige) {
        this.codeInseeCommuneCorrige = codeInseeCommuneCorrige;
    }

    public String getNumeroLot() {
        return numeroLot;
    }

    public TD001DPE numeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
        return this;
    }

    public void setNumeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
    }

    public LocalDate getDateReceptionDpe() {
        return dateReceptionDpe;
    }

    public TD001DPE dateReceptionDpe(LocalDate dateReceptionDpe) {
        this.dateReceptionDpe = dateReceptionDpe;
        return this;
    }

    public void setDateReceptionDpe(LocalDate dateReceptionDpe) {
        this.dateReceptionDpe = dateReceptionDpe;
    }

    public TD002Consommations getTd002Consommations() {
        return td002Consommations;
    }

    public TD001DPE td002Consommations(TD002Consommations tD002Consommations) {
        this.td002Consommations = tD002Consommations;
        return this;
    }

    public void setTd002Consommations(TD002Consommations tD002Consommations) {
        this.td002Consommations = tD002Consommations;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD001DPE td006Batiment(TD006Batiment tD006Batiment) {
        this.td006Batiment = tD006Batiment;
        return this;
    }

    public void setTd006Batiment(TD006Batiment tD006Batiment) {
        this.td006Batiment = tD006Batiment;
    }

    public TD017ConsommationNeuf getTd017ConsommationNeuf() {
        return td017ConsommationNeuf;
    }

    public TD001DPE td017ConsommationNeuf(TD017ConsommationNeuf tD017ConsommationNeuf) {
        this.td017ConsommationNeuf = tD017ConsommationNeuf;
        return this;
    }

    public void setTd017ConsommationNeuf(TD017ConsommationNeuf tD017ConsommationNeuf) {
        this.td017ConsommationNeuf = tD017ConsommationNeuf;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TD001DPE)) {
            return false;
        }
        return id != null && id.equals(((TD001DPE) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD001DPE{" +
            "id=" + getId() +
            ", numeroDpe='" + getNumeroDpe() + "'" +
            ", tr001ModeleDpe='" + getTr001ModeleDpe() + "'" +
            ", nomMethodeDpe='" + getNomMethodeDpe() + "'" +
            ", consommationEnergie=" + getConsommationEnergie() +
            ", classeConsommationEnergie='" + getClasseConsommationEnergie() + "'" +
            ", estimationGes=" + getEstimationGes() +
            ", classeEstimationGes='" + getClasseEstimationGes() + "'" +
            ", tr002TypeBatiment='" + getTr002TypeBatiment() + "'" +
            ", anneeConstruction=" + getAnneeConstruction() +
            ", surfaceHabitable=" + getSurfaceHabitable() +
            ", tv016Departement='" + getTv016Departement() + "'" +
            ", commune='" + getCommune() + "'" +
            ", arrondissement='" + getArrondissement() + "'" +
            ", typeVoie='" + getTypeVoie() + "'" +
            ", nomRue='" + getNomRue() + "'" +
            ", numeroRue='" + getNumeroRue() + "'" +
            ", batiment='" + getBatiment() + "'" +
            ", escalier='" + getEscalier() + "'" +
            ", etage='" + getEtage() + "'" +
            ", porte='" + getPorte() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", codeInseeCommune='" + getCodeInseeCommune() + "'" +
            ", codeInseeCommuneActualise='" + getCodeInseeCommuneActualise() + "'" +
            ", codeInseeCommuneCorrige='" + getCodeInseeCommuneCorrige() + "'" +
            ", numeroLot='" + getNumeroLot() + "'" +
            ", dateReceptionDpe='" + getDateReceptionDpe() + "'" +
            "}";
    }
}
