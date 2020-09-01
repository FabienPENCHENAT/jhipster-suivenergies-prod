import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfort } from 'app/shared/model/confort.model';
import { ConfortService } from './confort.service';

@Component({
  templateUrl: './confort-delete-dialog.component.html',
})
export class ConfortDeleteDialogComponent {
  confort?: IConfort;

  constructor(protected confortService: ConfortService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.confortService.delete(id).subscribe(() => {
      this.eventManager.broadcast('confortListModification');
      this.activeModal.close();
    });
  }
}
