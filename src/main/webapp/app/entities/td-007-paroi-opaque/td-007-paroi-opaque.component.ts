import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { TD007ParoiOpaqueService } from './td-007-paroi-opaque.service';
import { TD007ParoiOpaqueDeleteDialogComponent } from './td-007-paroi-opaque-delete-dialog.component';

@Component({
  selector: 'jhi-td-007-paroi-opaque',
  templateUrl: './td-007-paroi-opaque.component.html',
})
export class TD007ParoiOpaqueComponent implements OnInit, OnDestroy {
  tD007ParoiOpaques?: ITD007ParoiOpaque[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD007ParoiOpaqueService: TD007ParoiOpaqueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD007ParoiOpaqueService.query().subscribe((res: HttpResponse<ITD007ParoiOpaque[]>) => (this.tD007ParoiOpaques = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD007ParoiOpaques();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD007ParoiOpaque): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD007ParoiOpaques(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD007ParoiOpaqueListModification', () => this.loadAll());
  }

  delete(tD007ParoiOpaque: ITD007ParoiOpaque): void {
    const modalRef = this.modalService.open(TD007ParoiOpaqueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD007ParoiOpaque = tD007ParoiOpaque;
  }
}
