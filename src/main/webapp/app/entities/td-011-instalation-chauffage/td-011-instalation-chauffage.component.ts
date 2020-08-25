import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { TD011InstalationChauffageService } from './td-011-instalation-chauffage.service';
import { TD011InstalationChauffageDeleteDialogComponent } from './td-011-instalation-chauffage-delete-dialog.component';

@Component({
  selector: 'jhi-td-011-instalation-chauffage',
  templateUrl: './td-011-instalation-chauffage.component.html',
})
export class TD011InstalationChauffageComponent implements OnInit, OnDestroy {
  tD011InstalationChauffages?: ITD011InstalationChauffage[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD011InstalationChauffageService: TD011InstalationChauffageService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD011InstalationChauffageService
      .query()
      .subscribe((res: HttpResponse<ITD011InstalationChauffage[]>) => (this.tD011InstalationChauffages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD011InstalationChauffages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD011InstalationChauffage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD011InstalationChauffages(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD011InstalationChauffageListModification', () => this.loadAll());
  }

  delete(tD011InstalationChauffage: ITD011InstalationChauffage): void {
    const modalRef = this.modalService.open(TD011InstalationChauffageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD011InstalationChauffage = tD011InstalationChauffage;
  }
}
