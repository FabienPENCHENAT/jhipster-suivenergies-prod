import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { TD014GenerateurECSService } from './td-014-generateur-ecs.service';
import { TD014GenerateurECSDeleteDialogComponent } from './td-014-generateur-ecs-delete-dialog.component';

@Component({
  selector: 'jhi-td-014-generateur-ecs',
  templateUrl: './td-014-generateur-ecs.component.html',
})
export class TD014GenerateurECSComponent implements OnInit, OnDestroy {
  tD014GenerateurECS?: ITD014GenerateurECS[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD014GenerateurECSService: TD014GenerateurECSService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD014GenerateurECSService
      .query()
      .subscribe((res: HttpResponse<ITD014GenerateurECS[]>) => (this.tD014GenerateurECS = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD014GenerateurECS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD014GenerateurECS): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD014GenerateurECS(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD014GenerateurECSListModification', () => this.loadAll());
  }

  delete(tD014GenerateurECS: ITD014GenerateurECS): void {
    const modalRef = this.modalService.open(TD014GenerateurECSDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD014GenerateurECS = tD014GenerateurECS;
  }
}
