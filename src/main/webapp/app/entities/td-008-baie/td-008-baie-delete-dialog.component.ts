import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD008Baie } from 'app/shared/model/td-008-baie.model';
import { TD008BaieService } from './td-008-baie.service';

@Component({
  templateUrl: './td-008-baie-delete-dialog.component.html',
})
export class TD008BaieDeleteDialogComponent {
  tD008Baie?: ITD008Baie;

  constructor(protected tD008BaieService: TD008BaieService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD008BaieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD008BaieListModification');
      this.activeModal.close();
    });
  }
}
