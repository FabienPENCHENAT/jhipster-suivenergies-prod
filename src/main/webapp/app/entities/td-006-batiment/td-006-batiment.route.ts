import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD006Batiment, TD006Batiment } from 'app/shared/model/td-006-batiment.model';
import { TD006BatimentService } from './td-006-batiment.service';
import { TD006BatimentComponent } from './td-006-batiment.component';
import { TD006BatimentDetailComponent } from './td-006-batiment-detail.component';
import { TD006BatimentUpdateComponent } from './td-006-batiment-update.component';

@Injectable({ providedIn: 'root' })
export class TD006BatimentResolve implements Resolve<ITD006Batiment> {
  constructor(private service: TD006BatimentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD006Batiment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD006Batiment: HttpResponse<TD006Batiment>) => {
          if (tD006Batiment.body) {
            return of(tD006Batiment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD006Batiment());
  }
}

export const tD006BatimentRoute: Routes = [
  {
    path: '',
    component: TD006BatimentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD006Batiments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD006BatimentDetailComponent,
    resolve: {
      tD006Batiment: TD006BatimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD006Batiments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD006BatimentUpdateComponent,
    resolve: {
      tD006Batiment: TD006BatimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD006Batiments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD006BatimentUpdateComponent,
    resolve: {
      tD006Batiment: TD006BatimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD006Batiments',
    },
    canActivate: [UserRouteAccessService],
  },
];
