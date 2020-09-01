import { IConfort } from 'app/shared/model/confort.model';

export interface IElectromenager {
  id?: number;
  nom?: string;
  note?: string;
  byDefault?: boolean;
  consoAnnuelle?: number;
  conforts?: IConfort[];
}

export class Electromenager implements IElectromenager {
  constructor(
    public id?: number,
    public nom?: string,
    public note?: string,
    public byDefault?: boolean,
    public consoAnnuelle?: number,
    public conforts?: IConfort[]
  ) {
    this.byDefault = this.byDefault || false;
  }
}
