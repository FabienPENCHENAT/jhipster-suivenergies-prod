import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

export interface ITD014GenerateurECS {
  id?: number;
  td013InstallationEcs?: string;
  tr004TypeEnergie?: string;
  volumeStockage?: number;
  td013InstalationECS?: ITD013InstalationECS;
}

export class TD014GenerateurECS implements ITD014GenerateurECS {
  constructor(
    public id?: number,
    public td013InstallationEcs?: string,
    public tr004TypeEnergie?: string,
    public volumeStockage?: number,
    public td013InstalationECS?: ITD013InstalationECS
  ) {}
}
