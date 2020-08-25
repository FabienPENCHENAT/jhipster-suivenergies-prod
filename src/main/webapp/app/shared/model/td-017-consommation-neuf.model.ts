import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';

export interface ITD017ConsommationNeuf {
  id?: number;
  tr004TypeEnergie?: string;
  tr006TypeUsage?: string;
  tv044ConversionKwhEnergiesRelevees?: string;
  tv045ConversionKwhCo2?: string;
  tv046EvaluationContenuCo2Reseaux?: string;
  consommationEnergieFinale?: number;
  consommationEnergiePrimaire?: number;
  fraisAnnuelsEnergie?: number;
  td001DPE?: ITD001DPE;
}

export class TD017ConsommationNeuf implements ITD017ConsommationNeuf {
  constructor(
    public id?: number,
    public tr004TypeEnergie?: string,
    public tr006TypeUsage?: string,
    public tv044ConversionKwhEnergiesRelevees?: string,
    public tv045ConversionKwhCo2?: string,
    public tv046EvaluationContenuCo2Reseaux?: string,
    public consommationEnergieFinale?: number,
    public consommationEnergiePrimaire?: number,
    public fraisAnnuelsEnergie?: number,
    public td001DPE?: ITD001DPE
  ) {}
}
