import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

@Component({
  selector: 'jhi-td-014-generateur-ecs-detail',
  templateUrl: './td-014-generateur-ecs-detail.component.html',
})
export class TD014GenerateurECSDetailComponent implements OnInit {
  tD014GenerateurECS: ITD014GenerateurECS | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tD014GenerateurECS }) => (this.tD014GenerateurECS = tD014GenerateurECS));
  }

  previousState(): void {
    window.history.back();
  }
}
