import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

@Component({
  selector: 'jhi-td-011-instalation-chauffage-detail',
  templateUrl: './td-011-instalation-chauffage-detail.component.html',
})
export class TD011InstalationChauffageDetailComponent implements OnInit {
  tD011InstalationChauffage: ITD011InstalationChauffage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD011InstalationChauffage }) => (this.tD011InstalationChauffage = tD011InstalationChauffage));
  }

  previousState(): void {
    window.history.back();
  }
}
