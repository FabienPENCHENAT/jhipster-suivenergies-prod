import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IElectromenager, Electromenager } from 'app/shared/model/electromenager.model';
import { ElectromenagerService } from './electromenager.service';
import { ElectromenagerComponent } from './electromenager.component';
import { ElectromenagerDetailComponent } from './electromenager-detail.component';
import { ElectromenagerUpdateComponent } from './electromenager-update.component';

@Injectable({ providedIn: 'root' })
export class ElectromenagerResolve implements Resolve<IElectromenager> {
  constructor(private service: ElectromenagerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IElectromenager> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((electromenager: HttpResponse<Electromenager>) => {
          if (electromenager.body) {
            return of(electromenager.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Electromenager());
  }
}

export const electromenagerRoute: Routes = [
  {
    path: '',
    component: ElectromenagerComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Electromenagers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ElectromenagerDetailComponent,
    resolve: {
      electromenager: ElectromenagerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Electromenagers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ElectromenagerUpdateComponent,
    resolve: {
      electromenager: ElectromenagerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Electromenagers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ElectromenagerUpdateComponent,
    resolve: {
      electromenager: ElectromenagerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Electromenagers',
    },
    canActivate: [UserRouteAccessService],
  },
];
