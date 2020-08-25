import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { TD011InstalationChauffageService } from './td-011-instalation-chauffage.service';

@Component({
  templateUrl: './td-011-instalation-chauffage-delete-dialog.component.html',
})
export class TD011InstalationChauffageDeleteDialogComponent {
  tD011InstalationChauffage?: ITD011InstalationChauffage;

  constructor(
    protected tD011InstalationChauffageService: TD011InstalationChauffageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD011InstalationChauffageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD011InstalationChauffageListModification');
      this.activeModal.close();
    });
  }
}
