package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Electromenager.
 */
@Entity
@Table(name = "electromenager")
public class Electromenager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "note")
    private String note;

    @Column(name = "by_default")
    private Boolean byDefault;

    @Column(name = "conso_annuelle")
    private Double consoAnnuelle;

    @ManyToMany(mappedBy = "electromenagers")
    @JsonIgnore
    private Set<Confort> conforts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Electromenager nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNote() {
        return note;
    }

    public Electromenager note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isByDefault() {
        return byDefault;
    }

    public Electromenager byDefault(Boolean byDefault) {
        this.byDefault = byDefault;
        return this;
    }

    public void setByDefault(Boolean byDefault) {
        this.byDefault = byDefault;
    }

    public Double getConsoAnnuelle() {
        return consoAnnuelle;
    }

    public Electromenager consoAnnuelle(Double consoAnnuelle) {
        this.consoAnnuelle = consoAnnuelle;
        return this;
    }

    public void setConsoAnnuelle(Double consoAnnuelle) {
        this.consoAnnuelle = consoAnnuelle;
    }

    public Set<Confort> getConforts() {
        return conforts;
    }

    public Electromenager conforts(Set<Confort> conforts) {
        this.conforts = conforts;
        return this;
    }

    public Electromenager addConfort(Confort confort) {
        this.conforts.add(confort);
        confort.getElectromenagers().add(this);
        return this;
    }

    public Electromenager removeConfort(Confort confort) {
        this.conforts.remove(confort);
        confort.getElectromenagers().remove(this);
        return this;
    }

    public void setConforts(Set<Confort> conforts) {
        this.conforts = conforts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Electromenager)) {
            return false;
        }
        return id != null && id.equals(((Electromenager) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Electromenager{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", note='" + getNote() + "'" +
            ", byDefault='" + isByDefault() + "'" +
            ", consoAnnuelle=" + getConsoAnnuelle() +
            "}";
    }
}
