import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { IFacture } from 'app/shared/model/facture.model';
import { IModeVie } from 'app/shared/model/mode-vie.model';
import { IUser } from 'app/core/user/user.model';

export interface IClient {
  id?: number;
  adresse?: string;
  codePostal?: number;
  infoDPES?: IInfoDPE[];
  factures?: IFacture[];
  modeVies?: IModeVie[];
  users?: IUser[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public adresse?: string,
    public codePostal?: number,
    public infoDPES?: IInfoDPE[],
    public factures?: IFacture[],
    public modeVies?: IModeVie[],
    public users?: IUser[]
  ) {}
}
