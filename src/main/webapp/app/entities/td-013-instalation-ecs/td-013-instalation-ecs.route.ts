import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD013InstalationECS, TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';
import { TD013InstalationECSService } from './td-013-instalation-ecs.service';
import { TD013InstalationECSComponent } from './td-013-instalation-ecs.component';
import { TD013InstalationECSDetailComponent } from './td-013-instalation-ecs-detail.component';
import { TD013InstalationECSUpdateComponent } from './td-013-instalation-ecs-update.component';

@Injectable({ providedIn: 'root' })
export class TD013InstalationECSResolve implements Resolve<ITD013InstalationECS> {
  constructor(private service: TD013InstalationECSService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD013InstalationECS> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD013InstalationECS: HttpResponse<TD013InstalationECS>) => {
          if (tD013InstalationECS.body) {
            return of(tD013InstalationECS.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD013InstalationECS());
  }
}

export const tD013InstalationECSRoute: Routes = [
  {
    path: '',
    component: TD013InstalationECSComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD013InstalationECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD013InstalationECSDetailComponent,
    resolve: {
      tD013InstalationECS: TD013InstalationECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD013InstalationECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD013InstalationECSUpdateComponent,
    resolve: {
      tD013InstalationECS: TD013InstalationECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD013InstalationECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD013InstalationECSUpdateComponent,
    resolve: {
      tD013InstalationECS: TD013InstalationECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD013InstalationECS',
    },
    canActivate: [UserRouteAccessService],
  },
];
