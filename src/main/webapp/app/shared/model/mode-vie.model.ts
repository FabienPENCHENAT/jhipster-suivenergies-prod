import { IClient } from 'app/shared/model/client.model';

export interface IModeVie {
  id?: number;
  nbPersonnes?: number;
  presenceMatinSemaine?: boolean;
  presenceMatinWE?: boolean;
  presenceAMSemaine?: boolean;
  presenceAMWE?: boolean;
  presenceSoirSemaine?: boolean;
  presenceSoirWE?: boolean;
  presenceNuitSemaine?: boolean;
  presenceNuitWE?: boolean;
  semainesAbsenceHiver?: number;
  semainesAbsenceEte?: number;
  client?: IClient;
}

export class ModeVie implements IModeVie {
  constructor(
    public id?: number,
    public nbPersonnes?: number,
    public presenceMatinSemaine?: boolean,
    public presenceMatinWE?: boolean,
    public presenceAMSemaine?: boolean,
    public presenceAMWE?: boolean,
    public presenceSoirSemaine?: boolean,
    public presenceSoirWE?: boolean,
    public presenceNuitSemaine?: boolean,
    public presenceNuitWE?: boolean,
    public semainesAbsenceHiver?: number,
    public semainesAbsenceEte?: number,
    public client?: IClient
  ) {
    this.presenceMatinSemaine = this.presenceMatinSemaine || false;
    this.presenceMatinWE = this.presenceMatinWE || false;
    this.presenceAMSemaine = this.presenceAMSemaine || false;
    this.presenceAMWE = this.presenceAMWE || false;
    this.presenceSoirSemaine = this.presenceSoirSemaine || false;
    this.presenceSoirWE = this.presenceSoirWE || false;
    this.presenceNuitSemaine = this.presenceNuitSemaine || false;
    this.presenceNuitWE = this.presenceNuitWE || false;
  }
}
