import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITD013InstalationECS, TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { TD013InstalationECSService } from './td-013-instalation-ecs.service';
import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { TD014GenerateurECSService } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.service';

@Component({
  selector: 'jhi-td-013-instalation-ecs-update',
  templateUrl: './td-013-instalation-ecs-update.component.html',
})
export class TD013InstalationECSUpdateComponent implements OnInit {
  isSaving = false;
  td014generateurecs: ITD014GenerateurECS[] = [];

  editForm = this.fb.group({
    id: [],
    tr005TypeInstallationEcs: [],
    becs: [],
    tv039FormuleBecs: [],
    surfaceAlimentee: [],
    td014GenerateurECS: [],
  });

  constructor(
    protected tD013InstalationECSService: TD013InstalationECSService,
    protected tD014GenerateurECSService: TD014GenerateurECSService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD013InstalationECS }) => {
      this.updateForm(tD013InstalationECS);

      this.tD014GenerateurECSService
        .query({ filter: 'td013instalationecs-is-null' })
        .pipe(
          map((res: HttpResponse<ITD014GenerateurECS[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD014GenerateurECS[]) => {
          if (!tD013InstalationECS.td014GenerateurECS || !tD013InstalationECS.td014GenerateurECS.id) {
            this.td014generateurecs = resBody;
          } else {
            this.tD014GenerateurECSService
              .find(tD013InstalationECS.td014GenerateurECS.id)
              .pipe(
                map((subRes: HttpResponse<ITD014GenerateurECS>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD014GenerateurECS[]) => (this.td014generateurecs = concatRes));
          }
        });
    });
  }

  updateForm(tD013InstalationECS: ITD013InstalationECS): void {
    this.editForm.patchValue({
      id: tD013InstalationECS.id,
      tr005TypeInstallationEcs: tD013InstalationECS.tr005TypeInstallationEcs,
      becs: tD013InstalationECS.becs,
      tv039FormuleBecs: tD013InstalationECS.tv039FormuleBecs,
      surfaceAlimentee: tD013InstalationECS.surfaceAlimentee,
      td014GenerateurECS: tD013InstalationECS.td014GenerateurECS,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD013InstalationECS = this.createFromForm();
    if (tD013InstalationECS.id !== undefined) {
      this.subscribeToSaveResponse(this.tD013InstalationECSService.update(tD013InstalationECS));
    } else {
      this.subscribeToSaveResponse(this.tD013InstalationECSService.create(tD013InstalationECS));
    }
  }

  private createFromForm(): ITD013InstalationECS {
    return {
      ...new TD013InstalationECS(),
      id: this.editForm.get(['id'])!.value,
      tr005TypeInstallationEcs: this.editForm.get(['tr005TypeInstallationEcs'])!.value,
      becs: this.editForm.get(['becs'])!.value,
      tv039FormuleBecs: this.editForm.get(['tv039FormuleBecs'])!.value,
      surfaceAlimentee: this.editForm.get(['surfaceAlimentee'])!.value,
      td014GenerateurECS: this.editForm.get(['td014GenerateurECS'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD013InstalationECS>>): void {
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

  trackById(index: number, item: ITD014GenerateurECS): any {
    return item.id;
  }
}
