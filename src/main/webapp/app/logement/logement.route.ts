import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInfoDPE, InfoDPE } from 'app/shared/model/info-dpe.model';
import { InfoDPEService } from '../entities/info-dpe/info-dpe.service';
import { LogementComponent } from './logement.component';

@Injectable({ providedIn: 'root' })
export class InfoDPEResolve implements Resolve<IInfoDPE> {
  constructor(private service: InfoDPEService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInfoDPE> | Observable<never> {
    return this.service.findLast().pipe(
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
}

export const logementRoute: Routes = [
  {
    path: 'logement',
    component: LogementComponent,
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LogementComponent,
    resolve: {
      infoDPE: InfoDPEResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
];
