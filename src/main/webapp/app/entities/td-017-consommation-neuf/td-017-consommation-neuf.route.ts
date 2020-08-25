import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD017ConsommationNeuf, TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';
import { TD017ConsommationNeufService } from './td-017-consommation-neuf.service';
import { TD017ConsommationNeufComponent } from './td-017-consommation-neuf.component';
import { TD017ConsommationNeufDetailComponent } from './td-017-consommation-neuf-detail.component';
import { TD017ConsommationNeufUpdateComponent } from './td-017-consommation-neuf-update.component';

@Injectable({ providedIn: 'root' })
export class TD017ConsommationNeufResolve implements Resolve<ITD017ConsommationNeuf> {
  constructor(private service: TD017ConsommationNeufService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD017ConsommationNeuf> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD017ConsommationNeuf: HttpResponse<TD017ConsommationNeuf>) => {
          if (tD017ConsommationNeuf.body) {
            return of(tD017ConsommationNeuf.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD017ConsommationNeuf());
  }
}

export const tD017ConsommationNeufRoute: Routes = [
  {
    path: '',
    component: TD017ConsommationNeufComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD017ConsommationNeufs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD017ConsommationNeufDetailComponent,
    resolve: {
      tD017ConsommationNeuf: TD017ConsommationNeufResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD017ConsommationNeufs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD017ConsommationNeufUpdateComponent,
    resolve: {
      tD017ConsommationNeuf: TD017ConsommationNeufResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD017ConsommationNeufs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD017ConsommationNeufUpdateComponent,
    resolve: {
      tD017ConsommationNeuf: TD017ConsommationNeufResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD017ConsommationNeufs',
    },
    canActivate: [UserRouteAccessService],
  },
];
