import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModeVie } from 'app/shared/model/mode-vie.model';
import { ModeVieService } from './mode-vie.service';

@Component({
  templateUrl: './mode-vie-delete-dialog.component.html',
})
export class ModeVieDeleteDialogComponent {
  modeVie?: IModeVie;

  constructor(protected modeVieService: ModeVieService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modeVieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modeVieListModification');
      this.activeModal.close();
    });
  }
}
