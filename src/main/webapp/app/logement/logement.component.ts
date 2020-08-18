import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder } from '@angular/forms';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { IFacture, Facture } from 'app/shared/model/facture.model';
import { EnergiesFacture } from 'app/shared/model/enumerations/energies-facture.model';
import { LogementService } from './logement.service';
import { InfoDPEService } from '../entities/info-dpe/info-dpe.service';
import { FactureService } from '../entities/facture/facture.service';

@Component({
  selector: 'jhi-logement',
  templateUrl: './logement.component.html',
})
export class LogementComponent implements OnInit {
  isSaving = false;
  idDPE?: number;
  infoDPE?: IInfoDPE;

  dpeForm = this.fb.group({
    numero: [''],
    adresse: [''],
    typeBatiment: [''],
    anneeConstruction: [''],
    surface: [''],
    energieChauffage: [''],
    energieEauChaude: [''],
    energiePhotovoltaique: [''],
  });

  factureForm = this.fb.group({
    elec2017: [''],
    elec2018: [''],
    elec2019: [''],
    gaz2017: [''],
    gaz2018: [''],
    gaz2019: [''],
    fioul2017: [''],
    fioul2018: [''],
    fioul2019: [''],
    bois2017: [''],
    bois2018: [''],
    bois2019: [''],
  });

  constructor(
    private logementService: LogementService,
    protected infoDPEService: InfoDPEService,
    protected factureService: FactureService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.infoDPEService.findLast().subscribe((dpe: HttpResponse<IInfoDPE>) => this.completeForm(dpe));
  }

  completeForm(dpe: HttpResponse<IInfoDPE>): void {
    if (dpe && dpe.body) {
      this.infoDPE = dpe.body;
    }
    if (this.infoDPE) {
      this.dpeForm.patchValue({
        numero: this.infoDPE.numero,
        adresse: this.infoDPE.adresse,
        typeBatiment: this.infoDPE.typeBatiment,
        anneeConstruction: this.infoDPE.anneeConstruction,
        surface: this.infoDPE.surfaceHabitable,
        energieChauffage: this.infoDPE.energieChauffage,
        energieEauChaude: this.infoDPE.energieEauChaude,
        energiePhotovoltaique: this.infoDPE.energiePhotovoltaique,
      });
    }
  }

  saveDPE(numDPEToLoad: String): void {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.infoDPEService.downloadInfoDPE(numDPEToLoad));
  }

  saveConso(): void {
    this.isSaving = true;
    if (this.factureForm.get(['elec2019']) !== undefined) {
      const facture = this.createFromForm();
      if (facture.id !== undefined) {
        this.subscribeToSaveResponse(this.factureService.update(facture));
      } else {
        this.subscribeToSaveResponse(this.factureService.create(facture));
      }
    }
  }

  previousState(): void {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfoDPE>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  private createFromForm(): IFacture {
    return {
      ...new Facture(),
      id: this.factureForm.get(['id'])!.value,
      // type: this.factureForm.get(['type'])!.value,
      type: EnergiesFacture.ELEC,
      // annee: this.factureForm.get(['annee'])!.value,
      annee: 2019,
      quantite: this.factureForm.get(['elec2019'])!.value,
      client: this.factureForm.get(['client'])!.value,
    };
  }
}
