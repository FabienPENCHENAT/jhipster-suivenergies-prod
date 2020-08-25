import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD001DPE } from 'app/shared/model/td-001-dpe.model';

type EntityResponseType = HttpResponse<ITD001DPE>;
type EntityArrayResponseType = HttpResponse<ITD001DPE[]>;

@Injectable({ providedIn: 'root' })
export class TD001DPEService {
  public resourceUrl = SERVER_API_URL + 'api/td-001-dpes';

  constructor(protected http: HttpClient) {}

  create(tD001DPE: ITD001DPE): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tD001DPE);
    return this.http
      .post<ITD001DPE>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tD001DPE: ITD001DPE): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tD001DPE);
    return this.http
      .put<ITD001DPE>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITD001DPE>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITD001DPE[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tD001DPE: ITD001DPE): ITD001DPE {
    const copy: ITD001DPE = Object.assign({}, tD001DPE, {
      dateReceptionDpe:
        tD001DPE.dateReceptionDpe && tD001DPE.dateReceptionDpe.isValid() ? tD001DPE.dateReceptionDpe.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReceptionDpe = res.body.dateReceptionDpe ? moment(res.body.dateReceptionDpe) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tD001DPE: ITD001DPE) => {
        tD001DPE.dateReceptionDpe = tD001DPE.dateReceptionDpe ? moment(tD001DPE.dateReceptionDpe) : undefined;
      });
    }
    return res;
  }
}
