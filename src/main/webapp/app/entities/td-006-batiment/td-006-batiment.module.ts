import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD006BatimentComponent } from './td-006-batiment.component';
import { TD006BatimentDetailComponent } from './td-006-batiment-detail.component';
import { TD006BatimentUpdateComponent } from './td-006-batiment-update.component';
import { TD006BatimentDeleteDialogComponent } from './td-006-batiment-delete-dialog.component';
import { tD006BatimentRoute } from './td-006-batiment.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD006BatimentRoute)],
  declarations: [TD006BatimentComponent, TD006BatimentDetailComponent, TD006BatimentUpdateComponent, TD006BatimentDeleteDialogComponent],
  entryComponents: [TD006BatimentDeleteDialogComponent],
})
export class SuivEnergiesTD006BatimentModule {}
