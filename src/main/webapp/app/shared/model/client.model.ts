import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { IFacture } from 'app/shared/model/facture.model';
import { IModeVie } from 'app/shared/model/mode-vie.model';
import { IUser } from 'app/core/user/user.model';

export interface IClient {
  id?: number;
  infoDpes?: IInfoDPE[];
  factures?: IFacture[];
  modeVies?: IModeVie[];
  users?: IUser[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public infoDpes?: IInfoDPE[],
    public factures?: IFacture[],
    public modeVies?: IModeVie[],
    public users?: IUser[]
  ) {}
}
