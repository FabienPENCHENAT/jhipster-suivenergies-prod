import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD001DPEComponent } from './td-001-dpe.component';
import { TD001DPEDetailComponent } from './td-001-dpe-detail.component';
import { TD001DPEUpdateComponent } from './td-001-dpe-update.component';
import { TD001DPEDeleteDialogComponent } from './td-001-dpe-delete-dialog.component';
import { tD001DPERoute } from './td-001-dpe.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD001DPERoute)],
  declarations: [TD001DPEComponent, TD001DPEDetailComponent, TD001DPEUpdateComponent, TD001DPEDeleteDialogComponent],
  entryComponents: [TD001DPEDeleteDialogComponent],
})
export class SuivEnergiesTD001DPEModule {}
