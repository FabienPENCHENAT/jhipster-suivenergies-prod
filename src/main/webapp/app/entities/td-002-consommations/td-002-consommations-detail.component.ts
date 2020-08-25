import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';

@Component({
  selector: 'jhi-td-002-consommations-detail',
  templateUrl: './td-002-consommations-detail.component.html',
})
export class TD002ConsommationsDetailComponent implements OnInit {
  tD002Consommations: ITD002Consommations | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD002Consommations }) => (this.tD002Consommations = tD002Consommations));
  }

  previousState(): void {
    window.history.back();
  }
}
