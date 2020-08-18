import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInfoDPE } from 'app/shared/model/info-dpe.model';

type EntityResponseType = HttpResponse<IInfoDPE>;
type EntityArrayResponseType = HttpResponse<IInfoDPE[]>;

@Injectable({ providedIn: 'root' })
export class InfoDPEService {
  public resourceUrl = SERVER_API_URL + 'api/info-dpes';

  constructor(protected http: HttpClient) {}

  create(infoDPE: IInfoDPE): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(infoDPE);
    return this.http
      .post<IInfoDPE>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(infoDPE: IInfoDPE): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(infoDPE);
    return this.http
      .put<IInfoDPE>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInfoDPE>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findLast(): Observable<EntityResponseType> {
    return this.http
      .get<IInfoDPE>(`${this.resourceUrl}/last`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  downloadInfoDPE(num: String): Observable<EntityResponseType> {
    return this.http
      .post<IInfoDPE>(this.resourceUrl, num, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInfoDPE[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(infoDPE: IInfoDPE): IInfoDPE {
    const copy: IInfoDPE = Object.assign({}, infoDPE, {
      dateDPE: infoDPE.dateDPE && infoDPE.dateDPE.isValid() ? infoDPE.dateDPE.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDPE = res.body.dateDPE ? moment(res.body.dateDPE) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((infoDPE: IInfoDPE) => {
        infoDPE.dateDPE = infoDPE.dateDPE ? moment(infoDPE.dateDPE) : undefined;
      });
    }
    return res;
  }
}
