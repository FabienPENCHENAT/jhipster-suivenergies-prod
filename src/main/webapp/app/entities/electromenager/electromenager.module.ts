import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { ElectromenagerComponent } from './electromenager.component';
import { ElectromenagerDetailComponent } from './electromenager-detail.component';
import { ElectromenagerUpdateComponent } from './electromenager-update.component';
import { ElectromenagerDeleteDialogComponent } from './electromenager-delete-dialog.component';
import { electromenagerRoute } from './electromenager.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(electromenagerRoute)],
  declarations: [
    ElectromenagerComponent,
    ElectromenagerDetailComponent,
    ElectromenagerUpdateComponent,
    ElectromenagerDeleteDialogComponent,
  ],
  entryComponents: [ElectromenagerDeleteDialogComponent],
})
export class SuivEnergiesElectromenagerModule {}
