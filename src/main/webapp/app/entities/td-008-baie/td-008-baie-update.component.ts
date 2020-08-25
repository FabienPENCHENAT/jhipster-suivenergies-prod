import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD008Baie, TD008Baie } from 'app/shared/model/td-008-baie.model';
import { TD008BaieService } from './td-008-baie.service';

@Component({
  selector: 'jhi-td-008-baie-update',
  templateUrl: './td-008-baie-update.component.html',
})
export class TD008BaieUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    reference: [],
    td008Baie: [],
    deperdition: [],
    tv009CoefficientTransmissionThermiqueVitrage: [],
    tv012CoefTransmissionThermiqueBaieProtectionSolaire: [],
    surface: [],
    perimetre: [],
    tv013ValeurPontThermique: [],
  });

  constructor(protected tD008BaieService: TD008BaieService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD008Baie }) => {
      this.updateForm(tD008Baie);
    });
  }

  updateForm(tD008Baie: ITD008Baie): void {
    this.editForm.patchValue({
      id: tD008Baie.id,
      reference: tD008Baie.reference,
      td008Baie: tD008Baie.td008Baie,
      deperdition: tD008Baie.deperdition,
      tv009CoefficientTransmissionThermiqueVitrage: tD008Baie.tv009CoefficientTransmissionThermiqueVitrage,
      tv012CoefTransmissionThermiqueBaieProtectionSolaire: tD008Baie.tv012CoefTransmissionThermiqueBaieProtectionSolaire,
      surface: tD008Baie.surface,
      perimetre: tD008Baie.perimetre,
      tv013ValeurPontThermique: tD008Baie.tv013ValeurPontThermique,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD008Baie = this.createFromForm();
    if (tD008Baie.id !== undefined) {
      this.subscribeToSaveResponse(this.tD008BaieService.update(tD008Baie));
    } else {
      this.subscribeToSaveResponse(this.tD008BaieService.create(tD008Baie));
    }
  }

  private createFromForm(): ITD008Baie {
    return {
      ...new TD008Baie(),
      id: this.editForm.get(['id'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      td008Baie: this.editForm.get(['td008Baie'])!.value,
      deperdition: this.editForm.get(['deperdition'])!.value,
      tv009CoefficientTransmissionThermiqueVitrage: this.editForm.get(['tv009CoefficientTransmissionThermiqueVitrage'])!.value,
      tv012CoefTransmissionThermiqueBaieProtectionSolaire: this.editForm.get(['tv012CoefTransmissionThermiqueBaieProtectionSolaire'])!
        .value,
      surface: this.editForm.get(['surface'])!.value,
      perimetre: this.editForm.get(['perimetre'])!.value,
      tv013ValeurPontThermique: this.editForm.get(['tv013ValeurPontThermique'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD008Baie>>): void {
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
