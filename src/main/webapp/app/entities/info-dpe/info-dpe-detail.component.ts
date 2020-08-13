import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IInfoDPE } from 'app/shared/model/info-dpe.model';

@Component({
  selector: 'jhi-info-dpe-detail',
  templateUrl: './info-dpe-detail.component.html',
})
export class InfoDPEDetailComponent implements OnInit {
  infoDPE: IInfoDPE | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infoDPE }) => (this.infoDPE = infoDPE));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
