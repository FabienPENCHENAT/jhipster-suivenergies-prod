import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';

export interface ITD006Batiment {
  id?: number;
  besoinChauffage?: number;
  deperditionEnveloppe?: number;
  deperditionRenouvellementAir?: number;
  altitude?: number;
  nombreNiveau?: number;
  hspMoyenne?: number;
  td007ParoiOpaque?: ITD007ParoiOpaque;
  td010PontsThermiques?: ITD010PontsThermiques;
  td011InstalationChauffage?: ITD011InstalationChauffage;
  td013InstalationECS?: ITD013InstalationECS;
  td015ProductionEnergies?: ITD015ProductionEnergies;
  td001DPE?: ITD001DPE;
}

export class TD006Batiment implements ITD006Batiment {
  constructor(
    public id?: number,
    public besoinChauffage?: number,
    public deperditionEnveloppe?: number,
    public deperditionRenouvellementAir?: number,
    public altitude?: number,
    public nombreNiveau?: number,
    public hspMoyenne?: number,
    public td007ParoiOpaque?: ITD007ParoiOpaque,
    public td010PontsThermiques?: ITD010PontsThermiques,
    public td011InstalationChauffage?: ITD011InstalationChauffage,
    public td013InstalationECS?: ITD013InstalationECS,
    public td015ProductionEnergies?: ITD015ProductionEnergies,
    public td001DPE?: ITD001DPE
  ) {}
}
