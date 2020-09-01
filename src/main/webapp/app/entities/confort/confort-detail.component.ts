import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConfort } from 'app/shared/model/confort.model';

@Component({
  selector: 'jhi-confort-detail',
  templateUrl: './confort-detail.component.html',
})
export class ConfortDetailComponent implements OnInit {
  confort: IConfort | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ confort }) => (this.confort = confort));
  }

  previousState(): void {
    window.history.back();
  }
}
