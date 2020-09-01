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
      {
        path: 'td-001-dpe',
        loadChildren: () => import('./td-001-dpe/td-001-dpe.module').then(m => m.SuivEnergiesTD001DPEModule),
      },
      {
        path: 'td-002-consommations',
        loadChildren: () => import('./td-002-consommations/td-002-consommations.module').then(m => m.SuivEnergiesTD002ConsommationsModule),
      },
      {
        path: 'td-006-batiment',
        loadChildren: () => import('./td-006-batiment/td-006-batiment.module').then(m => m.SuivEnergiesTD006BatimentModule),
      },
      {
        path: 'td-007-paroi-opaque',
        loadChildren: () => import('./td-007-paroi-opaque/td-007-paroi-opaque.module').then(m => m.SuivEnergiesTD007ParoiOpaqueModule),
      },
      {
        path: 'td-008-baie',
        loadChildren: () => import('./td-008-baie/td-008-baie.module').then(m => m.SuivEnergiesTD008BaieModule),
      },
      {
        path: 'td-010-ponts-thermiques',
        loadChildren: () =>
          import('./td-010-ponts-thermiques/td-010-ponts-thermiques.module').then(m => m.SuivEnergiesTD010PontsThermiquesModule),
      },
      {
        path: 'td-011-instalation-chauffage',
        loadChildren: () =>
          import('./td-011-instalation-chauffage/td-011-instalation-chauffage.module').then(
            m => m.SuivEnergiesTD011InstalationChauffageModule
          ),
      },
      {
        path: 'td-012-generateur-chauffage',
        loadChildren: () =>
          import('./td-012-generateur-chauffage/td-012-generateur-chauffage.module').then(
            m => m.SuivEnergiesTD012GenerateurChauffageModule
          ),
      },
      {
        path: 'td-013-instalation-ecs',
        loadChildren: () =>
          import('./td-013-instalation-ecs/td-013-instalation-ecs.module').then(m => m.SuivEnergiesTD013InstalationECSModule),
      },
      {
        path: 'td-014-generateur-ecs',
        loadChildren: () =>
          import('./td-014-generateur-ecs/td-014-generateur-ecs.module').then(m => m.SuivEnergiesTD014GenerateurECSModule),
      },
      {
        path: 'td-015-production-energies',
        loadChildren: () =>
          import('./td-015-production-energies/td-015-production-energies.module').then(m => m.SuivEnergiesTD015ProductionEnergiesModule),
      },
      {
        path: 'td-017-consommation-neuf',
        loadChildren: () =>
          import('./td-017-consommation-neuf/td-017-consommation-neuf.module').then(m => m.SuivEnergiesTD017ConsommationNeufModule),
      },
      {
        path: 'confort',
        loadChildren: () => import('./confort/confort.module').then(m => m.SuivEnergiesConfortModule),
      },
      {
        path: 'electromenager',
        loadChildren: () => import('./electromenager/electromenager.module').then(m => m.SuivEnergiesElectromenagerModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SuivEnergiesEntityModule {}
