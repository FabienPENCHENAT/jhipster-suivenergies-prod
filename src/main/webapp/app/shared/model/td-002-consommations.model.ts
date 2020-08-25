import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';

export interface ITD002Consommations {
  id?: number;
  tr006TypeUsage?: string;
  tr004TypEnergie?: string;
  tv042TarifEnergie?: number;
  consommationEnergieFinale?: number;
  consommationEnergiePrimaire?: number;
  fraisAnnuelsEnergie?: number;
  estEfface?: boolean;
  td001DPE?: ITD001DPE;
}

export class TD002Consommations implements ITD002Consommations {
  constructor(
    public id?: number,
    public tr006TypeUsage?: string,
    public tr004TypEnergie?: string,
    public tv042TarifEnergie?: number,
    public consommationEnergieFinale?: number,
    public consommationEnergiePrimaire?: number,
    public fraisAnnuelsEnergie?: number,
    public estEfface?: boolean,
    public td001DPE?: ITD001DPE
  ) {
    this.estEfface = this.estEfface || false;
  }
}
