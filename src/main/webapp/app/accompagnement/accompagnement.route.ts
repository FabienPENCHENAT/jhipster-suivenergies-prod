import { Routes } from '@angular/router';


import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { AccompagnementComponent } from './accompagnement.component';

export const accompagnementRoute: Routes = [
	{
		path: 'accompagnement',
		component: AccompagnementComponent,
		data: {
			authorities: [Authority.USER]
		},
		canActivate: [UserRouteAccessService],
	},
];
