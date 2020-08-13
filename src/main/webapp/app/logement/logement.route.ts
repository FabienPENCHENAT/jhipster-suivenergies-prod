import { Route } from '@angular/router';

import { LogementComponent } from './logement.component';

export const logementRoute: Route = {
  path: 'logement',
  component: LogementComponent,
  data: {
    pageTitle: 'Mon logement et moi',
  },
};
