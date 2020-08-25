import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

@Component({
  selector: 'jhi-td-015-production-energies-detail',
  templateUrl: './td-015-production-energies-detail.component.html',
})
export class TD015ProductionEnergiesDetailComponent implements OnInit {
  tD015ProductionEnergies: ITD015ProductionEnergies | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD015ProductionEnergies }) => (this.tD015ProductionEnergies = tD015ProductionEnergies));
  }

  previousState(): void {
    window.history.back();
  }
}
