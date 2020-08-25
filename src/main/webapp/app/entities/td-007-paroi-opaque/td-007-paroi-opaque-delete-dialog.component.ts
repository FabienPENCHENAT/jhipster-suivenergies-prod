import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { TD007ParoiOpaqueService } from './td-007-paroi-opaque.service';

@Component({
  templateUrl: './td-007-paroi-opaque-delete-dialog.component.html',
})
export class TD007ParoiOpaqueDeleteDialogComponent {
  tD007ParoiOpaque?: ITD007ParoiOpaque;

  constructor(
    protected tD007ParoiOpaqueService: TD007ParoiOpaqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD007ParoiOpaqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD007ParoiOpaqueListModification');
      this.activeModal.close();
    });
  }
}
