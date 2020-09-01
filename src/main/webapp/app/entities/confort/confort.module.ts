import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { ConfortComponent } from './confort.component';
import { ConfortDetailComponent } from './confort-detail.component';
import { ConfortUpdateComponent } from './confort-update.component';
import { ConfortDeleteDialogComponent } from './confort-delete-dialog.component';
import { confortRoute } from './confort.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(confortRoute)],
  declarations: [ConfortComponent, ConfortDetailComponent, ConfortUpdateComponent, ConfortDeleteDialogComponent],
  entryComponents: [ConfortDeleteDialogComponent],
})
export class SuivEnergiesConfortModule {}
