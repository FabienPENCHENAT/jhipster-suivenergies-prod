import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD002ConsommationsComponent } from './td-002-consommations.component';
import { TD002ConsommationsDetailComponent } from './td-002-consommations-detail.component';
import { TD002ConsommationsUpdateComponent } from './td-002-consommations-update.component';
import { TD002ConsommationsDeleteDialogComponent } from './td-002-consommations-delete-dialog.component';
import { tD002ConsommationsRoute } from './td-002-consommations.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD002ConsommationsRoute)],
  declarations: [
    TD002ConsommationsComponent,
    TD002ConsommationsDetailComponent,
    TD002ConsommationsUpdateComponent,
    TD002ConsommationsDeleteDialogComponent,
  ],
  entryComponents: [TD002ConsommationsDeleteDialogComponent],
})
export class SuivEnergiesTD002ConsommationsModule {}
