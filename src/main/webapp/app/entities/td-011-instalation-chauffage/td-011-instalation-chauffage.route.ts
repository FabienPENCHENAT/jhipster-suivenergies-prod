import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD011InstalationChauffage, TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';
import { TD011InstalationChauffageService } from './td-011-instalation-chauffage.service';
import { TD011InstalationChauffageComponent } from './td-011-instalation-chauffage.component';
import { TD011InstalationChauffageDetailComponent } from './td-011-instalation-chauffage-detail.component';
import { TD011InstalationChauffageUpdateComponent } from './td-011-instalation-chauffage-update.component';

@Injectable({ providedIn: 'root' })
export class TD011InstalationChauffageResolve implements Resolve<ITD011InstalationChauffage> {
  constructor(private service: TD011InstalationChauffageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD011InstalationChauffage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD011InstalationChauffage: HttpResponse<TD011InstalationChauffage>) => {
          if (tD011InstalationChauffage.body) {
            return of(tD011InstalationChauffage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD011InstalationChauffage());
  }
}

export const tD011InstalationChauffageRoute: Routes = [
  {
    path: '',
    component: TD011InstalationChauffageComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD011InstalationChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD011InstalationChauffageDetailComponent,
    resolve: {
      tD011InstalationChauffage: TD011InstalationChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD011InstalationChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD011InstalationChauffageUpdateComponent,
    resolve: {
      tD011InstalationChauffage: TD011InstalationChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD011InstalationChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD011InstalationChauffageUpdateComponent,
    resolve: {
      tD011InstalationChauffage: TD011InstalationChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD011InstalationChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
];
