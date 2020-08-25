import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

type EntityResponseType = HttpResponse<ITD015ProductionEnergies>;
type EntityArrayResponseType = HttpResponse<ITD015ProductionEnergies[]>;

@Injectable({ providedIn: 'root' })
export class TD015ProductionEnergiesService {
  public resourceUrl = SERVER_API_URL + 'api/td-015-production-energies';

  constructor(protected http: HttpClient) {}

  create(tD015ProductionEnergies: ITD015ProductionEnergies): Observable<EntityResponseType> {
    return this.http.post<ITD015ProductionEnergies>(this.resourceUrl, tD015ProductionEnergies, { observe: 'response' });
  }

  update(tD015ProductionEnergies: ITD015ProductionEnergies): Observable<EntityResponseType> {
    return this.http.put<ITD015ProductionEnergies>(this.resourceUrl, tD015ProductionEnergies, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD015ProductionEnergies>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD015ProductionEnergies[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
