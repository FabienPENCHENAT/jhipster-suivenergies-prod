import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

@Component({
  selector: 'jhi-td-007-paroi-opaque-detail',
  templateUrl: './td-007-paroi-opaque-detail.component.html',
})
export class TD007ParoiOpaqueDetailComponent implements OnInit {
  tD007ParoiOpaque: ITD007ParoiOpaque | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD007ParoiOpaque }) => (this.tD007ParoiOpaque = tD007ParoiOpaque));
  }

  previousState(): void {
    window.history.back();
  }
}
