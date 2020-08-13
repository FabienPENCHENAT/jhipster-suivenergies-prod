import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModeVie, ModeVie } from 'app/shared/model/mode-vie.model';
import { ModeVieService } from './mode-vie.service';
import { ModeVieComponent } from './mode-vie.component';
import { ModeVieDetailComponent } from './mode-vie-detail.component';
import { ModeVieUpdateComponent } from './mode-vie-update.component';

@Injectable({ providedIn: 'root' })
export class ModeVieResolve implements Resolve<IModeVie> {
  constructor(private service: ModeVieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModeVie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modeVie: HttpResponse<ModeVie>) => {
          if (modeVie.body) {
            return of(modeVie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModeVie());
  }
}

export const modeVieRoute: Routes = [
  {
    path: '',
    component: ModeVieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ModeVies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModeVieDetailComponent,
    resolve: {
      modeVie: ModeVieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ModeVies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModeVieUpdateComponent,
    resolve: {
      modeVie: ModeVieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ModeVies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModeVieUpdateComponent,
    resolve: {
      modeVie: ModeVieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ModeVies',
    },
    canActivate: [UserRouteAccessService],
  },
];
