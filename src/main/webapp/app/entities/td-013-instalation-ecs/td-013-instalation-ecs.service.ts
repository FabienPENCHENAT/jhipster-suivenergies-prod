import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

type EntityResponseType = HttpResponse<ITD013InstalationECS>;
type EntityArrayResponseType = HttpResponse<ITD013InstalationECS[]>;

@Injectable({ providedIn: 'root' })
export class TD013InstalationECSService {
  public resourceUrl = SERVER_API_URL + 'api/td-013-instalation-ecs';

  constructor(protected http: HttpClient) {}

  create(tD013InstalationECS: ITD013InstalationECS): Observable<EntityResponseType> {
    return this.http.post<ITD013InstalationECS>(this.resourceUrl, tD013InstalationECS, { observe: 'response' });
  }

  update(tD013InstalationECS: ITD013InstalationECS): Observable<EntityResponseType> {
    return this.http.put<ITD013InstalationECS>(this.resourceUrl, tD013InstalationECS, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD013InstalationECS>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD013InstalationECS[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
