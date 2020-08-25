import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

export interface ITD012GenerateurChauffage {
  id?: number;
  systemeChauffageCogeneration?: string;
  td011InstallationChauffage?: string;
  tr004TypeEnergie?: string;
  consommationChauffage?: number;
  td011InstalationChauffage?: ITD011InstalationChauffage;
}

export class TD012GenerateurChauffage implements ITD012GenerateurChauffage {
  constructor(
    public id?: number,
    public systemeChauffageCogeneration?: string,
    public td011InstallationChauffage?: string,
    public tr004TypeEnergie?: string,
    public consommationChauffage?: number,
    public td011InstalationChauffage?: ITD011InstalationChauffage
  ) {}
}
