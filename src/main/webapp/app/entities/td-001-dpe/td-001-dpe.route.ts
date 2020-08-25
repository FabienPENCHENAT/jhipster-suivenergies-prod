import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD001DPE, TD001DPE } from 'app/shared/model/td-001-dpe.model';
import { TD001DPEService } from './td-001-dpe.service';
import { TD001DPEComponent } from './td-001-dpe.component';
import { TD001DPEDetailComponent } from './td-001-dpe-detail.component';
import { TD001DPEUpdateComponent } from './td-001-dpe-update.component';

@Injectable({ providedIn: 'root' })
export class TD001DPEResolve implements Resolve<ITD001DPE> {
  constructor(private service: TD001DPEService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD001DPE> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD001DPE: HttpResponse<TD001DPE>) => {
          if (tD001DPE.body) {
            return of(tD001DPE.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD001DPE());
  }
}

export const tD001DPERoute: Routes = [
  {
    path: '',
    component: TD001DPEComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD001DPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD001DPEDetailComponent,
    resolve: {
      tD001DPE: TD001DPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD001DPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD001DPEUpdateComponent,
    resolve: {
      tD001DPE: TD001DPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD001DPES',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD001DPEUpdateComponent,
    resolve: {
      tD001DPE: TD001DPEResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD001DPES',
    },
    canActivate: [UserRouteAccessService],
  },
];
