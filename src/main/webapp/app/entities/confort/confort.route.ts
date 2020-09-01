import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConfort, Confort } from 'app/shared/model/confort.model';
import { ConfortService } from './confort.service';
import { ConfortComponent } from './confort.component';
import { ConfortDetailComponent } from './confort-detail.component';
import { ConfortUpdateComponent } from './confort-update.component';

@Injectable({ providedIn: 'root' })
export class ConfortResolve implements Resolve<IConfort> {
  constructor(private service: ConfortService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConfort> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((confort: HttpResponse<Confort>) => {
          if (confort.body) {
            return of(confort.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Confort());
  }
}

export const confortRoute: Routes = [
  {
    path: '',
    component: ConfortComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Conforts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConfortDetailComponent,
    resolve: {
      confort: ConfortResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Conforts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConfortUpdateComponent,
    resolve: {
      confort: ConfortResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Conforts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConfortUpdateComponent,
    resolve: {
      confort: ConfortResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Conforts',
    },
    canActivate: [UserRouteAccessService],
  },
];
