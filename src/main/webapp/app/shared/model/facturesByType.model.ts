import { IFacture } from 'app/shared/model/facture.model';

export interface IFacturesByType {
  type?: String;
  factures?: IFacture[];
}

export class FacturesByType implements IFacturesByType {
  constructor(public type?: String, public factures?: IFacture[]) {}
}
