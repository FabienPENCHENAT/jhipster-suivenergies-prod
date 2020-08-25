import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD007ParoiOpaqueComponent } from './td-007-paroi-opaque.component';
import { TD007ParoiOpaqueDetailComponent } from './td-007-paroi-opaque-detail.component';
import { TD007ParoiOpaqueUpdateComponent } from './td-007-paroi-opaque-update.component';
import { TD007ParoiOpaqueDeleteDialogComponent } from './td-007-paroi-opaque-delete-dialog.component';
import { tD007ParoiOpaqueRoute } from './td-007-paroi-opaque.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD007ParoiOpaqueRoute)],
  declarations: [
    TD007ParoiOpaqueComponent,
    TD007ParoiOpaqueDetailComponent,
    TD007ParoiOpaqueUpdateComponent,
    TD007ParoiOpaqueDeleteDialogComponent,
  ],
  entryComponents: [TD007ParoiOpaqueDeleteDialogComponent],
})
export class SuivEnergiesTD007ParoiOpaqueModule {}
