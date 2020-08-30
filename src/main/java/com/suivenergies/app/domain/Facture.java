package com.suivenergies.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.suivenergies.app.domain.enumeration.EnergiesFacture;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnergiesFacture type;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "montant")
    private Double montant;

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnergiesFacture getType() {
        return type;
    }

    public Facture type(EnergiesFacture type) {
        this.type = type;
        return this;
    }

    public void setType(EnergiesFacture type) {
        this.type = type;
    }

    public Integer getAnnee() {
        return annee;
    }

    public Facture annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Facture quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getMontant() {
        return montant;
    }

    public Facture montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Client getClient() {
        return client;
    }

    public Facture client(Client client) {
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
        if (!(o instanceof Facture)) {
            return false;
        }
        return id != null && id.equals(((Facture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Facture{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", annee=" + getAnnee() +
            ", quantite=" + getQuantite() +
            ", montant=" + getMontant() +
            "}";
    }
}
