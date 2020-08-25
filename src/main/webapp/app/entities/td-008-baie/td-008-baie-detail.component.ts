import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD008Baie } from 'app/shared/model/td-008-baie.model';

@Component({
  selector: 'jhi-td-008-baie-detail',
  templateUrl: './td-008-baie-detail.component.html',
})
export class TD008BaieDetailComponent implements OnInit {
  tD008Baie: ITD008Baie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD008Baie }) => (this.tD008Baie = tD008Baie));
  }

  previousState(): void {
    window.history.back();
  }
}
