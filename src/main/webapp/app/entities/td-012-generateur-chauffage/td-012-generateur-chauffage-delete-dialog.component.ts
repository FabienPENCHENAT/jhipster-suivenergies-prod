import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { TD012GenerateurChauffageService } from './td-012-generateur-chauffage.service';

@Component({
  templateUrl: './td-012-generateur-chauffage-delete-dialog.component.html',
})
export class TD012GenerateurChauffageDeleteDialogComponent {
  tD012GenerateurChauffage?: ITD012GenerateurChauffage;

  constructor(
    protected tD012GenerateurChauffageService: TD012GenerateurChauffageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD012GenerateurChauffageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD012GenerateurChauffageListModification');
      this.activeModal.close();
    });
  }
}
