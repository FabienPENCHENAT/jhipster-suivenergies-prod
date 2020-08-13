package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SetAttribute<Client, ModeVie> modeVies;
	public static volatile SetAttribute<Client, Facture> factures;
	public static volatile SingularAttribute<Client, Long> id;
	public static volatile SetAttribute<Client, InfoDPE> infoDpes;
	public static volatile SetAttribute<Client, User> users;

	public static final String MODE_VIES = "modeVies";
	public static final String FACTURES = "factures";
	public static final String ID = "id";
	public static final String INFO_DPES = "infoDpes";
	public static final String USERS = "users";

}

