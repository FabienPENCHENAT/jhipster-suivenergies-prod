import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElectromenager } from 'app/shared/model/electromenager.model';
import { ElectromenagerService } from './electromenager.service';

@Component({
  templateUrl: './electromenager-delete-dialog.component.html',
})
export class ElectromenagerDeleteDialogComponent {
  electromenager?: IElectromenager;

  constructor(
    protected electromenagerService: ElectromenagerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.electromenagerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('electromenagerListModification');
      this.activeModal.close();
    });
  }
}
