import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { TD013InstalationECSService } from './td-013-instalation-ecs.service';

@Component({
  templateUrl: './td-013-instalation-ecs-delete-dialog.component.html',
})
export class TD013InstalationECSDeleteDialogComponent {
  tD013InstalationECS?: ITD013InstalationECS;

  constructor(
    protected tD013InstalationECSService: TD013InstalationECSService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tD013InstalationECSService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tD013InstalationECSListModification');
      this.activeModal.close();
    });
  }
}
