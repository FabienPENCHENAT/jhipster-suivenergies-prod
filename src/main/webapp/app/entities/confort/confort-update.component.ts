import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConfort, Confort } from 'app/shared/model/confort.model';
import { ConfortService } from './confort.service';
import { IElectromenager } from 'app/shared/model/electromenager.model';
import { ElectromenagerService } from 'app/entities/electromenager/electromenager.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

type SelectableEntity = IElectromenager | IClient;

@Component({
  selector: 'jhi-confort-update',
  templateUrl: './confort-update.component.html',
})
export class ConfortUpdateComponent implements OnInit {
  isSaving = false;
  electromenagers: IElectromenager[] = [];
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    installationElectrique: [],
    installationGaz: [],
    installationHumidite: [],
    installationPortesFenetres: [],
    chauffageHiver: [],
    surfaceChauffee: [],
    temperatureHiverSejour: [],
    temperatureHiverChambres: [],
    climEte: [],
    temperatureEteSejour: [],
    temperatureEteChambres: [],
    electromenagers: [],
    client: [],
  });

  constructor(
    protected confortService: ConfortService,
    protected electromenagerService: ElectromenagerService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ confort }) => {
      this.updateForm(confort);

      this.electromenagerService.query().subscribe((res: HttpResponse<IElectromenager[]>) => (this.electromenagers = res.body || []));

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(confort: IConfort): void {
    this.editForm.patchValue({
      id: confort.id,
      installationElectrique: confort.installationElectrique,
      installationGaz: confort.installationGaz,
      installationHumidite: confort.installationHumidite,
      installationPortesFenetres: confort.installationPortesFenetres,
      chauffageHiver: confort.chauffageHiver,
      surfaceChauffee: confort.surfaceChauffee,
      temperatureHiverSejour: confort.temperatureHiverSejour,
      temperatureHiverChambres: confort.temperatureHiverChambres,
      climEte: confort.climEte,
      temperatureEteSejour: confort.temperatureEteSejour,
      temperatureEteChambres: confort.temperatureEteChambres,
      electromenagers: confort.electromenagers,
      client: confort.client,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const confort = this.createFromForm();
    if (confort.id !== undefined) {
      this.subscribeToSaveResponse(this.confortService.update(confort));
    } else {
      this.subscribeToSaveResponse(this.confortService.create(confort));
    }
  }

  private createFromForm(): IConfort {
    return {
      ...new Confort(),
      id: this.editForm.get(['id'])!.value,
      installationElectrique: this.editForm.get(['installationElectrique'])!.value,
      installationGaz: this.editForm.get(['installationGaz'])!.value,
      installationHumidite: this.editForm.get(['installationHumidite'])!.value,
      installationPortesFenetres: this.editForm.get(['installationPortesFenetres'])!.value,
      chauffageHiver: this.editForm.get(['chauffageHiver'])!.value,
      surfaceChauffee: this.editForm.get(['surfaceChauffee'])!.value,
      temperatureHiverSejour: this.editForm.get(['temperatureHiverSejour'])!.value,
      temperatureHiverChambres: this.editForm.get(['temperatureHiverChambres'])!.value,
      climEte: this.editForm.get(['climEte'])!.value,
      temperatureEteSejour: this.editForm.get(['temperatureEteSejour'])!.value,
      temperatureEteChambres: this.editForm.get(['temperatureEteChambres'])!.value,
      electromenagers: this.editForm.get(['electromenagers'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConfort>>): void {
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

  getSelected(selectedVals: IElectromenager[], option: IElectromenager): IElectromenager {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
