import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD008Baie } from 'app/shared/model/td-008-baie.model';
import { TD008BaieService } from './td-008-baie.service';
import { TD008BaieDeleteDialogComponent } from './td-008-baie-delete-dialog.component';

@Component({
  selector: 'jhi-td-008-baie',
  templateUrl: './td-008-baie.component.html',
})
export class TD008BaieComponent implements OnInit, OnDestroy {
  tD008Baies?: ITD008Baie[];
  eventSubscriber?: Subscription;

  constructor(protected tD008BaieService: TD008BaieService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tD008BaieService.query().subscribe((res: HttpResponse<ITD008Baie[]>) => (this.tD008Baies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD008Baies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD008Baie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD008Baies(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD008BaieListModification', () => this.loadAll());
  }

  delete(tD008Baie: ITD008Baie): void {
    const modalRef = this.modalService.open(TD008BaieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD008Baie = tD008Baie;
  }
}
