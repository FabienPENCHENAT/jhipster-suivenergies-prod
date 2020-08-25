import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITD011InstalationChauffage, TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { TD011InstalationChauffageService } from './td-011-instalation-chauffage.service';
import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { TD012GenerateurChauffageService } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.service';

@Component({
  selector: 'jhi-td-011-instalation-chauffage-update',
  templateUrl: './td-011-instalation-chauffage-update.component.html',
})
export class TD011InstalationChauffageUpdateComponent implements OnInit {
  isSaving = false;
  td012generateurchauffages: ITD012GenerateurChauffage[] = [];

  editForm = this.fb.group({
    id: [],
    tr003TypeInstallationChauffage: [],
    surfaceChauffee: [],
    td012GenerateurChauffage: [],
  });

  constructor(
    protected tD011InstalationChauffageService: TD011InstalationChauffageService,
    protected tD012GenerateurChauffageService: TD012GenerateurChauffageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD011InstalationChauffage }) => {
      this.updateForm(tD011InstalationChauffage);

      this.tD012GenerateurChauffageService
        .query({ filter: 'td011instalationchauffage-is-null' })
        .pipe(
          map((res: HttpResponse<ITD012GenerateurChauffage[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD012GenerateurChauffage[]) => {
          if (!tD011InstalationChauffage.td012GenerateurChauffage || !tD011InstalationChauffage.td012GenerateurChauffage.id) {
            this.td012generateurchauffages = resBody;
          } else {
            this.tD012GenerateurChauffageService
              .find(tD011InstalationChauffage.td012GenerateurChauffage.id)
              .pipe(
                map((subRes: HttpResponse<ITD012GenerateurChauffage>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD012GenerateurChauffage[]) => (this.td012generateurchauffages = concatRes));
          }
        });
    });
  }

  updateForm(tD011InstalationChauffage: ITD011InstalationChauffage): void {
    this.editForm.patchValue({
      id: tD011InstalationChauffage.id,
      tr003TypeInstallationChauffage: tD011InstalationChauffage.tr003TypeInstallationChauffage,
      surfaceChauffee: tD011InstalationChauffage.surfaceChauffee,
      td012GenerateurChauffage: tD011InstalationChauffage.td012GenerateurChauffage,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD011InstalationChauffage = this.createFromForm();
    if (tD011InstalationChauffage.id !== undefined) {
      this.subscribeToSaveResponse(this.tD011InstalationChauffageService.update(tD011InstalationChauffage));
    } else {
      this.subscribeToSaveResponse(this.tD011InstalationChauffageService.create(tD011InstalationChauffage));
    }
  }

  private createFromForm(): ITD011InstalationChauffage {
    return {
      ...new TD011InstalationChauffage(),
      id: this.editForm.get(['id'])!.value,
      tr003TypeInstallationChauffage: this.editForm.get(['tr003TypeInstallationChauffage'])!.value,
      surfaceChauffee: this.editForm.get(['surfaceChauffee'])!.value,
      td012GenerateurChauffage: this.editForm.get(['td012GenerateurChauffage'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD011InstalationChauffage>>): void {
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

  trackById(index: number, item: ITD012GenerateurChauffage): any {
    return item.id;
  }
}
