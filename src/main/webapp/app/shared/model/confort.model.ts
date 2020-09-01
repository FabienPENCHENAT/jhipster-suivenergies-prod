import { IElectromenager } from 'app/shared/model/electromenager.model';
import { IClient } from 'app/shared/model/client.model';

export interface IConfort {
  id?: number;
  installationElectrique?: boolean;
  installationGaz?: boolean;
  installationHumidite?: boolean;
  installationPortesFenetres?: boolean;
  chauffageHiver?: boolean;
  surfaceChauffee?: number;
  temperatureHiverSejour?: number;
  temperatureHiverChambres?: number;
  climEte?: boolean;
  temperatureEteSejour?: number;
  temperatureEteChambres?: number;
  electromenagers?: IElectromenager[];
  client?: IClient;
}

export class Confort implements IConfort {
  constructor(
    public id?: number,
    public installationElectrique?: boolean,
    public installationGaz?: boolean,
    public installationHumidite?: boolean,
    public installationPortesFenetres?: boolean,
    public chauffageHiver?: boolean,
    public surfaceChauffee?: number,
    public temperatureHiverSejour?: number,
    public temperatureHiverChambres?: number,
    public climEte?: boolean,
    public temperatureEteSejour?: number,
    public temperatureEteChambres?: number,
    public electromenagers?: IElectromenager[],
    public client?: IClient
  ) {
    this.installationElectrique = this.installationElectrique || false;
    this.installationGaz = this.installationGaz || false;
    this.installationHumidite = this.installationHumidite || false;
    this.installationPortesFenetres = this.installationPortesFenetres || false;
    this.chauffageHiver = this.chauffageHiver || false;
    this.climEte = this.climEte || false;
  }
}
