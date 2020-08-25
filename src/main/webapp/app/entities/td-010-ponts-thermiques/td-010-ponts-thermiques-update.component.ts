import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD010PontsThermiques, TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { TD010PontsThermiquesService } from './td-010-ponts-thermiques.service';

@Component({
  selector: 'jhi-td-010-ponts-thermiques-update',
  templateUrl: './td-010-ponts-thermiques-update.component.html',
})
export class TD010PontsThermiquesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    longueur: [],
    tv013ValeurPontThermique: [],
  });

  constructor(
    protected tD010PontsThermiquesService: TD010PontsThermiquesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD010PontsThermiques }) => {
      this.updateForm(tD010PontsThermiques);
    });
  }

  updateForm(tD010PontsThermiques: ITD010PontsThermiques): void {
    this.editForm.patchValue({
      id: tD010PontsThermiques.id,
      longueur: tD010PontsThermiques.longueur,
      tv013ValeurPontThermique: tD010PontsThermiques.tv013ValeurPontThermique,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD010PontsThermiques = this.createFromForm();
    if (tD010PontsThermiques.id !== undefined) {
      this.subscribeToSaveResponse(this.tD010PontsThermiquesService.update(tD010PontsThermiques));
    } else {
      this.subscribeToSaveResponse(this.tD010PontsThermiquesService.create(tD010PontsThermiques));
    }
  }

  private createFromForm(): ITD010PontsThermiques {
    return {
      ...new TD010PontsThermiques(),
      id: this.editForm.get(['id'])!.value,
      longueur: this.editForm.get(['longueur'])!.value,
      tv013ValeurPontThermique: this.editForm.get(['tv013ValeurPontThermique'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD010PontsThermiques>>): void {
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
