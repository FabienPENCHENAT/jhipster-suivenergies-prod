import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { TD006BatimentService } from './td-006-batiment.service';

@Component({
  templateUrl: './td-006-batiment-delete-dialog.component.html',
})
export class TD006BatimentDeleteDialogComponent {
  tD006Batiment?: ITD006Batiment;

  constructor(
    protected tD006BatimentService: TD006BatimentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD006BatimentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD006BatimentListModification');
      this.activeModal.close();
    });
  }
}
