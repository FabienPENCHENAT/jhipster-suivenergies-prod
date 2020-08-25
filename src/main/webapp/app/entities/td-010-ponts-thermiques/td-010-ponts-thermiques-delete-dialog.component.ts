import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { TD010PontsThermiquesService } from './td-010-ponts-thermiques.service';

@Component({
  templateUrl: './td-010-ponts-thermiques-delete-dialog.component.html',
})
export class TD010PontsThermiquesDeleteDialogComponent {
  tD010PontsThermiques?: ITD010PontsThermiques;

  constructor(
    protected tD010PontsThermiquesService: TD010PontsThermiquesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD010PontsThermiquesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD010PontsThermiquesListModification');
      this.activeModal.close();
    });
  }
}
