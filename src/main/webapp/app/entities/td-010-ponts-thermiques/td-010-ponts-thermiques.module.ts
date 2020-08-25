import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD010PontsThermiquesComponent } from './td-010-ponts-thermiques.component';
import { TD010PontsThermiquesDetailComponent } from './td-010-ponts-thermiques-detail.component';
import { TD010PontsThermiquesUpdateComponent } from './td-010-ponts-thermiques-update.component';
import { TD010PontsThermiquesDeleteDialogComponent } from './td-010-ponts-thermiques-delete-dialog.component';
import { tD010PontsThermiquesRoute } from './td-010-ponts-thermiques.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD010PontsThermiquesRoute)],
  declarations: [
    TD010PontsThermiquesComponent,
    TD010PontsThermiquesDetailComponent,
    TD010PontsThermiquesUpdateComponent,
    TD010PontsThermiquesDeleteDialogComponent,
  ],
  entryComponents: [TD010PontsThermiquesDeleteDialogComponent],
})
export class SuivEnergiesTD010PontsThermiquesModule {}
