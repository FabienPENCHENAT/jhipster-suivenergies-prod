import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { IModeVie, ModeVie } from 'app/shared/model/mode-vie.model';
import { IFacture, Facture } from 'app/shared/model/facture.model';
import { FacturesByType } from 'app/shared/model/facturesByType.model';
import { EnergiesFacture } from 'app/shared/model/enumerations/energies-facture.model';
import { LogementService } from './logement.service';
import { InfoDPEService } from '../entities/info-dpe/info-dpe.service';
import { FactureService } from '../entities/facture/facture.service';
import { ModeVieService } from '../entities/mode-vie/mode-vie.service';

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
  eventSubscriber?: Subscription;

  dpeForm = this.fb.group({
    numero: [''],
    adresse: [''],
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

  constructor(
    private logementService: LogementService,
    protected infoDPEService: InfoDPEService,
    protected factureService: FactureService,
    protected modeVieService: ModeVieService,
    private fb: FormBuilder,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.infoDPEService.findLast().subscribe((dpe: HttpResponse<IInfoDPE>) => this.completeDpeForm(dpe));
    this.loadAllFactures();
    this.modeVieService.findOneByClient().subscribe((res: HttpResponse<IModeVie>) => this.completeModeVie(res));
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
        adresse: this.infoDPE.adresse,
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

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
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
}
