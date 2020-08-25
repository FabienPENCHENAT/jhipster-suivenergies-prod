import { Moment } from 'moment';
import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

export interface ITD001DPE {
  id?: number;
  numeroDpe?: string;
  tr001ModeleDpe?: string;
  nomMethodeDpe?: string;
  consommationEnergie?: number;
  classeConsommationEnergie?: string;
  estimationGes?: number;
  classeEstimationGes?: string;
  tr002TypeBatiment?: string;
  anneeConstruction?: number;
  surfaceHabitable?: number;
  tv016Departement?: string;
  commune?: string;
  arrondissement?: string;
  typeVoie?: string;
  nomRue?: string;
  numeroRue?: string;
  batiment?: string;
  escalier?: string;
  etage?: string;
  porte?: string;
  codePostal?: string;
  codeInseeCommune?: string;
  codeInseeCommuneActualise?: string;
  codeInseeCommuneCorrige?: string;
  numeroLot?: string;
  dateReceptionDpe?: Moment;
  td002Consommations?: ITD002Consommations;
  td006Batiment?: ITD006Batiment;
  td017ConsommationNeuf?: ITD017ConsommationNeuf;
}

export class TD001DPE implements ITD001DPE {
  constructor(
    public id?: number,
    public numeroDpe?: string,
    public tr001ModeleDpe?: string,
    public nomMethodeDpe?: string,
    public consommationEnergie?: number,
    public classeConsommationEnergie?: string,
    public estimationGes?: number,
    public classeEstimationGes?: string,
    public tr002TypeBatiment?: string,
    public anneeConstruction?: number,
    public surfaceHabitable?: number,
    public tv016Departement?: string,
    public commune?: string,
    public arrondissement?: string,
    public typeVoie?: string,
    public nomRue?: string,
    public numeroRue?: string,
    public batiment?: string,
    public escalier?: string,
    public etage?: string,
    public porte?: string,
    public codePostal?: string,
    public codeInseeCommune?: string,
    public codeInseeCommuneActualise?: string,
    public codeInseeCommuneCorrige?: string,
    public numeroLot?: string,
    public dateReceptionDpe?: Moment,
    public td002Consommations?: ITD002Consommations,
    public td006Batiment?: ITD006Batiment,
    public td017ConsommationNeuf?: ITD017ConsommationNeuf
  ) {}
}
