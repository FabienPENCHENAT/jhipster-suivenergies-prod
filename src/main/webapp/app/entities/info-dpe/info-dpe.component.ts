import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { InfoDPEService } from './info-dpe.service';
import { InfoDPEDeleteDialogComponent } from './info-dpe-delete-dialog.component';

@Component({
  selector: 'jhi-info-dpe',
  templateUrl: './info-dpe.component.html',
})
export class InfoDPEComponent implements OnInit, OnDestroy {
  infoDPES?: IInfoDPE[];
  eventSubscriber?: Subscription;

  constructor(
    protected infoDPEService: InfoDPEService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.infoDPEService.query().subscribe((res: HttpResponse<IInfoDPE[]>) => (this.infoDPES = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInfoDPES();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInfoDPE): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInInfoDPES(): void {
    this.eventSubscriber = this.eventManager.subscribe('infoDPEListModification', () => this.loadAll());
  }

  delete(infoDPE: IInfoDPE): void {
    const modalRef = this.modalService.open(InfoDPEDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.infoDPE = infoDPE;
  }
}
