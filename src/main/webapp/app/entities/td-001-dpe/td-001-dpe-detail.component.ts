import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';

@Component({
  selector: 'jhi-td-001-dpe-detail',
  templateUrl: './td-001-dpe-detail.component.html',
})
export class TD001DPEDetailComponent implements OnInit {
  tD001DPE: ITD001DPE | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD001DPE }) => (this.tD001DPE = tD001DPE));
  }

  previousState(): void {
    window.history.back();
  }
}
