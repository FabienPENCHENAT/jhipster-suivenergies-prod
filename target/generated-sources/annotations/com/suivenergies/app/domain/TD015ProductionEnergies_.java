package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD015ProductionEnergies.class)
public abstract class TD015ProductionEnergies_ {

	public static volatile SingularAttribute<TD015ProductionEnergies, Boolean> productionleEctriciteMicroEolienne;
	public static volatile SingularAttribute<TD015ProductionEnergies, Double> productionElectriciteCapteursPhotovoltaiques;
	public static volatile SingularAttribute<TD015ProductionEnergies, String> tr004TypeEnergie;
	public static volatile SingularAttribute<TD015ProductionEnergies, Double> productionCogeneration;
	public static volatile SingularAttribute<TD015ProductionEnergies, TD006Batiment> td006Batiment;
	public static volatile SingularAttribute<TD015ProductionEnergies, Long> id;

	public static final String PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE = "productionleEctriciteMicroEolienne";
	public static final String PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES = "productionElectriciteCapteursPhotovoltaiques";
	public static final String TR004_TYPE_ENERGIE = "tr004TypeEnergie";
	public static final String PRODUCTION_COGENERATION = "productionCogeneration";
	public static final String TD006_BATIMENT = "td006Batiment";
	public static final String ID = "id";

}

