import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

export interface ITD013InstalationECS {
  id?: number;
  tr005TypeInstallationEcs?: string;
  becs?: number;
  tv039FormuleBecs?: string;
  surfaceAlimentee?: number;
  td014GenerateurECS?: ITD014GenerateurECS;
  td006Batiment?: ITD006Batiment;
}

export class TD013InstalationECS implements ITD013InstalationECS {
  constructor(
    public id?: number,
    public tr005TypeInstallationEcs?: string,
    public becs?: number,
    public tv039FormuleBecs?: string,
    public surfaceAlimentee?: number,
    public td014GenerateurECS?: ITD014GenerateurECS,
    public td006Batiment?: ITD006Batiment
  ) {}
}
