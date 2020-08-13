package com.suivenergies.app.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfoDPE.class)
public abstract class InfoDPE_ {

	public static volatile SingularAttribute<InfoDPE, String> energieEauChaude;
	public static volatile SingularAttribute<InfoDPE, byte[]> dpeJson;
	public static volatile SingularAttribute<InfoDPE, String> typeBatiment;
	public static volatile SingularAttribute<InfoDPE, String> numero;
	public static volatile SingularAttribute<InfoDPE, Long> anneeConstruction;
	public static volatile SingularAttribute<InfoDPE, String> classeConsoEnergie;
	public static volatile SingularAttribute<InfoDPE, LocalDate> dateDPE;
	public static volatile SingularAttribute<InfoDPE, Long> energiePhotovoltaique;
	public static volatile SingularAttribute<InfoDPE, Long> surfaceHabitable;
	public static volatile SingularAttribute<InfoDPE, String> dpeJsonContentType;
	public static volatile SingularAttribute<InfoDPE, String> adresse;
	public static volatile SingularAttribute<InfoDPE, Client> client;
	public static volatile SingularAttribute<InfoDPE, Long> id;
	public static volatile SingularAttribute<InfoDPE, String> energieChauffage;

	public static final String ENERGIE_EAU_CHAUDE = "energieEauChaude";
	public static final String DPE_JSON = "dpeJson";
	public static final String TYPE_BATIMENT = "typeBatiment";
	public static final String NUMERO = "numero";
	public static final String ANNEE_CONSTRUCTION = "anneeConstruction";
	public static final String CLASSE_CONSO_ENERGIE = "classeConsoEnergie";
	public static final String DATE_DP_E = "dateDPE";
	public static final String ENERGIE_PHOTOVOLTAIQUE = "energiePhotovoltaique";
	public static final String SURFACE_HABITABLE = "surfaceHabitable";
	public static final String DPE_JSON_CONTENT_TYPE = "dpeJsonContentType";
	public static final String ADRESSE = "adresse";
	public static final String CLIENT = "client";
	public static final String ID = "id";
	public static final String ENERGIE_CHAUFFAGE = "energieChauffage";

}

