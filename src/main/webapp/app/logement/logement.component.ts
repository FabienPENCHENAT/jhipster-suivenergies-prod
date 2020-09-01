import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { IModeVie, ModeVie } from 'app/shared/model/mode-vie.model';
import { IConfort, Confort } from 'app/shared/model/confort.model';
import { IFacture } from 'app/shared/model/facture.model';
import { IElectromenager } from 'app/shared/model/electromenager.model';
import { FacturesByType } from 'app/shared/model/facturesByType.model';
import { LogementService } from './logement.service';
import { InfoDPEService } from '../entities/info-dpe/info-dpe.service';
import { ConfortService } from '../entities/confort/confort.service';
import { FactureService } from '../entities/facture/facture.service';
import { ModeVieService } from '../entities/mode-vie/mode-vie.service';
import { ElectromenagerService } from '../entities/electromenager/electromenager.service';

import { FactureDeleteDialogComponent } from '../entities/facture/facture-delete-dialog.component';

@Component({
  selector: 'jhi-logement',
  templateUrl: './logement.component.html',
})
export class LogementComponent implements OnInit, OnDestroy {
  isSaving = false;
  idDPE?: number;
  infoDPE?: IInfoDPE;
  facturesByTypes?: FacturesByType[];
  modeVie?: IModeVie;
  confort?: IConfort;
  eventSubscriber?: Subscription;
  electromenagers?: IElectromenager[];
  selected?: number;

  dpeForm = this.fb.group({
    numero: [''],
    adresse1: [''],
    adresse2: [''],
    typeBatiment: [''],
    anneeConstruction: [''],
    surface: [''],
    energieChauffage: [''],
    energieEauChaude: [''],
    energiePhotovoltaique: [''],
  });

  modeVieForm = this.fb.group({
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

  factureForm = this.fb.group({
    elec2017: [''],
    elec2018: [''],
    elec2019: [''],
    gaz2017: [''],
    gaz2018: [''],
    gaz2019: [''],
    fioul2017: [''],
    fioul2018: [''],
    fioul2019: [''],
    bois2017: [''],
    bois2018: [''],
    bois2019: [''],
  });

  confortForm = this.fb.group({
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
    private logementService: LogementService,
    protected infoDPEService: InfoDPEService,
    protected factureService: FactureService,
    protected modeVieService: ModeVieService,
    protected confortService: ConfortService,
    protected electromenagerService: ElectromenagerService,
    private fb: FormBuilder,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.infoDPEService.findLast().subscribe((dpe: HttpResponse<IInfoDPE>) => this.completeDpeForm(dpe));
    this.loadAllFactures();
    this.modeVieService.findOneByClient().subscribe((res: HttpResponse<IModeVie>) => this.completeModeVie(res));
    this.confortService.findOneByClient().subscribe((res: HttpResponse<IConfort>) => this.completeConfort(res));
    this.electromenagerService.query().subscribe((res: HttpResponse<IElectromenager[]>) => (this.electromenagers = res.body || []));
    this.registerChangeInFactures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  registerChangeInFactures(): void {
    this.eventSubscriber = this.eventManager.subscribe('factureListModification', () => this.loadAllFactures());
  }

  loadAllFactures(): void {
    this.factureService.findAllByClientId().subscribe((res: HttpResponse<FacturesByType[]>) => (this.facturesByTypes = res.body || []));
  }

  completeDpeForm(dpe: HttpResponse<IInfoDPE>): void {
    if (dpe && dpe.body) {
      this.infoDPE = dpe.body;
    }
    if (this.infoDPE) {
      this.dpeForm.patchValue({
        numero: this.infoDPE.numero,
        adresse1: this.infoDPE.adresse,
        typeBatiment: this.infoDPE.typeBatiment,
        anneeConstruction: this.infoDPE.anneeConstruction,
        surface: this.infoDPE.surfaceHabitable,
        energieChauffage: this.infoDPE.energieChauffage,
        energieEauChaude: this.infoDPE.energieEauChaude,
        energiePhotovoltaique: this.infoDPE.energiePhotovoltaique,
      });
    }
  }

  completeModeVie(res: HttpResponse<IModeVie>): void {
    if (res && res.body) {
      this.modeVie = res.body;
    }
    if (this.modeVie) {
      this.modeVieForm.patchValue({
        id: this.modeVie.id,
        nbPersonnes: this.modeVie.nbPersonnes,
        presenceMatinSemaine: this.modeVie.presenceMatinSemaine,
        presenceMatinWE: this.modeVie.presenceMatinWE,
        presenceAMSemaine: this.modeVie.presenceAMSemaine,
        presenceAMWE: this.modeVie.presenceAMWE,
        presenceSoirSemaine: this.modeVie.presenceSoirSemaine,
        presenceSoirWE: this.modeVie.presenceSoirWE,
        presenceNuitSemaine: this.modeVie.presenceNuitSemaine,
        presenceNuitWE: this.modeVie.presenceNuitWE,
        semainesAbsenceHiver: this.modeVie.semainesAbsenceHiver,
        semainesAbsenceEte: this.modeVie.semainesAbsenceEte,
        client: this.modeVie.client,
      });
    }
  }

  completeConfort(res: HttpResponse<IConfort>): void {
    if (res && res.body) {
      this.confort = res.body;
    }
    if (this.confort) {
      this.confortForm.patchValue({
        id: this.confort.id,
        installationElectrique: this.confort.installationElectrique,
        installationGaz: this.confort.installationGaz,
        installationHumidite: this.confort.installationHumidite,
        installationPortesFenetres: this.confort.installationPortesFenetres,
        chauffageHiver: this.confort.chauffageHiver,
        surfaceChauffee: this.confort.surfaceChauffee,
        temperatureHiverSejour: this.confort.temperatureHiverSejour,
        temperatureHiverChambres: this.confort.temperatureHiverChambres,
        climEte: this.confort.climEte,
        temperatureEteSejour: this.confort.temperatureEteSejour,
        temperatureEteChambres: this.confort.temperatureEteChambres,
        electromenagers: this.confort.electromenagers,
        client: this.confort.client,
      });
    }
  }

  saveDPE(): void {
    this.isSaving = true;
    const numDpe = this.dpeForm.get(['numero'])!.value;
    if (numDpe) {
      this.subscribeToSaveResponse(this.infoDPEService.downloadInfoDPE(numDpe));
    }
  }

  delete(facture: IFacture): void {
    const modalRef = this.modalService.open(FactureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.facture = facture;
  }

  removeElectromenager(id: number): void {
    this.confortService.removeElectromenager(id).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  previousState(): void {
    window.history.back();
  }

  saveModeVie(): void {
    this.isSaving = true;
    const modeVie = this.createFromModeVieForm();
    if (modeVie.id !== undefined) {
      this.subscribeToSaveResponseModeVie(this.modeVieService.update(modeVie));
    } else {
      this.subscribeToSaveResponseModeVie(this.modeVieService.create(modeVie));
    }
  }

  saveConfort(): void {
    this.isSaving = true;
    const confort = this.createFromConfortForm();
    if (confort.id !== undefined) {
      this.subscribeToSaveResponseConfort(this.confortService.update(confort));
    } else {
      this.subscribeToSaveResponseConfort(this.confortService.create(confort));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfoDPE>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected subscribeToSaveResponseModeVie(result: Observable<HttpResponse<IModeVie>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected subscribeToSaveResponseConfort(result: Observable<HttpResponse<IConfort>>): void {
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

  private createFromModeVieForm(): IModeVie {
    return {
      ...new ModeVie(),
      id: this.modeVieForm.get(['id'])!.value,
      nbPersonnes: this.modeVieForm.get(['nbPersonnes'])!.value,
      presenceMatinSemaine: this.modeVieForm.get(['presenceMatinSemaine'])!.value,
      presenceMatinWE: this.modeVieForm.get(['presenceMatinWE'])!.value,
      presenceAMSemaine: this.modeVieForm.get(['presenceAMSemaine'])!.value,
      presenceAMWE: this.modeVieForm.get(['presenceAMWE'])!.value,
      presenceSoirSemaine: this.modeVieForm.get(['presenceSoirSemaine'])!.value,
      presenceSoirWE: this.modeVieForm.get(['presenceSoirWE'])!.value,
      presenceNuitSemaine: this.modeVieForm.get(['presenceNuitSemaine'])!.value,
      presenceNuitWE: this.modeVieForm.get(['presenceNuitWE'])!.value,
      semainesAbsenceHiver: this.modeVieForm.get(['semainesAbsenceHiver'])!.value,
      semainesAbsenceEte: this.modeVieForm.get(['semainesAbsenceEte'])!.value,
      client: this.modeVieForm.get(['client'])!.value,
    };
  }

  private createFromConfortForm(): IConfort {
    return {
      ...new Confort(),
      id: this.confortForm.get(['id'])!.value,
      installationElectrique: this.confortForm.get(['installationElectrique'])!.value,
      installationGaz: this.confortForm.get(['installationGaz'])!.value,
      installationHumidite: this.confortForm.get(['installationHumidite'])!.value,
      installationPortesFenetres: this.confortForm.get(['installationPortesFenetres'])!.value,
      chauffageHiver: this.confortForm.get(['chauffageHiver'])!.value,
      surfaceChauffee: this.confortForm.get(['surfaceChauffee'])!.value,
      temperatureHiverSejour: this.confortForm.get(['temperatureHiverSejour'])!.value,
      temperatureHiverChambres: this.confortForm.get(['temperatureHiverChambres'])!.value,
      climEte: this.confortForm.get(['climEte'])!.value,
      temperatureEteSejour: this.confortForm.get(['temperatureEteSejour'])!.value,
      temperatureEteChambres: this.confortForm.get(['temperatureEteChambres'])!.value,
      electromenagers: this.confortForm.get(['electromenagers'])!.value,
      client: this.confortForm.get(['client'])!.value,
    };
  }
}
