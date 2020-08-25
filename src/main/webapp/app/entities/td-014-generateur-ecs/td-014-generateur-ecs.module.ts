import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD014GenerateurECSComponent } from './td-014-generateur-ecs.component';
import { TD014GenerateurECSDetailComponent } from './td-014-generateur-ecs-detail.component';
import { TD014GenerateurECSUpdateComponent } from './td-014-generateur-ecs-update.component';
import { TD014GenerateurECSDeleteDialogComponent } from './td-014-generateur-ecs-delete-dialog.component';
import { tD014GenerateurECSRoute } from './td-014-generateur-ecs.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD014GenerateurECSRoute)],
  declarations: [
    TD014GenerateurECSComponent,
    TD014GenerateurECSDetailComponent,
    TD014GenerateurECSUpdateComponent,
    TD014GenerateurECSDeleteDialogComponent,
  ],
  entryComponents: [TD014GenerateurECSDeleteDialogComponent],
})
export class SuivEnergiesTD014GenerateurECSModule {}
