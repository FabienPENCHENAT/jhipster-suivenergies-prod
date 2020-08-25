import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD015ProductionEnergiesComponent } from './td-015-production-energies.component';
import { TD015ProductionEnergiesDetailComponent } from './td-015-production-energies-detail.component';
import { TD015ProductionEnergiesUpdateComponent } from './td-015-production-energies-update.component';
import { TD015ProductionEnergiesDeleteDialogComponent } from './td-015-production-energies-delete-dialog.component';
import { tD015ProductionEnergiesRoute } from './td-015-production-energies.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD015ProductionEnergiesRoute)],
  declarations: [
    TD015ProductionEnergiesComponent,
    TD015ProductionEnergiesDetailComponent,
    TD015ProductionEnergiesUpdateComponent,
    TD015ProductionEnergiesDeleteDialogComponent,
  ],
  entryComponents: [TD015ProductionEnergiesDeleteDialogComponent],
})
export class SuivEnergiesTD015ProductionEnergiesModule {}
