import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SuivEnergiesSharedModule } from 'app/shared/shared.module';

import { ConsommationComponent } from './consommation.component';
import { consommationRoute } from './consommation.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forRoot(consommationRoute)],
  declarations: [ConsommationComponent],
})

export class ConsommationModule {}