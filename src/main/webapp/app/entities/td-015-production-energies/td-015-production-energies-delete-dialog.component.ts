import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { TD015ProductionEnergiesService } from './td-015-production-energies.service';

@Component({
  templateUrl: './td-015-production-energies-delete-dialog.component.html',
})
export class TD015ProductionEnergiesDeleteDialogComponent {
  tD015ProductionEnergies?: ITD015ProductionEnergies;

  constructor(
    protected tD015ProductionEnergiesService: TD015ProductionEnergiesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD015ProductionEnergiesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD015ProductionEnergiesListModification');
      this.activeModal.close();
    });
  }
}
