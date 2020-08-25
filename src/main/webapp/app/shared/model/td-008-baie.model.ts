import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

export interface ITD008Baie {
  id?: number;
  reference?: string;
  td008Baie?: string;
  deperdition?: number;
  tv009CoefficientTransmissionThermiqueVitrage?: number;
  tv012CoefTransmissionThermiqueBaieProtectionSolaire?: number;
  surface?: number;
  perimetre?: number;
  tv013ValeurPontThermique?: number;
  td007ParoiOpaque?: ITD007ParoiOpaque;
}

export class TD008Baie implements ITD008Baie {
  constructor(
    public id?: number,
    public reference?: string,
    public td008Baie?: string,
    public deperdition?: number,
    public tv009CoefficientTransmissionThermiqueVitrage?: number,
    public tv012CoefTransmissionThermiqueBaieProtectionSolaire?: number,
    public surface?: number,
    public perimetre?: number,
    public tv013ValeurPontThermique?: number,
    public td007ParoiOpaque?: ITD007ParoiOpaque
  ) {}
}
