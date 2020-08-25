import { Routes } from '@angular/router';


import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConsommationComponent } from './consommation.component';

export const consommationRoute: Routes = [
	{
		path: 'consommation',
		component: ConsommationComponent,
		data: {
			authorities: [Authority.USER]
		},
		canActivate: [UserRouteAccessService],
	},
];
