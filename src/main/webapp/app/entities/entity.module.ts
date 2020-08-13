import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'info-dpe',
        loadChildren: () => import('./info-dpe/info-dpe.module').then(m => m.SuivEnergiesInfoDPEModule),
      },
      {
        path: 'facture',
        loadChildren: () => import('./facture/facture.module').then(m => m.SuivEnergiesFactureModule),
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.SuivEnergiesClientModule),
      },
      {
        path: 'mode-vie',
        loadChildren: () => import('./mode-vie/mode-vie.module').then(m => m.SuivEnergiesModeVieModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SuivEnergiesEntityModule {}
