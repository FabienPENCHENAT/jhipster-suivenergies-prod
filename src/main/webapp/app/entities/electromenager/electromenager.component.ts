import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IElectromenager } from 'app/shared/model/electromenager.model';
import { ElectromenagerService } from './electromenager.service';
import { ElectromenagerDeleteDialogComponent } from './electromenager-delete-dialog.component';

@Component({
  selector: 'jhi-electromenager',
  templateUrl: './electromenager.component.html',
})
export class ElectromenagerComponent implements OnInit, OnDestroy {
  electromenagers?: IElectromenager[];
  eventSubscriber?: Subscription;

  constructor(
    protected electromenagerService: ElectromenagerService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.electromenagerService.query().subscribe((res: HttpResponse<IElectromenager[]>) => (this.electromenagers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInElectromenagers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IElectromenager): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInElectromenagers(): void {
    this.eventSubscriber = this.eventManager.subscribe('electromenagerListModification', () => this.loadAll());
  }

  delete(electromenager: IElectromenager): void {
    const modalRef = this.modalService.open(ElectromenagerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.electromenager = electromenager;
  }
}
