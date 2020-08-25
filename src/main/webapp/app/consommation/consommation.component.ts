import { Component, OnInit } from '@angular/core';

import { ConsommationService } from './consommation.service';

@Component({
  selector: 'jhi-consommation',
  templateUrl: './consommation.component.html',
})

export class ConsommationComponent implements OnInit {
	success = false;

  constructor(
		  private consommationService: ConsommationService
  ) {}
  
  ngOnInit(): void {
	  this.success = true;
	  }

}
