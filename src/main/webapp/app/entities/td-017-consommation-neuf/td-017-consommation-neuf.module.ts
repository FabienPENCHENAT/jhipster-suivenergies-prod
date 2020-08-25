import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD017ConsommationNeufComponent } from './td-017-consommation-neuf.component';
import { TD017ConsommationNeufDetailComponent } from './td-017-consommation-neuf-detail.component';
import { TD017ConsommationNeufUpdateComponent } from './td-017-consommation-neuf-update.component';
import { TD017ConsommationNeufDeleteDialogComponent } from './td-017-consommation-neuf-delete-dialog.component';
import { tD017ConsommationNeufRoute } from './td-017-consommation-neuf.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD017ConsommationNeufRoute)],
  declarations: [
    TD017ConsommationNeufComponent,
    TD017ConsommationNeufDetailComponent,
    TD017ConsommationNeufUpdateComponent,
    TD017ConsommationNeufDeleteDialogComponent,
  ],
  entryComponents: [TD017ConsommationNeufDeleteDialogComponent],
})
export class SuivEnergiesTD017ConsommationNeufModule {}
