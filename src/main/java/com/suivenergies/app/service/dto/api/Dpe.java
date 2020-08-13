package com.suivenergies.app.service.dto.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize
@JsonPropertyOrder(
    {
        "classe_consommation_energie",
        "tr001_modele_dpe_type_libelle",
        "annee_construction",
        "_geopoint",
        "latitude",
        "surface_thermique_lot",
        "_i",
        "tr002_type_batiment_description",
        "_geoshape.coordinates.0",
        "_geoshape.coordinates.1",
        "_geoshape.type",
        "_rand",
        "_geocorners.0",
        "estimation_ges",
        "classe_estimation_ges",
        "version_methode_dpe",
        "nom_methode_dpe",
        "result_label",
        "consommation_energie",
        "result_score",
        "date_etablissement_dpe",
        "longitude",
        "_score",
        "_id",
    }
)
public class Dpe {
    @JsonProperty("classe_consommation_energie")
    private String classeConsommationEnergie;

    @JsonProperty("tr001_modele_dpe_type_libelle")
    private String tr001ModeleDpeTypeLibelle;

    @JsonProperty("annee_construction")
    private Long anneeConstruction;

    @JsonProperty("_geopoint")
    private String geopoint;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("surface_thermique_lot")
    private Long surfaceThermiqueLot;

    @JsonProperty("_i")
    private Long i;

    @JsonProperty("tr002_type_batiment_description")
    private String tr002TypeBatimentDescription;

    @JsonProperty("_geoshape.coordinates.0")
    private Double geoshapeCoordinates0;

    @JsonProperty("_geoshape.coordinates.1")
    private Double geoshapeCoordinates1;

    @JsonProperty("_geoshape.type")
    private String geoshapeType;

    @JsonProperty("_rand")
    private Long rand;

    @JsonProperty("_geocorners.0")
    private String geocorners0;

    @JsonProperty("estimation_ges")
    private Long estimationGes;

    @JsonProperty("classe_estimation_ges")
    private String classeEstimationGes;

    @JsonProperty("version_methode_dpe")
    private String versionMethodeDpe;

    @JsonProperty("nom_methode_dpe")
    private String nomMethodeDpe;

    @JsonProperty("result_label")
    private String resultLabel;

    @JsonProperty("consommation_energie")
    private Long consommationEnergie;

    @JsonProperty("result_score")
    private Double resultScore;

    @JsonProperty("date_etablissement_dpe")
    private String dateEtablissementDpe;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("_score")
    private Long score;

    @JsonProperty("_id")
    private String id;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Dpe() {}

    /**
     *
     * @param surfaceThermiqueLot
     * @param geoshapeCoordinates0
     * @param geoshapeCoordinates1
     * @param anneeConstruction
     * @param latitude
     * @param classeConsommationEnergie
     * @param geopoint
     * @param i
     * @param estimationGes
     * @param rand
     * @param score
     * @param geocorners0
     * @param nomMethodeDpe
     * @param versionMethodeDpe
     * @param tr002TypeBatimentDescription
     * @param resultScore
     * @param tr001ModeleDpeTypeLibelle
     * @param classeEstimationGes
     * @param dateEtablissementDpe
     * @param id
     * @param geoshapeType
     * @param resultLabel
     * @param consommationEnergie
     * @param longitude
     */
    public Dpe(
        String classeConsommationEnergie,
        String tr001ModeleDpeTypeLibelle,
        Long anneeConstruction,
        String geopoint,
        Double latitude,
        Long surfaceThermiqueLot,
        Long i,
        String tr002TypeBatimentDescription,
        Double geoshapeCoordinates0,
        Double geoshapeCoordinates1,
        String geoshapeType,
        Long rand,
        String geocorners0,
        Long estimationGes,
        String classeEstimationGes,
        String versionMethodeDpe,
        String nomMethodeDpe,
        String resultLabel,
        Long consommationEnergie,
        Double resultScore,
        String dateEtablissementDpe,
        Double longitude,
        Long score,
        String id
    ) {
        super();
        this.classeConsommationEnergie = classeConsommationEnergie;
        this.tr001ModeleDpeTypeLibelle = tr001ModeleDpeTypeLibelle;
        this.anneeConstruction = anneeConstruction;
        this.geopoint = geopoint;
        this.latitude = latitude;
        this.surfaceThermiqueLot = surfaceThermiqueLot;
        this.i = i;
        this.tr002TypeBatimentDescription = tr002TypeBatimentDescription;
        this.geoshapeCoordinates0 = geoshapeCoordinates0;
        this.geoshapeCoordinates1 = geoshapeCoordinates1;
        this.geoshapeType = geoshapeType;
        this.rand = rand;
        this.geocorners0 = geocorners0;
        this.estimationGes = estimationGes;
        this.classeEstimationGes = classeEstimationGes;
        this.versionMethodeDpe = versionMethodeDpe;
        this.nomMethodeDpe = nomMethodeDpe;
        this.resultLabel = resultLabel;
        this.consommationEnergie = consommationEnergie;
        this.resultScore = resultScore;
        this.dateEtablissementDpe = dateEtablissementDpe;
        this.longitude = longitude;
        this.score = score;
        this.id = id;
    }

    @JsonProperty("classe_consommation_energie")
    public String getClasseConsommationEnergie() {
        return classeConsommationEnergie;
    }

    @JsonProperty("classe_consommation_energie")
    public void setClasseConsommationEnergie(String classeConsommationEnergie) {
        this.classeConsommationEnergie = classeConsommationEnergie;
    }

    @JsonProperty("tr001_modele_dpe_type_libelle")
    public String getTr001ModeleDpeTypeLibelle() {
        return tr001ModeleDpeTypeLibelle;
    }

    @JsonProperty("tr001_modele_dpe_type_libelle")
    public void setTr001ModeleDpeTypeLibelle(String tr001ModeleDpeTypeLibelle) {
        this.tr001ModeleDpeTypeLibelle = tr001ModeleDpeTypeLibelle;
    }

    @JsonProperty("annee_construction")
    public Long getAnneeConstruction() {
        return anneeConstruction;
    }

    @JsonProperty("annee_construction")
    public void setAnneeConstruction(Long anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    @JsonProperty("_geopoint")
    public String getGeopoint() {
        return geopoint;
    }

    @JsonProperty("_geopoint")
    public void setGeopoint(String geopoint) {
        this.geopoint = geopoint;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("surface_thermique_lot")
    public Long getSurfaceThermiqueLot() {
        return surfaceThermiqueLot;
    }

    @JsonProperty("surface_thermique_lot")
    public void setSurfaceThermiqueLot(Long surfaceThermiqueLot) {
        this.surfaceThermiqueLot = surfaceThermiqueLot;
    }

    @JsonProperty("_i")
    public Long getI() {
        return i;
    }

    @JsonProperty("_i")
    public void setI(Long i) {
        this.i = i;
    }

    @JsonProperty("tr002_type_batiment_description")
    public String getTr002TypeBatimentDescription() {
        return tr002TypeBatimentDescription;
    }

    @JsonProperty("tr002_type_batiment_description")
    public void setTr002TypeBatimentDescription(String tr002TypeBatimentDescription) {
        this.tr002TypeBatimentDescription = tr002TypeBatimentDescription;
    }

    @JsonProperty("_geoshape.coordinates.0")
    public Double getGeoshapeCoordinates0() {
        return geoshapeCoordinates0;
    }

    @JsonProperty("_geoshape.coordinates.0")
    public void setGeoshapeCoordinates0(Double geoshapeCoordinates0) {
        this.geoshapeCoordinates0 = geoshapeCoordinates0;
    }

    @JsonProperty("_geoshape.coordinates.1")
    public Double getGeoshapeCoordinates1() {
        return geoshapeCoordinates1;
    }

    @JsonProperty("_geoshape.coordinates.1")
    public void setGeoshapeCoordinates1(Double geoshapeCoordinates1) {
        this.geoshapeCoordinates1 = geoshapeCoordinates1;
    }

    @JsonProperty("_geoshape.type")
    public String getGeoshapeType() {
        return geoshapeType;
    }

    @JsonProperty("_geoshape.type")
    public void setGeoshapeType(String geoshapeType) {
        this.geoshapeType = geoshapeType;
    }

    @JsonProperty("_rand")
    public Long getRand() {
        return rand;
    }

    @JsonProperty("_rand")
    public void setRand(Long rand) {
        this.rand = rand;
    }

    @JsonProperty("_geocorners.0")
    public String getGeocorners0() {
        return geocorners0;
    }

    @JsonProperty("_geocorners.0")
    public void setGeocorners0(String geocorners0) {
        this.geocorners0 = geocorners0;
    }

    @JsonProperty("estimation_ges")
    public Long getEstimationGes() {
        return estimationGes;
    }

    @JsonProperty("estimation_ges")
    public void setEstimationGes(Long estimationGes) {
        this.estimationGes = estimationGes;
    }

    @JsonProperty("classe_estimation_ges")
    public String getClasseEstimationGes() {
        return classeEstimationGes;
    }

    @JsonProperty("classe_estimation_ges")
    public void setClasseEstimationGes(String classeEstimationGes) {
        this.classeEstimationGes = classeEstimationGes;
    }

    @JsonProperty("version_methode_dpe")
    public String getVersionMethodeDpe() {
        return versionMethodeDpe;
    }

    @JsonProperty("version_methode_dpe")
    public void setVersionMethodeDpe(String versionMethodeDpe) {
        this.versionMethodeDpe = versionMethodeDpe;
    }

    @JsonProperty("nom_methode_dpe")
    public String getNomMethodeDpe() {
        return nomMethodeDpe;
    }

    @JsonProperty("nom_methode_dpe")
    public void setNomMethodeDpe(String nomMethodeDpe) {
        this.nomMethodeDpe = nomMethodeDpe;
    }

    @JsonProperty("result_label")
    public String getResultLabel() {
        return resultLabel;
    }

    @JsonProperty("result_label")
    public void setResultLabel(String resultLabel) {
        this.resultLabel = resultLabel;
    }

    @JsonProperty("consommation_energie")
    public Long getConsommationEnergie() {
        return consommationEnergie;
    }

    @JsonProperty("consommation_energie")
    public void setConsommationEnergie(Long consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    @JsonProperty("result_score")
    public Double getResultScore() {
        return resultScore;
    }

    @JsonProperty("result_score")
    public void setResultScore(Double resultScore) {
        this.resultScore = resultScore;
    }

    @JsonProperty("date_etablissement_dpe")
    public String getDateEtablissementDpe() {
        return dateEtablissementDpe;
    }

    @JsonProperty("date_etablissement_dpe")
    public void setDateEtablissementDpe(String dateEtablissementDpe) {
        this.dateEtablissementDpe = dateEtablissementDpe;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("_score")
    public Long getScore() {
        return score;
    }

    @JsonProperty("_score")
    public void setScore(Long score) {
        this.score = score;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
