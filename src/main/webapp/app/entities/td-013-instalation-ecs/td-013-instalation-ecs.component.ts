import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { TD013InstalationECSService } from './td-013-instalation-ecs.service';
import { TD013InstalationECSDeleteDialogComponent } from './td-013-instalation-ecs-delete-dialog.component';

@Component({
  selector: 'jhi-td-013-instalation-ecs',
  templateUrl: './td-013-instalation-ecs.component.html',
})
export class TD013InstalationECSComponent implements OnInit, OnDestroy {
  tD013InstalationECS?: ITD013InstalationECS[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD013InstalationECSService: TD013InstalationECSService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD013InstalationECSService
      .query()
      .subscribe((res: HttpResponse<ITD013InstalationECS[]>) => (this.tD013InstalationECS = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD013InstalationECS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD013InstalationECS): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD013InstalationECS(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD013InstalationECSListModification', () => this.loadAll());
  }

  delete(tD013InstalationECS: ITD013InstalationECS): void {
    const modalRef = this.modalService.open(TD013InstalationECSDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD013InstalationECS = tD013InstalationECS;
  }
}
