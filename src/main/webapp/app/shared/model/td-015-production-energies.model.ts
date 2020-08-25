import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

export interface ITD015ProductionEnergies {
  id?: number;
  tr004TypeEnergie?: string;
  productionElectriciteCapteursPhotovoltaiques?: number;
  productionleEctriciteMicroEolienne?: boolean;
  productionCogeneration?: number;
  td006Batiment?: ITD006Batiment;
}

export class TD015ProductionEnergies implements ITD015ProductionEnergies {
  constructor(
    public id?: number,
    public tr004TypeEnergie?: string,
    public productionElectriciteCapteursPhotovoltaiques?: number,
    public productionleEctriciteMicroEolienne?: boolean,
    public productionCogeneration?: number,
    public td006Batiment?: ITD006Batiment
  ) {
    this.productionleEctriciteMicroEolienne = this.productionleEctriciteMicroEolienne || false;
  }
}
