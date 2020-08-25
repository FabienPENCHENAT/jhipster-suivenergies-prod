import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

@Component({
  selector: 'jhi-td-013-instalation-ecs-detail',
  templateUrl: './td-013-instalation-ecs-detail.component.html',
})
export class TD013InstalationECSDetailComponent implements OnInit {
  tD013InstalationECS: ITD013InstalationECS | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD013InstalationECS }) => (this.tD013InstalationECS = tD013InstalationECS));
  }

  previousState(): void {
    window.history.back();
  }
}
