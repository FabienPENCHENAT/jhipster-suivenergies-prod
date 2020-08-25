import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { TD012GenerateurChauffageComponent } from './td-012-generateur-chauffage.component';
import { TD012GenerateurChauffageDetailComponent } from './td-012-generateur-chauffage-detail.component';
import { TD012GenerateurChauffageUpdateComponent } from './td-012-generateur-chauffage-update.component';
import { TD012GenerateurChauffageDeleteDialogComponent } from './td-012-generateur-chauffage-delete-dialog.component';
import { tD012GenerateurChauffageRoute } from './td-012-generateur-chauffage.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild(tD012GenerateurChauffageRoute)],
  declarations: [
    TD012GenerateurChauffageComponent,
    TD012GenerateurChauffageDetailComponent,
    TD012GenerateurChauffageUpdateComponent,
    TD012GenerateurChauffageDeleteDialogComponent,
  ],
  entryComponents: [TD012GenerateurChauffageDeleteDialogComponent],
})
export class SuivEnergiesTD012GenerateurChauffageModule {}
