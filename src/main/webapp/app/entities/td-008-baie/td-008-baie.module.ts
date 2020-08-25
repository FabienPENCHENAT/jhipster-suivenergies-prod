import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD008BaieComponent } from './td-008-baie.component';
import { TD008BaieDetailComponent } from './td-008-baie-detail.component';
import { TD008BaieUpdateComponent } from './td-008-baie-update.component';
import { TD008BaieDeleteDialogComponent } from './td-008-baie-delete-dialog.component';
import { tD008BaieRoute } from './td-008-baie.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD008BaieRoute)],
  declarations: [TD008BaieComponent, TD008BaieDetailComponent, TD008BaieUpdateComponent, TD008BaieDeleteDialogComponent],
  entryComponents: [TD008BaieDeleteDialogComponent],
})
export class SuivEnergiesTD008BaieModule {}
