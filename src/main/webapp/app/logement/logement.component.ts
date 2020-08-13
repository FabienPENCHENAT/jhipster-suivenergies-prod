import { Component } from '@angular/core';


import { LogementService } from './logement.service';

@Component({
  selector: 'jhi-logement',
  templateUrl: './logement.component.html',
})
export class LogementComponent {

  constructor(private logementService: LogementService) {}



}
