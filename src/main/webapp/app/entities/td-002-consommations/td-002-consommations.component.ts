import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { TD002ConsommationsService } from './td-002-consommations.service';
import { TD002ConsommationsDeleteDialogComponent } from './td-002-consommations-delete-dialog.component';

@Component({
  selector: 'jhi-td-002-consommations',
  templateUrl: './td-002-consommations.component.html',
})
export class TD002ConsommationsComponent implements OnInit, OnDestroy {
  tD002Consommations?: ITD002Consommations[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD002ConsommationsService: TD002ConsommationsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD002ConsommationsService
      .query()
      .subscribe((res: HttpResponse<ITD002Consommations[]>) => (this.tD002Consommations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD002Consommations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD002Consommations): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD002Consommations(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD002ConsommationsListModification', () => this.loadAll());
  }

  delete(tD002Consommations: ITD002Consommations): void {
    const modalRef = this.modalService.open(TD002ConsommationsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD002Consommations = tD002Consommations;
  }
}
