import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

@Component({
  selector: 'jhi-td-006-batiment-detail',
  templateUrl: './td-006-batiment-detail.component.html',
})
export class TD006BatimentDetailComponent implements OnInit {
  tD006Batiment: ITD006Batiment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD006Batiment }) => (this.tD006Batiment = tD006Batiment));
  }

  previousState(): void {
    window.history.back();
  }
}
