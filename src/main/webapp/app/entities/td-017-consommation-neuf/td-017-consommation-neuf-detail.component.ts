import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

@Component({
  selector: 'jhi-td-017-consommation-neuf-detail',
  templateUrl: './td-017-consommation-neuf-detail.component.html',
})
export class TD017ConsommationNeufDetailComponent implements OnInit {
  tD017ConsommationNeuf: ITD017ConsommationNeuf | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD017ConsommationNeuf }) => (this.tD017ConsommationNeuf = tD017ConsommationNeuf));
  }

  previousState(): void {
    window.history.back();
  }
}
