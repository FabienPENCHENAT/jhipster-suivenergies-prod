import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD015ProductionEnergies, TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';
import { TD015ProductionEnergiesService } from './td-015-production-energies.service';
import { TD015ProductionEnergiesComponent } from './td-015-production-energies.component';
import { TD015ProductionEnergiesDetailComponent } from './td-015-production-energies-detail.component';
import { TD015ProductionEnergiesUpdateComponent } from './td-015-production-energies-update.component';

@Injectable({ providedIn: 'root' })
export class TD015ProductionEnergiesResolve implements Resolve<ITD015ProductionEnergies> {
  constructor(private service: TD015ProductionEnergiesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD015ProductionEnergies> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD015ProductionEnergies: HttpResponse<TD015ProductionEnergies>) => {
          if (tD015ProductionEnergies.body) {
            return of(tD015ProductionEnergies.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD015ProductionEnergies());
  }
}

export const tD015ProductionEnergiesRoute: Routes = [
  {
    path: '',
    component: TD015ProductionEnergiesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD015ProductionEnergies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD015ProductionEnergiesDetailComponent,
    resolve: {
      tD015ProductionEnergies: TD015ProductionEnergiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD015ProductionEnergies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD015ProductionEnergiesUpdateComponent,
    resolve: {
      tD015ProductionEnergies: TD015ProductionEnergiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD015ProductionEnergies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD015ProductionEnergiesUpdateComponent,
    resolve: {
      tD015ProductionEnergies: TD015ProductionEnergiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD015ProductionEnergies',
    },
    canActivate: [UserRouteAccessService],
  },
];
