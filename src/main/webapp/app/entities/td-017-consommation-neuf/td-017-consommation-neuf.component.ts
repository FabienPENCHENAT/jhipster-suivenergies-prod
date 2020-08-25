import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';
import { TD017ConsommationNeufService } from './td-017-consommation-neuf.service';
import { TD017ConsommationNeufDeleteDialogComponent } from './td-017-consommation-neuf-delete-dialog.component';

@Component({
  selector: 'jhi-td-017-consommation-neuf',
  templateUrl: './td-017-consommation-neuf.component.html',
})
export class TD017ConsommationNeufComponent implements OnInit, OnDestroy {
  tD017ConsommationNeufs?: ITD017ConsommationNeuf[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD017ConsommationNeufService: TD017ConsommationNeufService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD017ConsommationNeufService
      .query()
      .subscribe((res: HttpResponse<ITD017ConsommationNeuf[]>) => (this.tD017ConsommationNeufs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD017ConsommationNeufs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD017ConsommationNeuf): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD017ConsommationNeufs(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD017ConsommationNeufListModification', () => this.loadAll());
  }

  delete(tD017ConsommationNeuf: ITD017ConsommationNeuf): void {
    const modalRef = this.modalService.open(TD017ConsommationNeufDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD017ConsommationNeuf = tD017ConsommationNeuf;
  }
}
