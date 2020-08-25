package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD017ConsommationNeuf.class)
public abstract class TD017ConsommationNeuf_ {

	public static volatile SingularAttribute<TD017ConsommationNeuf, String> tv045ConversionKwhCo2;
	public static volatile SingularAttribute<TD017ConsommationNeuf, TD001DPE> td001DPE;
	public static volatile SingularAttribute<TD017ConsommationNeuf, Double> consommationEnergieFinale;
	public static volatile SingularAttribute<TD017ConsommationNeuf, String> tr004TypeEnergie;
	public static volatile SingularAttribute<TD017ConsommationNeuf, String> tv046EvaluationContenuCo2Reseaux;
	public static volatile SingularAttribute<TD017ConsommationNeuf, Long> id;
	public static volatile SingularAttribute<TD017ConsommationNeuf, String> tv044ConversionKwhEnergiesRelevees;
	public static volatile SingularAttribute<TD017ConsommationNeuf, Double> consommationEnergiePrimaire;
	public static volatile SingularAttribute<TD017ConsommationNeuf, Double> fraisAnnuelsEnergie;
	public static volatile SingularAttribute<TD017ConsommationNeuf, String> tr006TypeUsage;

	public static final String TV045_CONVERSION_KWH_CO2 = "tv045ConversionKwhCo2";
	public static final String TD001_DP_E = "td001DPE";
	public static final String CONSOMMATION_ENERGIE_FINALE = "consommationEnergieFinale";
	public static final String TR004_TYPE_ENERGIE = "tr004TypeEnergie";
	public static final String TV046_EVALUATION_CONTENU_CO2_RESEAUX = "tv046EvaluationContenuCo2Reseaux";
	public static final String ID = "id";
	public static final String TV044_CONVERSION_KWH_ENERGIES_RELEVEES = "tv044ConversionKwhEnergiesRelevees";
	public static final String CONSOMMATION_ENERGIE_PRIMAIRE = "consommationEnergiePrimaire";
	public static final String FRAIS_ANNUELS_ENERGIE = "fraisAnnuelsEnergie";
	public static final String TR006_TYPE_USAGE = "tr006TypeUsage";

}

