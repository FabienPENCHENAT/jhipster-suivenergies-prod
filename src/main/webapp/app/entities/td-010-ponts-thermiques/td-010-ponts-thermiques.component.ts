import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { TD010PontsThermiquesService } from './td-010-ponts-thermiques.service';
import { TD010PontsThermiquesDeleteDialogComponent } from './td-010-ponts-thermiques-delete-dialog.component';

@Component({
  selector: 'jhi-td-010-ponts-thermiques',
  templateUrl: './td-010-ponts-thermiques.component.html',
})
export class TD010PontsThermiquesComponent implements OnInit, OnDestroy {
  tD010PontsThermiques?: ITD010PontsThermiques[];
  eventSubscriber?: Subscription;

  constructor(
    protected tD010PontsThermiquesService: TD010PontsThermiquesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tD010PontsThermiquesService
      .query()
      .subscribe((res: HttpResponse<ITD010PontsThermiques[]>) => (this.tD010PontsThermiques = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTD010PontsThermiques();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITD010PontsThermiques): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTD010PontsThermiques(): void {
    this.eventSubscriber = this.eventManager.subscribe('tD010PontsThermiquesListModification', () => this.loadAll());
  }

  delete(tD010PontsThermiques: ITD010PontsThermiques): void {
    const modalRef = this.modalService.open(TD010PontsThermiquesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tD010PontsThermiques = tD010PontsThermiques;
  }
}
