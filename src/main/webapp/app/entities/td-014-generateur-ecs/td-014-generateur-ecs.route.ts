import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD014GenerateurECS, TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';
import { TD014GenerateurECSService } from './td-014-generateur-ecs.service';
import { TD014GenerateurECSComponent } from './td-014-generateur-ecs.component';
import { TD014GenerateurECSDetailComponent } from './td-014-generateur-ecs-detail.component';
import { TD014GenerateurECSUpdateComponent } from './td-014-generateur-ecs-update.component';

@Injectable({ providedIn: 'root' })
export class TD014GenerateurECSResolve implements Resolve<ITD014GenerateurECS> {
  constructor(private service: TD014GenerateurECSService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD014GenerateurECS> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD014GenerateurECS: HttpResponse<TD014GenerateurECS>) => {
          if (tD014GenerateurECS.body) {
            return of(tD014GenerateurECS.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD014GenerateurECS());
  }
}

export const tD014GenerateurECSRoute: Routes = [
  {
    path: '',
    component: TD014GenerateurECSComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD014GenerateurECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD014GenerateurECSDetailComponent,
    resolve: {
      tD014GenerateurECS: TD014GenerateurECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD014GenerateurECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD014GenerateurECSUpdateComponent,
    resolve: {
      tD014GenerateurECS: TD014GenerateurECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD014GenerateurECS',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD014GenerateurECSUpdateComponent,
    resolve: {
      tD014GenerateurECS: TD014GenerateurECSResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD014GenerateurECS',
    },
    canActivate: [UserRouteAccessService],
  },
];
