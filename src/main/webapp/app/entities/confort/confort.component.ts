import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConfort } from 'app/shared/model/confort.model';
import { ConfortService } from './confort.service';
import { ConfortDeleteDialogComponent } from './confort-delete-dialog.component';

@Component({
  selector: 'jhi-confort',
  templateUrl: './confort.component.html',
})
export class ConfortComponent implements OnInit, OnDestroy {
  conforts?: IConfort[];
  eventSubscriber?: Subscription;

  constructor(protected confortService: ConfortService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.confortService.query().subscribe((res: HttpResponse<IConfort[]>) => (this.conforts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConforts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConfort): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConforts(): void {
    this.eventSubscriber = this.eventManager.subscribe('confortListModification', () => this.loadAll());
  }

  delete(confort: IConfort): void {
    const modalRef = this.modalService.open(ConfortDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.confort = confort;
  }
}
