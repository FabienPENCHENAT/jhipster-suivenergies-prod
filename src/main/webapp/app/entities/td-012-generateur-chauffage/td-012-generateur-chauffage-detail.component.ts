import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

@Component({
  selector: 'jhi-td-012-generateur-chauffage-detail',
  templateUrl: './td-012-generateur-chauffage-detail.component.html',
})
export class TD012GenerateurChauffageDetailComponent implements OnInit {
  tD012GenerateurChauffage: ITD012GenerateurChauffage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD012GenerateurChauffage }) => (this.tD012GenerateurChauffage = tD012GenerateurChauffage));
  }

  previousState(): void {
    window.history.back();
  }
}
