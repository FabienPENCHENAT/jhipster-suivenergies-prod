import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITD006Batiment, TD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { TD006BatimentService } from './td-006-batiment.service';
import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { TD007ParoiOpaqueService } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.service';
import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { TD010PontsThermiquesService } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.service';
import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { TD011InstalationChauffageService } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.service';
import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { TD013InstalationECSService } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs.service';
import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { TD015ProductionEnergiesService } from 'app/entities/td-015-production-energies/td-015-production-energies.service';

type SelectableEntity =
  | ITD007ParoiOpaque
  | ITD010PontsThermiques
  | ITD011InstalationChauffage
  | ITD013InstalationECS
  | ITD015ProductionEnergies;

@Component({
  selector: 'jhi-td-006-batiment-update',
  templateUrl: './td-006-batiment-update.component.html',
})
export class TD006BatimentUpdateComponent implements OnInit {
  isSaving = false;
  td007paroiopaques: ITD007ParoiOpaque[] = [];
  td010pontsthermiques: ITD010PontsThermiques[] = [];
  td011instalationchauffages: ITD011InstalationChauffage[] = [];
  td013instalationecs: ITD013InstalationECS[] = [];
  td015productionenergies: ITD015ProductionEnergies[] = [];

  editForm = this.fb.group({
    id: [],
    besoinChauffage: [],
    deperditionEnveloppe: [],
    deperditionRenouvellementAir: [],
    altitude: [],
    nombreNiveau: [],
    hspMoyenne: [],
    td007ParoiOpaque: [],
    td010PontsThermiques: [],
    td011InstalationChauffage: [],
    td013InstalationECS: [],
    td015ProductionEnergies: [],
  });

  constructor(
    protected tD006BatimentService: TD006BatimentService,
    protected tD007ParoiOpaqueService: TD007ParoiOpaqueService,
    protected tD010PontsThermiquesService: TD010PontsThermiquesService,
    protected tD011InstalationChauffageService: TD011InstalationChauffageService,
    protected tD013InstalationECSService: TD013InstalationECSService,
    protected tD015ProductionEnergiesService: TD015ProductionEnergiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD006Batiment }) => {
      this.updateForm(tD006Batiment);

      this.tD007ParoiOpaqueService
        .query({ filter: 'td006batiment-is-null' })
        .pipe(
          map((res: HttpResponse<ITD007ParoiOpaque[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD007ParoiOpaque[]) => {
          if (!tD006Batiment.td007ParoiOpaque || !tD006Batiment.td007ParoiOpaque.id) {
            this.td007paroiopaques = resBody;
          } else {
            this.tD007ParoiOpaqueService
              .find(tD006Batiment.td007ParoiOpaque.id)
              .pipe(
                map((subRes: HttpResponse<ITD007ParoiOpaque>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD007ParoiOpaque[]) => (this.td007paroiopaques = concatRes));
          }
        });

      this.tD010PontsThermiquesService
        .query({ filter: 'td006batiment-is-null' })
        .pipe(
          map((res: HttpResponse<ITD010PontsThermiques[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD010PontsThermiques[]) => {
          if (!tD006Batiment.td010PontsThermiques || !tD006Batiment.td010PontsThermiques.id) {
            this.td010pontsthermiques = resBody;
          } else {
            this.tD010PontsThermiquesService
              .find(tD006Batiment.td010PontsThermiques.id)
              .pipe(
                map((subRes: HttpResponse<ITD010PontsThermiques>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD010PontsThermiques[]) => (this.td010pontsthermiques = concatRes));
          }
        });

      this.tD011InstalationChauffageService
        .query({ filter: 'td006batiment-is-null' })
        .pipe(
          map((res: HttpResponse<ITD011InstalationChauffage[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD011InstalationChauffage[]) => {
          if (!tD006Batiment.td011InstalationChauffage || !tD006Batiment.td011InstalationChauffage.id) {
            this.td011instalationchauffages = resBody;
          } else {
            this.tD011InstalationChauffageService
              .find(tD006Batiment.td011InstalationChauffage.id)
              .pipe(
                map((subRes: HttpResponse<ITD011InstalationChauffage>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD011InstalationChauffage[]) => (this.td011instalationchauffages = concatRes));
          }
        });

      this.tD013InstalationECSService
        .query({ filter: 'td006batiment-is-null' })
        .pipe(
          map((res: HttpResponse<ITD013InstalationECS[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD013InstalationECS[]) => {
          if (!tD006Batiment.td013InstalationECS || !tD006Batiment.td013InstalationECS.id) {
            this.td013instalationecs = resBody;
          } else {
            this.tD013InstalationECSService
              .find(tD006Batiment.td013InstalationECS.id)
              .pipe(
                map((subRes: HttpResponse<ITD013InstalationECS>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD013InstalationECS[]) => (this.td013instalationecs = concatRes));
          }
        });

      this.tD015ProductionEnergiesService
        .query({ filter: 'td006batiment-is-null' })
        .pipe(
          map((res: HttpResponse<ITD015ProductionEnergies[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD015ProductionEnergies[]) => {
          if (!tD006Batiment.td015ProductionEnergies || !tD006Batiment.td015ProductionEnergies.id) {
            this.td015productionenergies = resBody;
          } else {
            this.tD015ProductionEnergiesService
              .find(tD006Batiment.td015ProductionEnergies.id)
              .pipe(
                map((subRes: HttpResponse<ITD015ProductionEnergies>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD015ProductionEnergies[]) => (this.td015productionenergies = concatRes));
          }
        });
    });
  }

  updateForm(tD006Batiment: ITD006Batiment): void {
    this.editForm.patchValue({
      id: tD006Batiment.id,
      besoinChauffage: tD006Batiment.besoinChauffage,
      deperditionEnveloppe: tD006Batiment.deperditionEnveloppe,
      deperditionRenouvellementAir: tD006Batiment.deperditionRenouvellementAir,
      altitude: tD006Batiment.altitude,
      nombreNiveau: tD006Batiment.nombreNiveau,
      hspMoyenne: tD006Batiment.hspMoyenne,
      td007ParoiOpaque: tD006Batiment.td007ParoiOpaque,
      td010PontsThermiques: tD006Batiment.td010PontsThermiques,
      td011InstalationChauffage: tD006Batiment.td011InstalationChauffage,
      td013InstalationECS: tD006Batiment.td013InstalationECS,
      td015ProductionEnergies: tD006Batiment.td015ProductionEnergies,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD006Batiment = this.createFromForm();
    if (tD006Batiment.id !== undefined) {
      this.subscribeToSaveResponse(this.tD006BatimentService.update(tD006Batiment));
    } else {
      this.subscribeToSaveResponse(this.tD006BatimentService.create(tD006Batiment));
    }
  }

  private createFromForm(): ITD006Batiment {
    return {
      ...new TD006Batiment(),
      id: this.editForm.get(['id'])!.value,
      besoinChauffage: this.editForm.get(['besoinChauffage'])!.value,
      deperditionEnveloppe: this.editForm.get(['deperditionEnveloppe'])!.value,
      deperditionRenouvellementAir: this.editForm.get(['deperditionRenouvellementAir'])!.value,
      altitude: this.editForm.get(['altitude'])!.value,
      nombreNiveau: this.editForm.get(['nombreNiveau'])!.value,
      hspMoyenne: this.editForm.get(['hspMoyenne'])!.value,
      td007ParoiOpaque: this.editForm.get(['td007ParoiOpaque'])!.value,
      td010PontsThermiques: this.editForm.get(['td010PontsThermiques'])!.value,
      td011InstalationChauffage: this.editForm.get(['td011InstalationChauffage'])!.value,
      td013InstalationECS: this.editForm.get(['td013InstalationECS'])!.value,
      td015ProductionEnergies: this.editForm.get(['td015ProductionEnergies'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD006Batiment>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
