package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD002Consommations.class)
public abstract class TD002Consommations_ {

	public static volatile SingularAttribute<TD002Consommations, Boolean> estEfface;
	public static volatile SingularAttribute<TD002Consommations, Double> tv042TarifEnergie;
	public static volatile SingularAttribute<TD002Consommations, TD001DPE> td001DPE;
	public static volatile SingularAttribute<TD002Consommations, Double> consommationEnergieFinale;
	public static volatile SingularAttribute<TD002Consommations, String> tr004TypEnergie;
	public static volatile SingularAttribute<TD002Consommations, Long> id;
	public static volatile SingularAttribute<TD002Consommations, Double> consommationEnergiePrimaire;
	public static volatile SingularAttribute<TD002Consommations, Double> fraisAnnuelsEnergie;
	public static volatile SingularAttribute<TD002Consommations, String> tr006TypeUsage;

	public static final String EST_EFFACE = "estEfface";
	public static final String TV042_TARIF_ENERGIE = "tv042TarifEnergie";
	public static final String TD001_DP_E = "td001DPE";
	public static final String CONSOMMATION_ENERGIE_FINALE = "consommationEnergieFinale";
	public static final String TR004_TYP_ENERGIE = "tr004TypEnergie";
	public static final String ID = "id";
	public static final String CONSOMMATION_ENERGIE_PRIMAIRE = "consommationEnergiePrimaire";
	public static final String FRAIS_ANNUELS_ENERGIE = "fraisAnnuelsEnergie";
	public static final String TR006_TYPE_USAGE = "tr006TypeUsage";

}

