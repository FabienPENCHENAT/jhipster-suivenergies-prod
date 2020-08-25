import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD011InstalationChauffageComponent } from './td-011-instalation-chauffage.component';
import { TD011InstalationChauffageDetailComponent } from './td-011-instalation-chauffage-detail.component';
import { TD011InstalationChauffageUpdateComponent } from './td-011-instalation-chauffage-update.component';
import { TD011InstalationChauffageDeleteDialogComponent } from './td-011-instalation-chauffage-delete-dialog.component';
import { tD011InstalationChauffageRoute } from './td-011-instalation-chauffage.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD011InstalationChauffageRoute)],
  declarations: [
    TD011InstalationChauffageComponent,
    TD011InstalationChauffageDetailComponent,
    TD011InstalationChauffageUpdateComponent,
    TD011InstalationChauffageDeleteDialogComponent,
  ],
  entryComponents: [TD011InstalationChauffageDeleteDialogComponent],
})
export class SuivEnergiesTD011InstalationChauffageModule {}
