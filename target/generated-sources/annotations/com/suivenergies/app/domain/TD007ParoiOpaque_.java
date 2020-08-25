package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD007ParoiOpaque.class)
public abstract class TD007ParoiOpaque_ {

	public static volatile SingularAttribute<TD007ParoiOpaque, String> reference;
	public static volatile SingularAttribute<TD007ParoiOpaque, Double> resistanceThermiqueIsolation;
	public static volatile SingularAttribute<TD007ParoiOpaque, TD008Baie> td008Baie;
	public static volatile SingularAttribute<TD007ParoiOpaque, Double> deperditionThermique;
	public static volatile SingularAttribute<TD007ParoiOpaque, Double> coefficientTransmissionThermiqueParoi;
	public static volatile SingularAttribute<TD007ParoiOpaque, Double> epaisseurIsolation;
	public static volatile SingularAttribute<TD007ParoiOpaque, TD006Batiment> td006Batiment;
	public static volatile SingularAttribute<TD007ParoiOpaque, Long> id;
	public static volatile SingularAttribute<TD007ParoiOpaque, Double> surfaceParoi;
	public static volatile SingularAttribute<TD007ParoiOpaque, String> tr014TypeParoisOpaque;

	public static final String REFERENCE = "reference";
	public static final String RESISTANCE_THERMIQUE_ISOLATION = "resistanceThermiqueIsolation";
	public static final String TD008_BAIE = "td008Baie";
	public static final String DEPERDITION_THERMIQUE = "deperditionThermique";
	public static final String COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI = "coefficientTransmissionThermiqueParoi";
	public static final String EPAISSEUR_ISOLATION = "epaisseurIsolation";
	public static final String TD006_BATIMENT = "td006Batiment";
	public static final String ID = "id";
	public static final String SURFACE_PAROI = "surfaceParoi";
	public static final String TR014_TYPE_PAROIS_OPAQUE = "tr014TypeParoisOpaque";

}

