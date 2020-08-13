import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModeVie, ModeVie } from 'app/shared/model/mode-vie.model';
import { ModeVieService } from './mode-vie.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-mode-vie-update',
  templateUrl: './mode-vie-update.component.html',
})
export class ModeVieUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    nbPersonnes: [],
    presenceMatinSemaine: [],
    presenceMatinWE: [],
    presenceAMSemaine: [],
    presenceAMWE: [],
    presenceSoirSemaine: [],
    presenceSoirWE: [],
    presenceNuitSemaine: [],
    presenceNuitWE: [],
    semainesAbsenceHiver: [],
    semainesAbsenceEte: [],
    client: [],
  });

  constructor(
    protected modeVieService: ModeVieService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeVie }) => {
      this.updateForm(modeVie);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(modeVie: IModeVie): void {
    this.editForm.patchValue({
      id: modeVie.id,
      nbPersonnes: modeVie.nbPersonnes,
      presenceMatinSemaine: modeVie.presenceMatinSemaine,
      presenceMatinWE: modeVie.presenceMatinWE,
      presenceAMSemaine: modeVie.presenceAMSemaine,
      presenceAMWE: modeVie.presenceAMWE,
      presenceSoirSemaine: modeVie.presenceSoirSemaine,
      presenceSoirWE: modeVie.presenceSoirWE,
      presenceNuitSemaine: modeVie.presenceNuitSemaine,
      presenceNuitWE: modeVie.presenceNuitWE,
      semainesAbsenceHiver: modeVie.semainesAbsenceHiver,
      semainesAbsenceEte: modeVie.semainesAbsenceEte,
      client: modeVie.client,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modeVie = this.createFromForm();
    if (modeVie.id !== undefined) {
      this.subscribeToSaveResponse(this.modeVieService.update(modeVie));
    } else {
      this.subscribeToSaveResponse(this.modeVieService.create(modeVie));
    }
  }

  private createFromForm(): IModeVie {
    return {
      ...new ModeVie(),
      id: this.editForm.get(['id'])!.value,
      nbPersonnes: this.editForm.get(['nbPersonnes'])!.value,
      presenceMatinSemaine: this.editForm.get(['presenceMatinSemaine'])!.value,
      presenceMatinWE: this.editForm.get(['presenceMatinWE'])!.value,
      presenceAMSemaine: this.editForm.get(['presenceAMSemaine'])!.value,
      presenceAMWE: this.editForm.get(['presenceAMWE'])!.value,
      presenceSoirSemaine: this.editForm.get(['presenceSoirSemaine'])!.value,
      presenceSoirWE: this.editForm.get(['presenceSoirWE'])!.value,
      presenceNuitSemaine: this.editForm.get(['presenceNuitSemaine'])!.value,
      presenceNuitWE: this.editForm.get(['presenceNuitWE'])!.value,
      semainesAbsenceHiver: this.editForm.get(['semainesAbsenceHiver'])!.value,
      semainesAbsenceEte: this.editForm.get(['semainesAbsenceEte'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModeVie>>): void {
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

  trackById(index: number, item: IClient): any {
    return item.id;
  }
}
