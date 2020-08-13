package com.suivenergies.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModeVie.class)
public abstract class ModeVie_ {

	public static volatile SingularAttribute<ModeVie, Boolean> presenceSoirWE;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceMatinSemaine;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceNuitWE;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceAMSemaine;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceMatinWE;
	public static volatile SingularAttribute<ModeVie, Integer> semainesAbsenceHiver;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceSoirSemaine;
	public static volatile SingularAttribute<ModeVie, Client> client;
	public static volatile SingularAttribute<ModeVie, Long> id;
	public static volatile SingularAttribute<ModeVie, Integer> nbPersonnes;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceAMWE;
	public static volatile SingularAttribute<ModeVie, Boolean> presenceNuitSemaine;
	public static volatile SingularAttribute<ModeVie, Integer> semainesAbsenceEte;

	public static final String PRESENCE_SOIR_WE = "presenceSoirWE";
	public static final String PRESENCE_MATIN_SEMAINE = "presenceMatinSemaine";
	public static final String PRESENCE_NUIT_WE = "presenceNuitWE";
	public static final String PRESENCE_AM_SEMAINE = "presenceAMSemaine";
	public static final String PRESENCE_MATIN_WE = "presenceMatinWE";
	public static final String SEMAINES_ABSENCE_HIVER = "semainesAbsenceHiver";
	public static final String PRESENCE_SOIR_SEMAINE = "presenceSoirSemaine";
	public static final String CLIENT = "client";
	public static final String ID = "id";
	public static final String NB_PERSONNES = "nbPersonnes";
	public static final String PRESENCE_AM_WE = "presenceAMWE";
	public static final String PRESENCE_NUIT_SEMAINE = "presenceNuitSemaine";
	public static final String SEMAINES_ABSENCE_ETE = "semainesAbsenceEte";

}

