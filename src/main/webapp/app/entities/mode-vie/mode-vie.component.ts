import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModeVie } from 'app/shared/model/mode-vie.model';
import { ModeVieService } from './mode-vie.service';
import { ModeVieDeleteDialogComponent } from './mode-vie-delete-dialog.component';

@Component({
  selector: 'jhi-mode-vie',
  templateUrl: './mode-vie.component.html',
})
export class ModeVieComponent implements OnInit, OnDestroy {
  modeVies?: IModeVie[];
  eventSubscriber?: Subscription;

  constructor(protected modeVieService: ModeVieService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.modeVieService.query().subscribe((res: HttpResponse<IModeVie[]>) => (this.modeVies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInModeVies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModeVie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModeVies(): void {
    this.eventSubscriber = this.eventManager.subscribe('modeVieListModification', () => this.loadAll());
  }

  delete(modeVie: IModeVie): void {
    const modalRef = this.modalService.open(ModeVieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.modeVie = modeVie;
  }
}
