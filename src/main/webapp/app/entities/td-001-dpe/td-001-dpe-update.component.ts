import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITD001DPE, TD001DPE } from 'app/shared/model/td-001-dpe.model';
import { TD001DPEService } from './td-001-dpe.service';
import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { TD002ConsommationsService } from 'app/entities/td-002-consommations/td-002-consommations.service';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { TD006BatimentService } from 'app/entities/td-006-batiment/td-006-batiment.service';
import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';
import { TD017ConsommationNeufService } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.service';

type SelectableEntity = ITD002Consommations | ITD006Batiment | ITD017ConsommationNeuf;

@Component({
  selector: 'jhi-td-001-dpe-update',
  templateUrl: './td-001-dpe-update.component.html',
})
export class TD001DPEUpdateComponent implements OnInit {
  isSaving = false;
  td002consommations: ITD002Consommations[] = [];
  td006batiments: ITD006Batiment[] = [];
  td017consommationneufs: ITD017ConsommationNeuf[] = [];
  dateReceptionDpeDp: any;

  editForm = this.fb.group({
    id: [],
    numeroDpe: [],
    tr001ModeleDpe: [],
    nomMethodeDpe: [],
    consommationEnergie: [],
    classeConsommationEnergie: [],
    estimationGes: [],
    classeEstimationGes: [],
    tr002TypeBatiment: [],
    anneeConstruction: [],
    surfaceHabitable: [],
    tv016Departement: [],
    commune: [],
    arrondissement: [],
    typeVoie: [],
    nomRue: [],
    numeroRue: [],
    batiment: [],
    escalier: [],
    etage: [],
    porte: [],
    codePostal: [],
    codeInseeCommune: [],
    codeInseeCommuneActualise: [],
    codeInseeCommuneCorrige: [],
    numeroLot: [],
    dateReceptionDpe: [],
    td002Consommations: [],
    td006Batiment: [],
    td017ConsommationNeuf: [],
  });

  constructor(
    protected tD001DPEService: TD001DPEService,
    protected tD002ConsommationsService: TD002ConsommationsService,
    protected tD006BatimentService: TD006BatimentService,
    protected tD017ConsommationNeufService: TD017ConsommationNeufService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD001DPE }) => {
      this.updateForm(tD001DPE);

      this.tD002ConsommationsService
        .query({ filter: 'td001dpe-is-null' })
        .pipe(
          map((res: HttpResponse<ITD002Consommations[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD002Consommations[]) => {
          if (!tD001DPE.td002Consommations || !tD001DPE.td002Consommations.id) {
            this.td002consommations = resBody;
          } else {
            this.tD002ConsommationsService
              .find(tD001DPE.td002Consommations.id)
              .pipe(
                map((subRes: HttpResponse<ITD002Consommations>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD002Consommations[]) => (this.td002consommations = concatRes));
          }
        });

      this.tD006BatimentService
        .query({ filter: 'td001dpe-is-null' })
        .pipe(
          map((res: HttpResponse<ITD006Batiment[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD006Batiment[]) => {
          if (!tD001DPE.td006Batiment || !tD001DPE.td006Batiment.id) {
            this.td006batiments = resBody;
          } else {
            this.tD006BatimentService
              .find(tD001DPE.td006Batiment.id)
              .pipe(
                map((subRes: HttpResponse<ITD006Batiment>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD006Batiment[]) => (this.td006batiments = concatRes));
          }
        });

      this.tD017ConsommationNeufService
        .query({ filter: 'td001dpe-is-null' })
        .pipe(
          map((res: HttpResponse<ITD017ConsommationNeuf[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD017ConsommationNeuf[]) => {
          if (!tD001DPE.td017ConsommationNeuf || !tD001DPE.td017ConsommationNeuf.id) {
            this.td017consommationneufs = resBody;
          } else {
            this.tD017ConsommationNeufService
              .find(tD001DPE.td017ConsommationNeuf.id)
              .pipe(
                map((subRes: HttpResponse<ITD017ConsommationNeuf>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD017ConsommationNeuf[]) => (this.td017consommationneufs = concatRes));
          }
        });
    });
  }

  updateForm(tD001DPE: ITD001DPE): void {
    this.editForm.patchValue({
      id: tD001DPE.id,
      numeroDpe: tD001DPE.numeroDpe,
      tr001ModeleDpe: tD001DPE.tr001ModeleDpe,
      nomMethodeDpe: tD001DPE.nomMethodeDpe,
      consommationEnergie: tD001DPE.consommationEnergie,
      classeConsommationEnergie: tD001DPE.classeConsommationEnergie,
      estimationGes: tD001DPE.estimationGes,
      classeEstimationGes: tD001DPE.classeEstimationGes,
      tr002TypeBatiment: tD001DPE.tr002TypeBatiment,
      anneeConstruction: tD001DPE.anneeConstruction,
      surfaceHabitable: tD001DPE.surfaceHabitable,
      tv016Departement: tD001DPE.tv016Departement,
      commune: tD001DPE.commune,
      arrondissement: tD001DPE.arrondissement,
      typeVoie: tD001DPE.typeVoie,
      nomRue: tD001DPE.nomRue,
      numeroRue: tD001DPE.numeroRue,
      batiment: tD001DPE.batiment,
      escalier: tD001DPE.escalier,
      etage: tD001DPE.etage,
      porte: tD001DPE.porte,
      codePostal: tD001DPE.codePostal,
      codeInseeCommune: tD001DPE.codeInseeCommune,
      codeInseeCommuneActualise: tD001DPE.codeInseeCommuneActualise,
      codeInseeCommuneCorrige: tD001DPE.codeInseeCommuneCorrige,
      numeroLot: tD001DPE.numeroLot,
      dateReceptionDpe: tD001DPE.dateReceptionDpe,
      td002Consommations: tD001DPE.td002Consommations,
      td006Batiment: tD001DPE.td006Batiment,
      td017ConsommationNeuf: tD001DPE.td017ConsommationNeuf,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD001DPE = this.createFromForm();
    if (tD001DPE.id !== undefined) {
      this.subscribeToSaveResponse(this.tD001DPEService.update(tD001DPE));
    } else {
      this.subscribeToSaveResponse(this.tD001DPEService.create(tD001DPE));
    }
  }

  private createFromForm(): ITD001DPE {
    return {
      ...new TD001DPE(),
      id: this.editForm.get(['id'])!.value,
      numeroDpe: this.editForm.get(['numeroDpe'])!.value,
      tr001ModeleDpe: this.editForm.get(['tr001ModeleDpe'])!.value,
      nomMethodeDpe: this.editForm.get(['nomMethodeDpe'])!.value,
      consommationEnergie: this.editForm.get(['consommationEnergie'])!.value,
      classeConsommationEnergie: this.editForm.get(['classeConsommationEnergie'])!.value,
      estimationGes: this.editForm.get(['estimationGes'])!.value,
      classeEstimationGes: this.editForm.get(['classeEstimationGes'])!.value,
      tr002TypeBatiment: this.editForm.get(['tr002TypeBatiment'])!.value,
      anneeConstruction: this.editForm.get(['anneeConstruction'])!.value,
      surfaceHabitable: this.editForm.get(['surfaceHabitable'])!.value,
      tv016Departement: this.editForm.get(['tv016Departement'])!.value,
      commune: this.editForm.get(['commune'])!.value,
      arrondissement: this.editForm.get(['arrondissement'])!.value,
      typeVoie: this.editForm.get(['typeVoie'])!.value,
      nomRue: this.editForm.get(['nomRue'])!.value,
      numeroRue: this.editForm.get(['numeroRue'])!.value,
      batiment: this.editForm.get(['batiment'])!.value,
      escalier: this.editForm.get(['escalier'])!.value,
      etage: this.editForm.get(['etage'])!.value,
      porte: this.editForm.get(['porte'])!.value,
      codePostal: this.editForm.get(['codePostal'])!.value,
      codeInseeCommune: this.editForm.get(['codeInseeCommune'])!.value,
      codeInseeCommuneActualise: this.editForm.get(['codeInseeCommuneActualise'])!.value,
      codeInseeCommuneCorrige: this.editForm.get(['codeInseeCommuneCorrige'])!.value,
      numeroLot: this.editForm.get(['numeroLot'])!.value,
      dateReceptionDpe: this.editForm.get(['dateReceptionDpe'])!.value,
      td002Consommations: this.editForm.get(['td002Consommations'])!.value,
      td006Batiment: this.editForm.get(['td006Batiment'])!.value,
      td017ConsommationNeuf: this.editForm.get(['td017ConsommationNeuf'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD001DPE>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
