import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElectromenager } from 'app/shared/model/electromenager.model';

@Component({
  selector: 'jhi-electromenager-detail',
  templateUrl: './electromenager-detail.component.html',
})
export class ElectromenagerDetailComponent implements OnInit {
  electromenager: IElectromenager | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ electromenager }) => (this.electromenager = electromenager));
  }

  previousState(): void {
    window.history.back();
  }
}
