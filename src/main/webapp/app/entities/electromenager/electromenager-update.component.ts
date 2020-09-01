import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IElectromenager, Electromenager } from 'app/shared/model/electromenager.model';
import { ElectromenagerService } from './electromenager.service';

@Component({
  selector: 'jhi-electromenager-update',
  templateUrl: './electromenager-update.component.html',
})
export class ElectromenagerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    note: [],
    byDefault: [],
    consoAnnuelle: [],
  });

  constructor(protected electromenagerService: ElectromenagerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ electromenager }) => {
      this.updateForm(electromenager);
    });
  }

  updateForm(electromenager: IElectromenager): void {
    this.editForm.patchValue({
      id: electromenager.id,
      nom: electromenager.nom,
      note: electromenager.note,
      byDefault: electromenager.byDefault,
      consoAnnuelle: electromenager.consoAnnuelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const electromenager = this.createFromForm();
    if (electromenager.id !== undefined) {
      this.subscribeToSaveResponse(this.electromenagerService.update(electromenager));
    } else {
      this.subscribeToSaveResponse(this.electromenagerService.create(electromenager));
    }
  }

  private createFromForm(): IElectromenager {
    return {
      ...new Electromenager(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      note: this.editForm.get(['note'])!.value,
      byDefault: this.editForm.get(['byDefault'])!.value,
      consoAnnuelle: this.editForm.get(['consoAnnuelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElectromenager>>): void {
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
