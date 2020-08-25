import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD013InstalationECSComponent } from './td-013-instalation-ecs.component';
import { TD013InstalationECSDetailComponent } from './td-013-instalation-ecs-detail.component';
import { TD013InstalationECSUpdateComponent } from './td-013-instalation-ecs-update.component';
import { TD013InstalationECSDeleteDialogComponent } from './td-013-instalation-ecs-delete-dialog.component';
import { tD013InstalationECSRoute } from './td-013-instalation-ecs.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD013InstalationECSRoute)],
  declarations: [
    TD013InstalationECSComponent,
    TD013InstalationECSDetailComponent,
    TD013InstalationECSUpdateComponent,
    TD013InstalationECSDeleteDialogComponent,
  ],
  entryComponents: [TD013InstalationECSDeleteDialogComponent],
})
export class SuivEnergiesTD013InstalationECSModule {}
