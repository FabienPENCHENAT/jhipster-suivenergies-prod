import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IInfoDPE, InfoDPE } from 'app/shared/model/info-dpe.model';
import { InfoDPEService } from './info-dpe.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-info-dpe-update',
  templateUrl: './info-dpe-update.component.html',
})
export class InfoDPEUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];
  dateDPEDp: any;

  editForm = this.fb.group({
    id: [],
    numero: [],
    adresse: [],
    typeBatiment: [],
    anneeConstruction: [],
    surfaceHabitable: [],
    energieChauffage: [],
    energieEauChaude: [],
    energiePhotovoltaique: [],
    dateDPE: [],
    classeConsoEnergie: [],
    dpeJson: [],
    dpeJsonContentType: [],
    client: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected infoDPEService: InfoDPEService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infoDPE }) => {
      this.updateForm(infoDPE);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(infoDPE: IInfoDPE): void {
    this.editForm.patchValue({
      id: infoDPE.id,
      numero: infoDPE.numero,
      adresse: infoDPE.adresse,
      typeBatiment: infoDPE.typeBatiment,
      anneeConstruction: infoDPE.anneeConstruction,
      surfaceHabitable: infoDPE.surfaceHabitable,
      energieChauffage: infoDPE.energieChauffage,
      energieEauChaude: infoDPE.energieEauChaude,
      energiePhotovoltaique: infoDPE.energiePhotovoltaique,
      dateDPE: infoDPE.dateDPE,
      classeConsoEnergie: infoDPE.classeConsoEnergie,
      dpeJson: infoDPE.dpeJson,
      dpeJsonContentType: infoDPE.dpeJsonContentType,
      client: infoDPE.client,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('suivEnergiesApp.error', { message: err.message })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const infoDPE = this.createFromForm();
    if (infoDPE.id !== undefined) {
      this.subscribeToSaveResponse(this.infoDPEService.update(infoDPE));
    } else {
      this.subscribeToSaveResponse(this.infoDPEService.create(infoDPE));
    }
  }

  private createFromForm(): IInfoDPE {
    return {
      ...new InfoDPE(),
      id: this.editForm.get(['id'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      typeBatiment: this.editForm.get(['typeBatiment'])!.value,
      anneeConstruction: this.editForm.get(['anneeConstruction'])!.value,
      surfaceHabitable: this.editForm.get(['surfaceHabitable'])!.value,
      energieChauffage: this.editForm.get(['energieChauffage'])!.value,
      energieEauChaude: this.editForm.get(['energieEauChaude'])!.value,
      energiePhotovoltaique: this.editForm.get(['energiePhotovoltaique'])!.value,
      dateDPE: this.editForm.get(['dateDPE'])!.value,
      classeConsoEnergie: this.editForm.get(['classeConsoEnergie'])!.value,
      dpeJsonContentType: this.editForm.get(['dpeJsonContentType'])!.value,
      dpeJson: this.editForm.get(['dpeJson'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfoDPE>>): void {
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
