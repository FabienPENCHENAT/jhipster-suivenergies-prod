import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD010PontsThermiques, TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';
import { TD010PontsThermiquesService } from './td-010-ponts-thermiques.service';
import { TD010PontsThermiquesComponent } from './td-010-ponts-thermiques.component';
import { TD010PontsThermiquesDetailComponent } from './td-010-ponts-thermiques-detail.component';
import { TD010PontsThermiquesUpdateComponent } from './td-010-ponts-thermiques-update.component';

@Injectable({ providedIn: 'root' })
export class TD010PontsThermiquesResolve implements Resolve<ITD010PontsThermiques> {
  constructor(private service: TD010PontsThermiquesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD010PontsThermiques> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD010PontsThermiques: HttpResponse<TD010PontsThermiques>) => {
          if (tD010PontsThermiques.body) {
            return of(tD010PontsThermiques.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD010PontsThermiques());
  }
}

export const tD010PontsThermiquesRoute: Routes = [
  {
    path: '',
    component: TD010PontsThermiquesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD010PontsThermiques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD010PontsThermiquesDetailComponent,
    resolve: {
      tD010PontsThermiques: TD010PontsThermiquesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD010PontsThermiques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD010PontsThermiquesUpdateComponent,
    resolve: {
      tD010PontsThermiques: TD010PontsThermiquesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD010PontsThermiques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD010PontsThermiquesUpdateComponent,
    resolve: {
      tD010PontsThermiques: TD010PontsThermiquesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD010PontsThermiques',
    },
    canActivate: [UserRouteAccessService],
  },
];
