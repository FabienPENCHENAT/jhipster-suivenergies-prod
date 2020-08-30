package com.suivenergies.app.domain;

import com.suivenergies.app.domain.enumeration.EnergiesFacture;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Facture.class)
public abstract class Facture_ {

	public static volatile SingularAttribute<Facture, Client> client;
	public static volatile SingularAttribute<Facture, Double> montant;
	public static volatile SingularAttribute<Facture, Long> id;
	public static volatile SingularAttribute<Facture, Integer> annee;
	public static volatile SingularAttribute<Facture, EnergiesFacture> type;
	public static volatile SingularAttribute<Facture, Integer> quantite;

	public static final String CLIENT = "client";
	public static final String MONTANT = "montant";
	public static final String ID = "id";
	public static final String ANNEE = "annee";
	public static final String TYPE = "type";
	public static final String QUANTITE = "quantite";

}

