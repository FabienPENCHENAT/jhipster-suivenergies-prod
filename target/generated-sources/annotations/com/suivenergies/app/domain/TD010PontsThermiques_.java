package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD010PontsThermiques.class)
public abstract class TD010PontsThermiques_ {

	public static volatile SingularAttribute<TD010PontsThermiques, Long> longueur;
	public static volatile SingularAttribute<TD010PontsThermiques, TD006Batiment> td006Batiment;
	public static volatile SingularAttribute<TD010PontsThermiques, Long> id;
	public static volatile SingularAttribute<TD010PontsThermiques, Double> tv013ValeurPontThermique;

	public static final String LONGUEUR = "longueur";
	public static final String TD006_BATIMENT = "td006Batiment";
	public static final String ID = "id";
	public static final String TV013_VALEUR_PONT_THERMIQUE = "tv013ValeurPontThermique";

}

