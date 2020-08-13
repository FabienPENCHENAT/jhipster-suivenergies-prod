import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInfoDPE, InfoDPE } from 'app/shared/model/info-dpe.model';
import { InfoDPEService } from './info-dpe.service';
import { InfoDPEComponent } from './info-dpe.component';
import { InfoDPEDetailComponent } from './info-dpe-detail.component';
import { InfoDPEUpdateComponent } from './info-dpe-update.component';

@Injectable({ providedIn: 'root' })
export class InfoDPEResolve implements Resolve<IInfoDPE> {
  constructor(private service: InfoDPEService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInfoDPE> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((infoDPE: HttpResponse<InfoDPE>) => {
          if (infoDPE.body) {
            return of(infoDPE.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InfoDPE());
  }
}

export const infoDPERoute: Routes = [
  {
    path: '',
    component: InfoDPEComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'InfoDPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InfoDPEDetailComponent,
    resolve: {
      infoDPE: InfoDPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'InfoDPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InfoDPEUpdateComponent,
    resolve: {
      infoDPE: InfoDPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'InfoDPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InfoDPEUpdateComponent,
    resolve: {
      infoDPE: InfoDPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'InfoDPES',
    },
    canActivate: [UserRouteAccessService],
  },
];
