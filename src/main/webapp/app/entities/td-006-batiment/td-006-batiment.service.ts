import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD006Batiment } from 'app/shared/model/td-006-batiment.model';

type EntityResponseType = HttpResponse<ITD006Batiment>;
type EntityArrayResponseType = HttpResponse<ITD006Batiment[]>;

@Injectable({ providedIn: 'root' })
export class TD006BatimentService {
  public resourceUrl = SERVER_API_URL + 'api/td-006-batiments';

  constructor(protected http: HttpClient) {}

  create(tD006Batiment: ITD006Batiment): Observable<EntityResponseType> {
    return this.http.post<ITD006Batiment>(this.resourceUrl, tD006Batiment, { observe: 'response' });
  }

  update(tD006Batiment: ITD006Batiment): Observable<EntityResponseType> {
    return this.http.put<ITD006Batiment>(this.resourceUrl, tD006Batiment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD006Batiment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD006Batiment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
