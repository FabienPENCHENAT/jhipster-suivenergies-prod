import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';
import { InfoDPEService } from './info-dpe.service';

@Component({
  templateUrl: './info-dpe-delete-dialog.component.html',
})
export class InfoDPEDeleteDialogComponent {
  infoDPE?: IInfoDPE;

  constructor(protected infoDPEService: InfoDPEService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.infoDPEService.delete(id).subscribe(() => {
      this.eventManager.broadcast('infoDPEListModification');
      this.activeModal.close();
    });
  }
}
