import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { ModeVieComponent } from './mode-vie.component';
import { ModeVieDetailComponent } from './mode-vie-detail.component';
import { ModeVieUpdateComponent } from './mode-vie-update.component';
import { ModeVieDeleteDialogComponent } from './mode-vie-delete-dialog.component';
import { modeVieRoute } from './mode-vie.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(modeVieRoute)],
  declarations: [ModeVieComponent, ModeVieDetailComponent, ModeVieUpdateComponent, ModeVieDeleteDialogComponent],
  entryComponents: [ModeVieDeleteDialogComponent],
})
export class SuivEnergiesModeVieModule {}
