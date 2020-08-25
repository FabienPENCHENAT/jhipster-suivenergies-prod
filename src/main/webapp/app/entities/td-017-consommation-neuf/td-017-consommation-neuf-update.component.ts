import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD017ConsommationNeuf, TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';
import { TD017ConsommationNeufService } from './td-017-consommation-neuf.service';

@Component({
  selector: 'jhi-td-017-consommation-neuf-update',
  templateUrl: './td-017-consommation-neuf-update.component.html',
})
export class TD017ConsommationNeufUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tr004TypeEnergie: [],
    tr006TypeUsage: [],
    tv044ConversionKwhEnergiesRelevees: [],
    tv045ConversionKwhCo2: [],
    tv046EvaluationContenuCo2Reseaux: [],
    consommationEnergieFinale: [],
    consommationEnergiePrimaire: [],
    fraisAnnuelsEnergie: [],
  });

  constructor(
    protected tD017ConsommationNeufService: TD017ConsommationNeufService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD017ConsommationNeuf }) => {
      this.updateForm(tD017ConsommationNeuf);
    });
  }

  updateForm(tD017ConsommationNeuf: ITD017ConsommationNeuf): void {
    this.editForm.patchValue({
      id: tD017ConsommationNeuf.id,
      tr004TypeEnergie: tD017ConsommationNeuf.tr004TypeEnergie,
      tr006TypeUsage: tD017ConsommationNeuf.tr006TypeUsage,
      tv044ConversionKwhEnergiesRelevees: tD017ConsommationNeuf.tv044ConversionKwhEnergiesRelevees,
      tv045ConversionKwhCo2: tD017ConsommationNeuf.tv045ConversionKwhCo2,
      tv046EvaluationContenuCo2Reseaux: tD017ConsommationNeuf.tv046EvaluationContenuCo2Reseaux,
      consommationEnergieFinale: tD017ConsommationNeuf.consommationEnergieFinale,
      consommationEnergiePrimaire: tD017ConsommationNeuf.consommationEnergiePrimaire,
      fraisAnnuelsEnergie: tD017ConsommationNeuf.fraisAnnuelsEnergie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD017ConsommationNeuf = this.createFromForm();
    if (tD017ConsommationNeuf.id !== undefined) {
      this.subscribeToSaveResponse(this.tD017ConsommationNeufService.update(tD017ConsommationNeuf));
    } else {
      this.subscribeToSaveResponse(this.tD017ConsommationNeufService.create(tD017ConsommationNeuf));
    }
  }

  private createFromForm(): ITD017ConsommationNeuf {
    return {
      ...new TD017ConsommationNeuf(),
      id: this.editForm.get(['id'])!.value,
      tr004TypeEnergie: this.editForm.get(['tr004TypeEnergie'])!.value,
      tr006TypeUsage: this.editForm.get(['tr006TypeUsage'])!.value,
      tv044ConversionKwhEnergiesRelevees: this.editForm.get(['tv044ConversionKwhEnergiesRelevees'])!.value,
      tv045ConversionKwhCo2: this.editForm.get(['tv045ConversionKwhCo2'])!.value,
      tv046EvaluationContenuCo2Reseaux: this.editForm.get(['tv046EvaluationContenuCo2Reseaux'])!.value,
      consommationEnergieFinale: this.editForm.get(['consommationEnergieFinale'])!.value,
      consommationEnergiePrimaire: this.editForm.get(['consommationEnergiePrimaire'])!.value,
      fraisAnnuelsEnergie: this.editForm.get(['fraisAnnuelsEnergie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD017ConsommationNeuf>>): void {
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
