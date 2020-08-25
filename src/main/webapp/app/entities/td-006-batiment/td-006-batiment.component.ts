import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { TD006BatimentService } from './td-006-batiment.service';
import { TD006BatimentDeleteDialogComponent } from './td-006-batiment-delete-dialog.component';

@Component({
  selector: 'jhi-td-006-batiment',
  templateUrl: './td-006-batiment.component.html',
})
export class TD006BatimentComponent implements OnInit, OnDestroy {
  tD006Batiments?: ITD006Batiment[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD006BatimentService: TD006BatimentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD006BatimentService.query().subscribe((res: HttpResponse<ITD006Batiment[]>) => (this.tD006Batiments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD006Batiments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD006Batiment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD006Batiments(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD006BatimentListModification', () => this.loadAll());
  }

  delete(tD006Batiment: ITD006Batiment): void {
    const modalRef = this.modalService.open(TD006BatimentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD006Batiment = tD006Batiment;
  }
}
