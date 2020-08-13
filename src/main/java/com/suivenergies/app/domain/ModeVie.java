package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ModeVie.
 */
@Entity
@Table(name = "mode_vie")
public class ModeVie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nb_personnes")
    private Integer nbPersonnes;

    @Column(name = "presence_matin_semaine")
    private Boolean presenceMatinSemaine;

    @Column(name = "presence_matin_we")
    private Boolean presenceMatinWE;

    @Column(name = "presence_am_semaine")
    private Boolean presenceAMSemaine;

    @Column(name = "presence_amwe")
    private Boolean presenceAMWE;

    @Column(name = "presence_soir_semaine")
    private Boolean presenceSoirSemaine;

    @Column(name = "presence_soir_we")
    private Boolean presenceSoirWE;

    @Column(name = "presence_nuit_semaine")
    private Boolean presenceNuitSemaine;

    @Column(name = "presence_nuit_we")
    private Boolean presenceNuitWE;

    @Column(name = "semaines_absence_hiver")
    private Integer semainesAbsenceHiver;

    @Column(name = "semaines_absence_ete")
    private Integer semainesAbsenceEte;

    @ManyToOne
    @JsonIgnoreProperties(value = "modeVies", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbPersonnes() {
        return nbPersonnes;
    }

    public ModeVie nbPersonnes(Integer nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
        return this;
    }

    public void setNbPersonnes(Integer nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Boolean isPresenceMatinSemaine() {
        return presenceMatinSemaine;
    }

    public ModeVie presenceMatinSemaine(Boolean presenceMatinSemaine) {
        this.presenceMatinSemaine = presenceMatinSemaine;
        return this;
    }

    public void setPresenceMatinSemaine(Boolean presenceMatinSemaine) {
        this.presenceMatinSemaine = presenceMatinSemaine;
    }

    public Boolean isPresenceMatinWE() {
        return presenceMatinWE;
    }

    public ModeVie presenceMatinWE(Boolean presenceMatinWE) {
        this.presenceMatinWE = presenceMatinWE;
        return this;
    }

    public void setPresenceMatinWE(Boolean presenceMatinWE) {
        this.presenceMatinWE = presenceMatinWE;
    }

    public Boolean isPresenceAMSemaine() {
        return presenceAMSemaine;
    }

    public ModeVie presenceAMSemaine(Boolean presenceAMSemaine) {
        this.presenceAMSemaine = presenceAMSemaine;
        return this;
    }

    public void setPresenceAMSemaine(Boolean presenceAMSemaine) {
        this.presenceAMSemaine = presenceAMSemaine;
    }

    public Boolean isPresenceAMWE() {
        return presenceAMWE;
    }

    public ModeVie presenceAMWE(Boolean presenceAMWE) {
        this.presenceAMWE = presenceAMWE;
        return this;
    }

    public void setPresenceAMWE(Boolean presenceAMWE) {
        this.presenceAMWE = presenceAMWE;
    }

    public Boolean isPresenceSoirSemaine() {
        return presenceSoirSemaine;
    }

    public ModeVie presenceSoirSemaine(Boolean presenceSoirSemaine) {
        this.presenceSoirSemaine = presenceSoirSemaine;
        return this;
    }

    public void setPresenceSoirSemaine(Boolean presenceSoirSemaine) {
        this.presenceSoirSemaine = presenceSoirSemaine;
    }

    public Boolean isPresenceSoirWE() {
        return presenceSoirWE;
    }

    public ModeVie presenceSoirWE(Boolean presenceSoirWE) {
        this.presenceSoirWE = presenceSoirWE;
        return this;
    }

    public void setPresenceSoirWE(Boolean presenceSoirWE) {
        this.presenceSoirWE = presenceSoirWE;
    }

    public Boolean isPresenceNuitSemaine() {
        return presenceNuitSemaine;
    }

    public ModeVie presenceNuitSemaine(Boolean presenceNuitSemaine) {
        this.presenceNuitSemaine = presenceNuitSemaine;
        return this;
    }

    public void setPresenceNuitSemaine(Boolean presenceNuitSemaine) {
        this.presenceNuitSemaine = presenceNuitSemaine;
    }

    public Boolean isPresenceNuitWE() {
        return presenceNuitWE;
    }

    public ModeVie presenceNuitWE(Boolean presenceNuitWE) {
        this.presenceNuitWE = presenceNuitWE;
        return this;
    }

    public void setPresenceNuitWE(Boolean presenceNuitWE) {
        this.presenceNuitWE = presenceNuitWE;
    }

    public Integer getSemainesAbsenceHiver() {
        return semainesAbsenceHiver;
    }

    public ModeVie semainesAbsenceHiver(Integer semainesAbsenceHiver) {
        this.semainesAbsenceHiver = semainesAbsenceHiver;
        return this;
    }

    public void setSemainesAbsenceHiver(Integer semainesAbsenceHiver) {
        this.semainesAbsenceHiver = semainesAbsenceHiver;
    }

    public Integer getSemainesAbsenceEte() {
        return semainesAbsenceEte;
    }

    public ModeVie semainesAbsenceEte(Integer semainesAbsenceEte) {
        this.semainesAbsenceEte = semainesAbsenceEte;
        return this;
    }

    public void setSemainesAbsenceEte(Integer semainesAbsenceEte) {
        this.semainesAbsenceEte = semainesAbsenceEte;
    }

    public Client getClient() {
        return client;
    }

    public ModeVie client(Client client) {
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
        if (!(o instanceof ModeVie)) {
            return false;
        }
        return id != null && id.equals(((ModeVie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModeVie{" +
            "id=" + getId() +
            ", nbPersonnes=" + getNbPersonnes() +
            ", presenceMatinSemaine='" + isPresenceMatinSemaine() + "'" +
            ", presenceMatinWE='" + isPresenceMatinWE() + "'" +
            ", presenceAMSemaine='" + isPresenceAMSemaine() + "'" +
            ", presenceAMWE='" + isPresenceAMWE() + "'" +
            ", presenceSoirSemaine='" + isPresenceSoirSemaine() + "'" +
            ", presenceSoirWE='" + isPresenceSoirWE() + "'" +
            ", presenceNuitSemaine='" + isPresenceNuitSemaine() + "'" +
            ", presenceNuitWE='" + isPresenceNuitWE() + "'" +
            ", semainesAbsenceHiver=" + getSemainesAbsenceHiver() +
            ", semainesAbsenceEte=" + getSemainesAbsenceEte() +
            "}";
    }
}
