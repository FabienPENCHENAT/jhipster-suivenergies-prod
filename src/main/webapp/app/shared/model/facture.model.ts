import { IClient } from 'app/shared/model/client.model';
import { EnergiesFacture } from 'app/shared/model/enumerations/energies-facture.model';

export interface IFacture {
  id?: number;
  type?: EnergiesFacture;
  annee?: number;
  quantite?: number;
  montant?: number;
  client?: IClient;
}

export class Facture implements IFacture {
  constructor(
    public id?: number,
    public type?: EnergiesFacture,
    public annee?: number,
    public quantite?: number,
    public montant?: number,
    public client?: IClient
  ) {}
}
