import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD014GenerateurECS, TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { TD014GenerateurECSService } from './td-014-generateur-ecs.service';

@Component({
  selector: 'jhi-td-014-generateur-ecs-update',
  templateUrl: './td-014-generateur-ecs-update.component.html',
})
export class TD014GenerateurECSUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    td013InstallationEcs: [],
    tr004TypeEnergie: [],
    volumeStockage: [],
  });

  constructor(
    protected tD014GenerateurECSService: TD014GenerateurECSService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD014GenerateurECS }) => {
      this.updateForm(tD014GenerateurECS);
    });
  }

  updateForm(tD014GenerateurECS: ITD014GenerateurECS): void {
    this.editForm.patchValue({
      id: tD014GenerateurECS.id,
      td013InstallationEcs: tD014GenerateurECS.td013InstallationEcs,
      tr004TypeEnergie: tD014GenerateurECS.tr004TypeEnergie,
      volumeStockage: tD014GenerateurECS.volumeStockage,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD014GenerateurECS = this.createFromForm();
    if (tD014GenerateurECS.id !== undefined) {
      this.subscribeToSaveResponse(this.tD014GenerateurECSService.update(tD014GenerateurECS));
    } else {
      this.subscribeToSaveResponse(this.tD014GenerateurECSService.create(tD014GenerateurECS));
    }
  }

  private createFromForm(): ITD014GenerateurECS {
    return {
      ...new TD014GenerateurECS(),
      id: this.editForm.get(['id'])!.value,
      td013InstallationEcs: this.editForm.get(['td013InstallationEcs'])!.value,
      tr004TypeEnergie: this.editForm.get(['tr004TypeEnergie'])!.value,
      volumeStockage: this.editForm.get(['volumeStockage'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD014GenerateurECS>>): void {
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
