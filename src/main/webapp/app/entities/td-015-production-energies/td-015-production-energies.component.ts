import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { TD015ProductionEnergiesService } from './td-015-production-energies.service';
import { TD015ProductionEnergiesDeleteDialogComponent } from './td-015-production-energies-delete-dialog.component';

@Component({
  selector: 'jhi-td-015-production-energies',
  templateUrl: './td-015-production-energies.component.html',
})
export class TD015ProductionEnergiesComponent implements OnInit, OnDestroy {
  tD015ProductionEnergies?: ITD015ProductionEnergies[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD015ProductionEnergiesService: TD015ProductionEnergiesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD015ProductionEnergiesService
      .query()
      .subscribe((res: HttpResponse<ITD015ProductionEnergies[]>) => (this.tD015ProductionEnergies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD015ProductionEnergies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD015ProductionEnergies): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD015ProductionEnergies(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD015ProductionEnergiesListModification', () => this.loadAll());
  }

  delete(tD015ProductionEnergies: ITD015ProductionEnergies): void {
    const modalRef = this.modalService.open(TD015ProductionEnergiesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD015ProductionEnergies = tD015ProductionEnergies;
  }
}
