import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

@Component({
  selector: 'jhi-td-010-ponts-thermiques-detail',
  templateUrl: './td-010-ponts-thermiques-detail.component.html',
})
export class TD010PontsThermiquesDetailComponent implements OnInit {
  tD010PontsThermiques: ITD010PontsThermiques | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD010PontsThermiques }) => (this.tD010PontsThermiques = tD010PontsThermiques));
  }

  previousState(): void {
    window.history.back();
  }
}
