package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD011InstalationChauffage.class)
public abstract class TD011InstalationChauffage_ {

	public static volatile SingularAttribute<TD011InstalationChauffage, TD012GenerateurChauffage> td012GenerateurChauffage;
	public static volatile SingularAttribute<TD011InstalationChauffage, TD006Batiment> td006Batiment;
	public static volatile SingularAttribute<TD011InstalationChauffage, Long> id;
	public static volatile SingularAttribute<TD011InstalationChauffage, Double> surfaceChauffee;
	public static volatile SingularAttribute<TD011InstalationChauffage, String> tr003TypeInstallationChauffage;

	public static final String TD012_GENERATEUR_CHAUFFAGE = "td012GenerateurChauffage";
	public static final String TD006_BATIMENT = "td006Batiment";
	public static final String ID = "id";
	public static final String SURFACE_CHAUFFEE = "surfaceChauffee";
	public static final String TR003_TYPE_INSTALLATION_CHAUFFAGE = "tr003TypeInstallationChauffage";

}

