import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { InfoDPEComponent } from './info-dpe.component';
import { InfoDPEDetailComponent } from './info-dpe-detail.component';
import { InfoDPEUpdateComponent } from './info-dpe-update.component';
import { InfoDPEDeleteDialogComponent } from './info-dpe-delete-dialog.component';
import { infoDPERoute } from './info-dpe.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(infoDPERoute)],
  declarations: [InfoDPEComponent, InfoDPEDetailComponent, InfoDPEUpdateComponent, InfoDPEDeleteDialogComponent],
  entryComponents: [InfoDPEDeleteDialogComponent],
})
export class SuivEnergiesInfoDPEModule {}
