import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { TD012GenerateurChauffageService } from './td-012-generateur-chauffage.service';
import { TD012GenerateurChauffageDeleteDialogComponent } from './td-012-generateur-chauffage-delete-dialog.component';

@Component({
  selector: 'jhi-td-012-generateur-chauffage',
  templateUrl: './td-012-generateur-chauffage.component.html',
})
export class TD012GenerateurChauffageComponent implements OnInit, OnDestroy {
  tD012GenerateurChauffages?: ITD012GenerateurChauffage[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD012GenerateurChauffageService: TD012GenerateurChauffageService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD012GenerateurChauffageService
      .query()
      .subscribe((res: HttpResponse<ITD012GenerateurChauffage[]>) => (this.tD012GenerateurChauffages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD012GenerateurChauffages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD012GenerateurChauffage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD012GenerateurChauffages(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD012GenerateurChauffageListModification', () => this.loadAll());
  }

  delete(tD012GenerateurChauffage: ITD012GenerateurChauffage): void {
    const modalRef = this.modalService.open(TD012GenerateurChauffageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD012GenerateurChauffage = tD012GenerateurChauffage;
  }
}
