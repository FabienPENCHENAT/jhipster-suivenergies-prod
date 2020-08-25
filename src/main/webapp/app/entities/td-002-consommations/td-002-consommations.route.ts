import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD002Consommations, TD002Consommations } from 'app/shared/model/td-002-consommations.model';
import { TD002ConsommationsService } from './td-002-consommations.service';
import { TD002ConsommationsComponent } from './td-002-consommations.component';
import { TD002ConsommationsDetailComponent } from './td-002-consommations-detail.component';
import { TD002ConsommationsUpdateComponent } from './td-002-consommations-update.component';

@Injectable({ providedIn: 'root' })
export class TD002ConsommationsResolve implements Resolve<ITD002Consommations> {
  constructor(private service: TD002ConsommationsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD002Consommations> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD002Consommations: HttpResponse<TD002Consommations>) => {
          if (tD002Consommations.body) {
            return of(tD002Consommations.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD002Consommations());
  }
}

export const tD002ConsommationsRoute: Routes = [
  {
    path: '',
    component: TD002ConsommationsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD002Consommations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD002ConsommationsDetailComponent,
    resolve: {
      tD002Consommations: TD002ConsommationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD002Consommations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD002ConsommationsUpdateComponent,
    resolve: {
      tD002Consommations: TD002ConsommationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD002Consommations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD002ConsommationsUpdateComponent,
    resolve: {
      tD002Consommations: TD002ConsommationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD002Consommations',
    },
    canActivate: [UserRouteAccessService],
  },
];
