import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD012GenerateurChauffage, TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';
import { TD012GenerateurChauffageService } from './td-012-generateur-chauffage.service';
import { TD012GenerateurChauffageComponent } from './td-012-generateur-chauffage.component';
import { TD012GenerateurChauffageDetailComponent } from './td-012-generateur-chauffage-detail.component';
import { TD012GenerateurChauffageUpdateComponent } from './td-012-generateur-chauffage-update.component';

@Injectable({ providedIn: 'root' })
export class TD012GenerateurChauffageResolve implements Resolve<ITD012GenerateurChauffage> {
  constructor(private service: TD012GenerateurChauffageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD012GenerateurChauffage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD012GenerateurChauffage: HttpResponse<TD012GenerateurChauffage>) => {
          if (tD012GenerateurChauffage.body) {
            return of(tD012GenerateurChauffage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD012GenerateurChauffage());
  }
}

export const tD012GenerateurChauffageRoute: Routes = [
  {
    path: '',
    component: TD012GenerateurChauffageComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD012GenerateurChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD012GenerateurChauffageDetailComponent,
    resolve: {
      tD012GenerateurChauffage: TD012GenerateurChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD012GenerateurChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD012GenerateurChauffageUpdateComponent,
    resolve: {
      tD012GenerateurChauffage: TD012GenerateurChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD012GenerateurChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD012GenerateurChauffageUpdateComponent,
    resolve: {
      tD012GenerateurChauffage: TD012GenerateurChauffageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD012GenerateurChauffages',
    },
    canActivate: [UserRouteAccessService],
  },
];
