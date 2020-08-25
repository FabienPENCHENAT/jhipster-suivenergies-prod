import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { TD014GenerateurECSService } from './td-014-generateur-ecs.service';

@Component({
  templateUrl: './td-014-generateur-ecs-delete-dialog.component.html',
})
export class TD014GenerateurECSDeleteDialogComponent {
  tD014GenerateurECS?: ITD014GenerateurECS;

  constructor(
    protected tD014GenerateurECSService: TD014GenerateurECSService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD014GenerateurECSService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD014GenerateurECSListModification');
      this.activeModal.close();
    });
  }
}
