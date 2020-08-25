import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

export interface ITD010PontsThermiques {
  id?: number;
  longueur?: number;
  tv013ValeurPontThermique?: number;
  td006Batiment?: ITD006Batiment;
}

export class TD010PontsThermiques implements ITD010PontsThermiques {
  constructor(
    public id?: number,
    public longueur?: number,
    public tv013ValeurPontThermique?: number,
    public td006Batiment?: ITD006Batiment
  ) {}
}
