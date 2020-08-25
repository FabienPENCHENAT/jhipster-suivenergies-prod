package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD008Baie.class)
public abstract class TD008Baie_ {

	public static volatile SingularAttribute<TD008Baie, String> reference;
	public static volatile SingularAttribute<TD008Baie, Double> tv012CoefTransmissionThermiqueBaieProtectionSolaire;
	public static volatile SingularAttribute<TD008Baie, String> td008Baie;
	public static volatile SingularAttribute<TD008Baie, Double> surface;
	public static volatile SingularAttribute<TD008Baie, Double> tv009CoefficientTransmissionThermiqueVitrage;
	public static volatile SingularAttribute<TD008Baie, Double> perimetre;
	public static volatile SingularAttribute<TD008Baie, TD007ParoiOpaque> td007ParoiOpaque;
	public static volatile SingularAttribute<TD008Baie, Double> deperdition;
	public static volatile SingularAttribute<TD008Baie, Long> id;
	public static volatile SingularAttribute<TD008Baie, Double> tv013ValeurPontThermique;

	public static final String REFERENCE = "reference";
	public static final String TV012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE = "tv012CoefTransmissionThermiqueBaieProtectionSolaire";
	public static final String TD008_BAIE = "td008Baie";
	public static final String SURFACE = "surface";
	public static final String TV009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE = "tv009CoefficientTransmissionThermiqueVitrage";
	public static final String PERIMETRE = "perimetre";
	public static final String TD007_PAROI_OPAQUE = "td007ParoiOpaque";
	public static final String DEPERDITION = "deperdition";
	public static final String ID = "id";
	public static final String TV013_VALEUR_PONT_THERMIQUE = "tv013ValeurPontThermique";

}

