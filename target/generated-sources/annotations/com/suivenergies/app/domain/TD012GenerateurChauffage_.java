package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TD012GenerateurChauffage.class)
public abstract class TD012GenerateurChauffage_ {

	public static volatile SingularAttribute<TD012GenerateurChauffage, String> td011InstallationChauffage;
	public static volatile SingularAttribute<TD012GenerateurChauffage, String> tr004TypeEnergie;
	public static volatile SingularAttribute<TD012GenerateurChauffage, Long> id;
	public static volatile SingularAttribute<TD012GenerateurChauffage, String> systemeChauffageCogeneration;
	public static volatile SingularAttribute<TD012GenerateurChauffage, TD011InstalationChauffage> td011InstalationChauffage;
	public static volatile SingularAttribute<TD012GenerateurChauffage, Double> consommationChauffage;

	public static final String TD011_INSTALLATION_CHAUFFAGE = "td011InstallationChauffage";
	public static final String TR004_TYPE_ENERGIE = "tr004TypeEnergie";
	public static final String ID = "id";
	public static final String SYSTEME_CHAUFFAGE_COGENERATION = "systemeChauffageCogeneration";
	public static final String TD011_INSTALATION_CHAUFFAGE = "td011InstalationChauffage";
	public static final String CONSOMMATION_CHAUFFAGE = "consommationChauffage";

}

