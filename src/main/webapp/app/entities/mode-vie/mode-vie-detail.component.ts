import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModeVie } from 'app/shared/model/mode-vie.model';

@Component({
  selector: 'jhi-mode-vie-detail',
  templateUrl: './mode-vie-detail.component.html',
})
export class ModeVieDetailComponent implements OnInit {
  modeVie: IModeVie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeVie }) => (this.modeVie = modeVie));
  }

  previousState(): void {
    window.history.back();
  }
}
