import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';
import { TD017ConsommationNeufService } from './td-017-consommation-neuf.service';

@Component({
  templateUrl: './td-017-consommation-neuf-delete-dialog.component.html',
})
export class TD017ConsommationNeufDeleteDialogComponent {
  tD017ConsommationNeuf?: ITD017ConsommationNeuf;

  constructor(
    protected tD017ConsommationNeufService: TD017ConsommationNeufService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD017ConsommationNeufService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD017ConsommationNeufListModification');
      this.activeModal.close();
    });
  }
}
