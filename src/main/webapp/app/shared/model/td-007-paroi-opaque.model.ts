import { ITD008Baie } from 'app/shared/model/td-008-baie.model';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

export interface ITD007ParoiOpaque {
  id?: number;
  tr014TypeParoisOpaque?: string;
  reference?: string;
  deperditionThermique?: number;
  coefficientTransmissionThermiqueParoi?: number;
  resistanceThermiqueIsolation?: number;
  epaisseurIsolation?: number;
  surfaceParoi?: number;
  td008Baie?: ITD008Baie;
  td006Batiment?: ITD006Batiment;
}

export class TD007ParoiOpaque implements ITD007ParoiOpaque {
  constructor(
    public id?: number,
    public tr014TypeParoisOpaque?: string,
    public reference?: string,
    public deperditionThermique?: number,
    public coefficientTransmissionThermiqueParoi?: number,
    public resistanceThermiqueIsolation?: number,
    public epaisseurIsolation?: number,
    public surfaceParoi?: number,
    public td008Baie?: ITD008Baie,
    public td006Batiment?: ITD006Batiment
  ) {}
}
