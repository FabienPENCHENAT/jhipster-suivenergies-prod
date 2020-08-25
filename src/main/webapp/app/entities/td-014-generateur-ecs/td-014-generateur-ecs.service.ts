import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

type EntityResponseType = HttpResponse<ITD014GenerateurECS>;
type EntityArrayResponseType = HttpResponse<ITD014GenerateurECS[]>;

@Injectable({ providedIn: 'root' })
export class TD014GenerateurECSService {
  public resourceUrl = SERVER_API_URL + 'api/td-014-generateur-ecs';

  constructor(protected http: HttpClient) {}

  create(tD014GenerateurECS: ITD014GenerateurECS): Observable<EntityResponseType> {
    return this.http.post<ITD014GenerateurECS>(this.resourceUrl, tD014GenerateurECS, { observe: 'response' });
  }

  update(tD014GenerateurECS: ITD014GenerateurECS): Observable<EntityResponseType> {
    return this.http.put<ITD014GenerateurECS>(this.resourceUrl, tD014GenerateurECS, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD014GenerateurECS>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD014GenerateurECS[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
