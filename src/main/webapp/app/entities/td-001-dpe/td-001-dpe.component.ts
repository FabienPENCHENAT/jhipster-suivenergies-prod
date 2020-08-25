import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';
import { TD001DPEService } from './td-001-dpe.service';
import { TD001DPEDeleteDialogComponent } from './td-001-dpe-delete-dialog.component';

@Component({
  selector: 'jhi-td-001-dpe',
  templateUrl: './td-001-dpe.component.html',
})
export class TD001DPEComponent implements OnInit, OnDestroy {
  tD001DPES?: ITD001DPE[];
  eventSubscriber?: Subscription;

  constructor(protected tD001DPEService: TD001DPEService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tD001DPEService.query().subscribe((res: HttpResponse<ITD001DPE[]>) => (this.tD001DPES = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD001DPES();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD001DPE): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD001DPES(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD001DPEListModification', () => this.loadAll());
  }

  delete(tD001DPE: ITD001DPE): void {
    const modalRef = this.modalService.open(TD001DPEDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD001DPE = tD001DPE;
  }
}
