package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD006Batiment.class)
public abstract class TD006Batiment_ {

	public static volatile SingularAttribute<TD006Batiment, TD010PontsThermiques> td010PontsThermiques;
	public static volatile SingularAttribute<TD006Batiment, Double> altitude;
	public static volatile SingularAttribute<TD006Batiment, TD001DPE> td001DPE;
	public static volatile SingularAttribute<TD006Batiment, TD007ParoiOpaque> td007ParoiOpaque;
	public static volatile SingularAttribute<TD006Batiment, TD013InstalationECS> td013InstalationECS;
	public static volatile SingularAttribute<TD006Batiment, Double> hspMoyenne;
	public static volatile SingularAttribute<TD006Batiment, Double> nombreNiveau;
	public static volatile SingularAttribute<TD006Batiment, TD011InstalationChauffage> td011InstalationChauffage;
	public static volatile SingularAttribute<TD006Batiment, Double> deperditionRenouvellementAir;
	public static volatile SingularAttribute<TD006Batiment, Double> besoinChauffage;
	public static volatile SingularAttribute<TD006Batiment, Double> deperditionEnveloppe;
	public static volatile SingularAttribute<TD006Batiment, Long> id;
	public static volatile SingularAttribute<TD006Batiment, TD015ProductionEnergies> td015ProductionEnergies;

	public static final String TD010_PONTS_THERMIQUES = "td010PontsThermiques";
	public static final String ALTITUDE = "altitude";
	public static final String TD001_DP_E = "td001DPE";
	public static final String TD007_PAROI_OPAQUE = "td007ParoiOpaque";
	public static final String TD013_INSTALATION_EC_S = "td013InstalationECS";
	public static final String HSP_MOYENNE = "hspMoyenne";
	public static final String NOMBRE_NIVEAU = "nombreNiveau";
	public static final String TD011_INSTALATION_CHAUFFAGE = "td011InstalationChauffage";
	public static final String DEPERDITION_RENOUVELLEMENT_AIR = "deperditionRenouvellementAir";
	public static final String BESOIN_CHAUFFAGE = "besoinChauffage";
	public static final String DEPERDITION_ENVELOPPE = "deperditionEnveloppe";
	public static final String ID = "id";
	public static final String TD015_PRODUCTION_ENERGIES = "td015ProductionEnergies";

}

