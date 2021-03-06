package com.suivenergies.app.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal")
    private Long codePostal;

    @OneToMany(mappedBy = "client")
    private Set<Confort> conforts = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<InfoDPE> infoDPES = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Facture> factures = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<ModeVie> modeVies = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "client_user",
               joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public Client adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getCodePostal() {
        return codePostal;
    }

    public Client codePostal(Long codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(Long codePostal) {
        this.codePostal = codePostal;
    }

    public Set<Confort> getConforts() {
        return conforts;
    }

    public Client conforts(Set<Confort> conforts) {
        this.conforts = conforts;
        return this;
    }

    public Client addConfort(Confort confort) {
        this.conforts.add(confort);
        confort.setClient(this);
        return this;
    }

    public Client removeConfort(Confort confort) {
        this.conforts.remove(confort);
        confort.setClient(null);
        return this;
    }

    public void setConforts(Set<Confort> conforts) {
        this.conforts = conforts;
    }

    public Set<InfoDPE> getInfoDPES() {
        return infoDPES;
    }

    public Client infoDPES(Set<InfoDPE> infoDPES) {
        this.infoDPES = infoDPES;
        return this;
    }

    public Client addInfoDPE(InfoDPE infoDPE) {
        this.infoDPES.add(infoDPE);
        infoDPE.setClient(this);
        return this;
    }

    public Client removeInfoDPE(InfoDPE infoDPE) {
        this.infoDPES.remove(infoDPE);
        infoDPE.setClient(null);
        return this;
    }

    public void setInfoDPES(Set<InfoDPE> infoDPES) {
        this.infoDPES = infoDPES;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public Client factures(Set<Facture> factures) {
        this.factures = factures;
        return this;
    }

    public Client addFacture(Facture facture) {
        this.factures.add(facture);
        facture.setClient(this);
        return this;
    }

    public Client removeFacture(Facture facture) {
        this.factures.remove(facture);
        facture.setClient(null);
        return this;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public Set<ModeVie> getModeVies() {
        return modeVies;
    }

    public Client modeVies(Set<ModeVie> modeVies) {
        this.modeVies = modeVies;
        return this;
    }

    public Client addModeVie(ModeVie modeVie) {
        this.modeVies.add(modeVie);
        modeVie.setClient(this);
        return this;
    }

    public Client removeModeVie(ModeVie modeVie) {
        this.modeVies.remove(modeVie);
        modeVie.setClient(null);
        return this;
    }

    public void setModeVies(Set<ModeVie> modeVies) {
        this.modeVies = modeVies;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Client users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Client addUser(User user) {
        this.users.add(user);
        return this;
    }

    public Client removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", adresse='" + getAdresse() + "'" +
            ", codePostal=" + getCodePostal() +
            "}";
    }
}
