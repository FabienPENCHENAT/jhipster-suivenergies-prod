import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD002Consommations, TD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { TD002ConsommationsService } from './td-002-consommations.service';

@Component({
  selector: 'jhi-td-002-consommations-update',
  templateUrl: './td-002-consommations-update.component.html',
})
export class TD002ConsommationsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tr006TypeUsage: [],
    tr004TypEnergie: [],
    tv042TarifEnergie: [],
    consommationEnergieFinale: [],
    consommationEnergiePrimaire: [],
    fraisAnnuelsEnergie: [],
    estEfface: [],
  });

  constructor(
    protected tD002ConsommationsService: TD002ConsommationsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD002Consommations }) => {
      this.updateForm(tD002Consommations);
    });
  }

  updateForm(tD002Consommations: ITD002Consommations): void {
    this.editForm.patchValue({
      id: tD002Consommations.id,
      tr006TypeUsage: tD002Consommations.tr006TypeUsage,
      tr004TypEnergie: tD002Consommations.tr004TypEnergie,
      tv042TarifEnergie: tD002Consommations.tv042TarifEnergie,
      consommationEnergieFinale: tD002Consommations.consommationEnergieFinale,
      consommationEnergiePrimaire: tD002Consommations.consommationEnergiePrimaire,
      fraisAnnuelsEnergie: tD002Consommations.fraisAnnuelsEnergie,
      estEfface: tD002Consommations.estEfface,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD002Consommations = this.createFromForm();
    if (tD002Consommations.id !== undefined) {
      this.subscribeToSaveResponse(this.tD002ConsommationsService.update(tD002Consommations));
    } else {
      this.subscribeToSaveResponse(this.tD002ConsommationsService.create(tD002Consommations));
    }
  }

  private createFromForm(): ITD002Consommations {
    return {
      ...new TD002Consommations(),
      id: this.editForm.get(['id'])!.value,
      tr006TypeUsage: this.editForm.get(['tr006TypeUsage'])!.value,
      tr004TypEnergie: this.editForm.get(['tr004TypEnergie'])!.value,
      tv042TarifEnergie: this.editForm.get(['tv042TarifEnergie'])!.value,
      consommationEnergieFinale: this.editForm.get(['consommationEnergieFinale'])!.value,
      consommationEnergiePrimaire: this.editForm.get(['consommationEnergiePrimaire'])!.value,
      fraisAnnuelsEnergie: this.editForm.get(['fraisAnnuelsEnergie'])!.value,
      estEfface: this.editForm.get(['estEfface'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD002Consommations>>): void {
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
}
