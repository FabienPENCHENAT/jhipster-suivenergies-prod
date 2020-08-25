import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { TD002ConsommationsService } from './td-002-consommations.service';

@Component({
  templateUrl: './td-002-consommations-delete-dialog.component.html',
})
export class TD002ConsommationsDeleteDialogComponent {
  tD002Consommations?: ITD002Consommations;

  constructor(
    protected tD002ConsommationsService: TD002ConsommationsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD002ConsommationsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD002ConsommationsListModification');
      this.activeModal.close();
    });
  }
}
