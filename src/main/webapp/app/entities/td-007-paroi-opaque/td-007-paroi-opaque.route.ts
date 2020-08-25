import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD007ParoiOpaque, TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';
import { TD007ParoiOpaqueService } from './td-007-paroi-opaque.service';
import { TD007ParoiOpaqueComponent } from './td-007-paroi-opaque.component';
import { TD007ParoiOpaqueDetailComponent } from './td-007-paroi-opaque-detail.component';
import { TD007ParoiOpaqueUpdateComponent } from './td-007-paroi-opaque-update.component';

@Injectable({ providedIn: 'root' })
export class TD007ParoiOpaqueResolve implements Resolve<ITD007ParoiOpaque> {
  constructor(private service: TD007ParoiOpaqueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD007ParoiOpaque> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD007ParoiOpaque: HttpResponse<TD007ParoiOpaque>) => {
          if (tD007ParoiOpaque.body) {
            return of(tD007ParoiOpaque.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD007ParoiOpaque());
  }
}

export const tD007ParoiOpaqueRoute: Routes = [
  {
    path: '',
    component: TD007ParoiOpaqueComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD007ParoiOpaques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD007ParoiOpaqueDetailComponent,
    resolve: {
      tD007ParoiOpaque: TD007ParoiOpaqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD007ParoiOpaques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD007ParoiOpaqueUpdateComponent,
    resolve: {
      tD007ParoiOpaque: TD007ParoiOpaqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD007ParoiOpaques',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD007ParoiOpaqueUpdateComponent,
    resolve: {
      tD007ParoiOpaque: TD007ParoiOpaqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD007ParoiOpaques',
    },
    canActivate: [UserRouteAccessService],
  },
];
