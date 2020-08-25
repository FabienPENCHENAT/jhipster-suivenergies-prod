import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD015ProductionEnergies, TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { TD015ProductionEnergiesService } from './td-015-production-energies.service';

@Component({
  selector: 'jhi-td-015-production-energies-update',
  templateUrl: './td-015-production-energies-update.component.html',
})
export class TD015ProductionEnergiesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tr004TypeEnergie: [],
    productionElectriciteCapteursPhotovoltaiques: [],
    productionleEctriciteMicroEolienne: [],
    productionCogeneration: [],
  });

  constructor(
    protected tD015ProductionEnergiesService: TD015ProductionEnergiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD015ProductionEnergies }) => {
      this.updateForm(tD015ProductionEnergies);
    });
  }

  updateForm(tD015ProductionEnergies: ITD015ProductionEnergies): void {
    this.editForm.patchValue({
      id: tD015ProductionEnergies.id,
      tr004TypeEnergie: tD015ProductionEnergies.tr004TypeEnergie,
      productionElectriciteCapteursPhotovoltaiques: tD015ProductionEnergies.productionElectriciteCapteursPhotovoltaiques,
      productionleEctriciteMicroEolienne: tD015ProductionEnergies.productionleEctriciteMicroEolienne,
      productionCogeneration: tD015ProductionEnergies.productionCogeneration,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD015ProductionEnergies = this.createFromForm();
    if (tD015ProductionEnergies.id !== undefined) {
      this.subscribeToSaveResponse(this.tD015ProductionEnergiesService.update(tD015ProductionEnergies));
    } else {
      this.subscribeToSaveResponse(this.tD015ProductionEnergiesService.create(tD015ProductionEnergies));
    }
  }

  private createFromForm(): ITD015ProductionEnergies {
    return {
      ...new TD015ProductionEnergies(),
      id: this.editForm.get(['id'])!.value,
      tr004TypeEnergie: this.editForm.get(['tr004TypeEnergie'])!.value,
      productionElectriciteCapteursPhotovoltaiques: this.editForm.get(['productionElectriciteCapteursPhotovoltaiques'])!.value,
      productionleEctriciteMicroEolienne: this.editForm.get(['productionleEctriciteMicroEolienne'])!.value,
      productionCogeneration: this.editForm.get(['productionCogeneration'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD015ProductionEnergies>>): void {
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
