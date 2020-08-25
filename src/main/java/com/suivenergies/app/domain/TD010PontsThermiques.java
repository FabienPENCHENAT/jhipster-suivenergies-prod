package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TD010PontsThermiques.
 */
@Entity
@Table(name = "td_010_ponts_thermiques")
public class TD010PontsThermiques implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longueur")
    private Long longueur;

    @Column(name = "tv_013_valeur_pont_thermique")
    private Double tv013ValeurPontThermique;

    @OneToOne(mappedBy = "td010PontsThermiques")
    @JsonIgnore
    private TD006Batiment td006Batiment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLongueur() {
        return longueur;
    }

    public TD010PontsThermiques longueur(Long longueur) {
        this.longueur = longueur;
        return this;
    }

    public void setLongueur(Long longueur) {
        this.longueur = longueur;
    }

    public Double getTv013ValeurPontThermique() {
        return tv013ValeurPontThermique;
    }

    public TD010PontsThermiques tv013ValeurPontThermique(Double tv013ValeurPontThermique) {
        this.tv013ValeurPontThermique = tv013ValeurPontThermique;
        return this;
    }

    public void setTv013ValeurPontThermique(Double tv013ValeurPontThermique) {
        this.tv013ValeurPontThermique = tv013ValeurPontThermique;
    }

    public TD006Batiment getTd006Batiment() {
        return td006Batiment;
    }

    public TD010PontsThermiques td006Batiment(TD006Batiment tD006Batiment) {
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
        if (!(o instanceof TD010PontsThermiques)) {
            return false;
        }
        return id != null && id.equals(((TD010PontsThermiques) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TD010PontsThermiques{" +
            "id=" + getId() +
            ", longueur=" + getLongueur() +
            ", tv013ValeurPontThermique=" + getTv013ValeurPontThermique() +
            "}";
    }
}
