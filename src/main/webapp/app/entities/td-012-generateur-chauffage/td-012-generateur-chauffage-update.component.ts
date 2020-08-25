import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITD012GenerateurChauffage, TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { TD012GenerateurChauffageService } from './td-012-generateur-chauffage.service';

@Component({
  selector: 'jhi-td-012-generateur-chauffage-update',
  templateUrl: './td-012-generateur-chauffage-update.component.html',
})
export class TD012GenerateurChauffageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    systemeChauffageCogeneration: [],
    td011InstallationChauffage: [],
    tr004TypeEnergie: [],
    consommationChauffage: [],
  });

  constructor(
    protected tD012GenerateurChauffageService: TD012GenerateurChauffageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD012GenerateurChauffage }) => {
      this.updateForm(tD012GenerateurChauffage);
    });
  }

  updateForm(tD012GenerateurChauffage: ITD012GenerateurChauffage): void {
    this.editForm.patchValue({
      id: tD012GenerateurChauffage.id,
      systemeChauffageCogeneration: tD012GenerateurChauffage.systemeChauffageCogeneration,
      td011InstallationChauffage: tD012GenerateurChauffage.td011InstallationChauffage,
      tr004TypeEnergie: tD012GenerateurChauffage.tr004TypeEnergie,
      consommationChauffage: tD012GenerateurChauffage.consommationChauffage,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD012GenerateurChauffage = this.createFromForm();
    if (tD012GenerateurChauffage.id !== undefined) {
      this.subscribeToSaveResponse(this.tD012GenerateurChauffageService.update(tD012GenerateurChauffage));
    } else {
      this.subscribeToSaveResponse(this.tD012GenerateurChauffageService.create(tD012GenerateurChauffage));
    }
  }

  private createFromForm(): ITD012GenerateurChauffage {
    return {
      ...new TD012GenerateurChauffage(),
      id: this.editForm.get(['id'])!.value,
      systemeChauffageCogeneration: this.editForm.get(['systemeChauffageCogeneration'])!.value,
      td011InstallationChauffage: this.editForm.get(['td011InstallationChauffage'])!.value,
      tr004TypeEnergie: this.editForm.get(['tr004TypeEnergie'])!.value,
      consommationChauffage: this.editForm.get(['consommationChauffage'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD012GenerateurChauffage>>): void {
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
