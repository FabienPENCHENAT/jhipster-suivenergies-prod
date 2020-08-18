import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SuivEnergiesSharedModule } from 'app/shared/shared.module';

import { AccompagnementComponent } from './accompagnement.component';
import { accompagnementRoute } from './accompagnement.route';

@NgModule({
  imports: [SuivEnergiesSharedModule, RouterModule.forRoot(accompagnementRoute)],
  declarations: [AccompagnementComponent],
})

export class AccompagnementModule {}