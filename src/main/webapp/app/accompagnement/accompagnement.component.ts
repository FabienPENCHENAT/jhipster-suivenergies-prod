import { Component, OnInit } from '@angular/core';

import { AccompagnementService } from './accompagnement.service';

@Component({
  selector: 'jhi-accompagnement',
  templateUrl: './accompagnement.component.html',
})

export class AccompagnementComponent implements OnInit {
	success = false;

  constructor(
		  private accompagnementService: AccompagnementService
  ) {}
  
  ngOnInit(): void {
	  this.success = true;
	  }

}
