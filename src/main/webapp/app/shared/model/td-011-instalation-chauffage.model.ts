import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

export interface ITD011InstalationChauffage {
  id?: number;
  tr003TypeInstallationChauffage?: string;
  surfaceChauffee?: number;
  td012GenerateurChauffage?: ITD012GenerateurChauffage;
  td006Batiment?: ITD006Batiment;
}

export class TD011InstalationChauffage implements ITD011InstalationChauffage {
  constructor(
    public id?: number,
    public tr003TypeInstallationChauffage?: string,
    public surfaceChauffee?: number,
    public td012GenerateurChauffage?: ITD012GenerateurChauffage,
    public td006Batiment?: ITD006Batiment
  ) {}
}
