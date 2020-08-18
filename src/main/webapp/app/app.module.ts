import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SuivEnergiesSharedModule } from 'app/shared/shared.module';
import { SuivEnergiesCoreModule } from 'app/core/core.module';
import { SuivEnergiesAppRoutingModule } from './app-routing.module';
import { LogementModule } from './logement/logement.module';
import { AccompagnementModule } from './accompagnement/accompagnement.module';
import { SuivEnergiesHomeModule } from './home/home.module';
import { SuivEnergiesEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SuivEnergiesSharedModule,
    SuivEnergiesCoreModule,
    SuivEnergiesHomeModule,
    LogementModule,
    AccompagnementModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SuivEnergiesEntityModule,
    SuivEnergiesAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class SuivEnergiesAppModule {}
