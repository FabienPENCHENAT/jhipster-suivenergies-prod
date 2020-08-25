import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITD007ParoiOpaque, TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { TD007ParoiOpaqueService } from './td-007-paroi-opaque.service';
import { ITD008Baie } from 'app/shared/model/td-008-baie.model';
import { TD008BaieService } from 'app/entities/td-008-baie/td-008-baie.service';

@Component({
  selector: 'jhi-td-007-paroi-opaque-update',
  templateUrl: './td-007-paroi-opaque-update.component.html',
})
export class TD007ParoiOpaqueUpdateComponent implements OnInit {
  isSaving = false;
  td008baies: ITD008Baie[] = [];

  editForm = this.fb.group({
    id: [],
    tr014TypeParoisOpaque: [],
    reference: [],
    deperditionThermique: [],
    coefficientTransmissionThermiqueParoi: [],
    resistanceThermiqueIsolation: [],
    epaisseurIsolation: [],
    surfaceParoi: [],
    td008Baie: [],
  });

  constructor(
    protected tD007ParoiOpaqueService: TD007ParoiOpaqueService,
    protected tD008BaieService: TD008BaieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD007ParoiOpaque }) => {
      this.updateForm(tD007ParoiOpaque);

      this.tD008BaieService
        .query({ filter: 'td007paroiopaque-is-null' })
        .pipe(
          map((res: HttpResponse<ITD008Baie[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITD008Baie[]) => {
          if (!tD007ParoiOpaque.td008Baie || !tD007ParoiOpaque.td008Baie.id) {
            this.td008baies = resBody;
          } else {
            this.tD008BaieService
              .find(tD007ParoiOpaque.td008Baie.id)
              .pipe(
                map((subRes: HttpResponse<ITD008Baie>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITD008Baie[]) => (this.td008baies = concatRes));
          }
        });
    });
  }

  updateForm(tD007ParoiOpaque: ITD007ParoiOpaque): void {
    this.editForm.patchValue({
      id: tD007ParoiOpaque.id,
      tr014TypeParoisOpaque: tD007ParoiOpaque.tr014TypeParoisOpaque,
      reference: tD007ParoiOpaque.reference,
      deperditionThermique: tD007ParoiOpaque.deperditionThermique,
      coefficientTransmissionThermiqueParoi: tD007ParoiOpaque.coefficientTransmissionThermiqueParoi,
      resistanceThermiqueIsolation: tD007ParoiOpaque.resistanceThermiqueIsolation,
      epaisseurIsolation: tD007ParoiOpaque.epaisseurIsolation,
      surfaceParoi: tD007ParoiOpaque.surfaceParoi,
      td008Baie: tD007ParoiOpaque.td008Baie,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tD007ParoiOpaque = this.createFromForm();
    if (tD007ParoiOpaque.id !== undefined) {
      this.subscribeToSaveResponse(this.tD007ParoiOpaqueService.update(tD007ParoiOpaque));
    } else {
      this.subscribeToSaveResponse(this.tD007ParoiOpaqueService.create(tD007ParoiOpaque));
    }
  }

  private createFromForm(): ITD007ParoiOpaque {
    return {
      ...new TD007ParoiOpaque(),
      id: this.editForm.get(['id'])!.value,
      tr014TypeParoisOpaque: this.editForm.get(['tr014TypeParoisOpaque'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      deperditionThermique: this.editForm.get(['deperditionThermique'])!.value,
      coefficientTransmissionThermiqueParoi: this.editForm.get(['coefficientTransmissionThermiqueParoi'])!.value,
      resistanceThermiqueIsolation: this.editForm.get(['resistanceThermiqueIsolation'])!.value,
      epaisseurIsolation: this.editForm.get(['epaisseurIsolation'])!.value,
      surfaceParoi: this.editForm.get(['surfaceParoi'])!.value,
      td008Baie: this.editForm.get(['td008Baie'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITD007ParoiOpaque>>): void {
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

  trackById(index: number, item: ITD008Baie): any {
    return item.id;
  }
}
