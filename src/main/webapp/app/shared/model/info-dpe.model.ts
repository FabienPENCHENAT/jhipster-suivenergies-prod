import { Moment } from 'moment';
import { IClient } from 'app/shared/model/client.model';

export interface IInfoDPE {
  id?: number;
  numero?: string;
  adresse?: string;
  typeBatiment?: string;
  anneeConstruction?: number;
  surfaceHabitable?: number;
  energieChauffage?: string;
  energieEauChaude?: string;
  energiePhotovoltaique?: number;
  dateDPE?: Moment;
  classeConsoEnergie?: string;
  dpeJsonContentType?: string;
  dpeJson?: any;
  client?: IClient;
}

export class InfoDPE implements IInfoDPE {
  constructor(
    public id?: number,
    public numero?: string,
    public adresse?: string,
    public typeBatiment?: string,
    public anneeConstruction?: number,
    public surfaceHabitable?: number,
    public energieChauffage?: string,
    public energieEauChaude?: string,
    public energiePhotovoltaique?: number,
    public dateDPE?: Moment,
    public classeConsoEnergie?: string,
    public dpeJsonContentType?: string,
    public dpeJson?: any,
    public client?: IClient
  ) {}
}
