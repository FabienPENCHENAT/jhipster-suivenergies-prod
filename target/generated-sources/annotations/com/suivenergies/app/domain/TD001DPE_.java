package com.suivenergies.app.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD001DPE.class)
public abstract class TD001DPE_ {

	public static volatile SingularAttribute<TD001DPE, String> commune;
	public static volatile SingularAttribute<TD001DPE, String> arrondissement;
	public static volatile SingularAttribute<TD001DPE, String> classeConsommationEnergie;
	public static volatile SingularAttribute<TD001DPE, Double> estimationGes;
	public static volatile SingularAttribute<TD001DPE, String> escalier;
	public static volatile SingularAttribute<TD001DPE, String> batiment;
	public static volatile SingularAttribute<TD001DPE, String> tr001ModeleDpe;
	public static volatile SingularAttribute<TD001DPE, String> nomRue;
	public static volatile SingularAttribute<TD001DPE, Double> surfaceHabitable;
	public static volatile SingularAttribute<TD001DPE, String> codeInseeCommuneCorrige;
	public static volatile SingularAttribute<TD001DPE, String> porte;
	public static volatile SingularAttribute<TD001DPE, Long> id;
	public static volatile SingularAttribute<TD001DPE, String> classeEstimationGes;
	public static volatile SingularAttribute<TD001DPE, String> typeVoie;
	public static volatile SingularAttribute<TD001DPE, String> codeInseeCommuneActualise;
	public static volatile SingularAttribute<TD001DPE, Long> anneeConstruction;
	public static volatile SingularAttribute<TD001DPE, TD006Batiment> td006Batiment;
	public static volatile SingularAttribute<TD001DPE, String> tv016Departement;
	public static volatile SingularAttribute<TD001DPE, String> numeroRue;
	public static volatile SingularAttribute<TD001DPE, String> codePostal;
	public static volatile SingularAttribute<TD001DPE, TD017ConsommationNeuf> td017ConsommationNeuf;
	public static volatile SingularAttribute<TD001DPE, LocalDate> dateReceptionDpe;
	public static volatile SingularAttribute<TD001DPE, String> nomMethodeDpe;
	public static volatile SingularAttribute<TD001DPE, String> etage;
	public static volatile SingularAttribute<TD001DPE, String> numeroDpe;
	public static volatile SingularAttribute<TD001DPE, String> tr002TypeBatiment;
	public static volatile SingularAttribute<TD001DPE, String> numeroLot;
	public static volatile SingularAttribute<TD001DPE, Double> consommationEnergie;
	public static volatile SingularAttribute<TD001DPE, String> codeInseeCommune;
	public static volatile SingularAttribute<TD001DPE, TD002Consommations> td002Consommations;

	public static final String COMMUNE = "commune";
	public static final String ARRONDISSEMENT = "arrondissement";
	public static final String CLASSE_CONSOMMATION_ENERGIE = "classeConsommationEnergie";
	public static final String ESTIMATION_GES = "estimationGes";
	public static final String ESCALIER = "escalier";
	public static final String BATIMENT = "batiment";
	public static final String TR001_MODELE_DPE = "tr001ModeleDpe";
	public static final String NOM_RUE = "nomRue";
	public static final String SURFACE_HABITABLE = "surfaceHabitable";
	public static final String CODE_INSEE_COMMUNE_CORRIGE = "codeInseeCommuneCorrige";
	public static final String PORTE = "porte";
	public static final String ID = "id";
	public static final String CLASSE_ESTIMATION_GES = "classeEstimationGes";
	public static final String TYPE_VOIE = "typeVoie";
	public static final String CODE_INSEE_COMMUNE_ACTUALISE = "codeInseeCommuneActualise";
	public static final String ANNEE_CONSTRUCTION = "anneeConstruction";
	public static final String TD006_BATIMENT = "td006Batiment";
	public static final String TV016_DEPARTEMENT = "tv016Departement";
	public static final String NUMERO_RUE = "numeroRue";
	public static final String CODE_POSTAL = "codePostal";
	public static final String TD017_CONSOMMATION_NEUF = "td017ConsommationNeuf";
	public static final String DATE_RECEPTION_DPE = "dateReceptionDpe";
	public static final String NOM_METHODE_DPE = "nomMethodeDpe";
	public static final String ETAGE = "etage";
	public static final String NUMERO_DPE = "numeroDpe";
	public static final String TR002_TYPE_BATIMENT = "tr002TypeBatiment";
	public static final String NUMERO_LOT = "numeroLot";
	public static final String CONSOMMATION_ENERGIE = "consommationEnergie";
	public static final String CODE_INSEE_COMMUNE = "codeInseeCommune";
	public static final String TD002_CONSOMMATIONS = "td002Consommations";

}

