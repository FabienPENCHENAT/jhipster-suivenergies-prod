import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITD008Baie, TD008Baie } from 'app/shared/model/td-008-baie.model';
import { TD008BaieService } from './td-008-baie.service';
import { TD008BaieComponent } from './td-008-baie.component';
import { TD008BaieDetailComponent } from './td-008-baie-detail.component';
import { TD008BaieUpdateComponent } from './td-008-baie-update.component';

@Injectable({ providedIn: 'root' })
export class TD008BaieResolve implements Resolve<ITD008Baie> {
  constructor(private service: TD008BaieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITD008Baie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tD008Baie: HttpResponse<TD008Baie>) => {
          if (tD008Baie.body) {
            return of(tD008Baie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TD008Baie());
  }
}

export const tD008BaieRoute: Routes = [
  {
    path: '',
    component: TD008BaieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD008Baies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TD008BaieDetailComponent,
    resolve: {
      tD008Baie: TD008BaieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD008Baies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TD008BaieUpdateComponent,
    resolve: {
      tD008Baie: TD008BaieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD008Baies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TD008BaieUpdateComponent,
    resolve: {
      tD008Baie: TD008BaieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TD008Baies',
    },
    canActivate: [UserRouteAccessService],
  },
];
