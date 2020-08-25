import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';
import { TD001DPEService } from './td-001-dpe.service';

@Component({
  templateUrl: './td-001-dpe-delete-dialog.component.html',
})
export class TD001DPEDeleteDialogComponent {
  tD001DPE?: ITD001DPE;

  constructor(protected tD001DPEService: TD001DPEService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD001DPEService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD001DPEListModification');
      this.activeModal.close();
    });
  }
}
