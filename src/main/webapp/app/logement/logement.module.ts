import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SuivEnergiesSharedModule } from 'app/shared/shared.module';

import { LogementComponent } from './logement.component';

import { logementRoute } from './logement.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forChild([logementRoute])],
  declarations: [LogementComponent],
})
export class LogementModule {}
