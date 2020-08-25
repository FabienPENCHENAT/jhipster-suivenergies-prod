import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD002Consommations } from 'app/shared/model/td-002-consommations.model';

type EntityResponseType = HttpResponse<ITD002Consommations>;
type EntityArrayResponseType = HttpResponse<ITD002Consommations[]>;

@Injectable({ providedIn: 'root' })
export class TD002ConsommationsService {
  public resourceUrl = SERVER_API_URL + 'api/td-002-consommations';

  constructor(protected http: HttpClient) {}

  create(tD002Consommations: ITD002Consommations): Observable<EntityResponseType> {
    return this.http.post<ITD002Consommations>(this.resourceUrl, tD002Consommations, { observe: 'response' });
  }

  update(tD002Consommations: ITD002Consommations): Observable<EntityResponseType> {
    return this.http.put<ITD002Consommations>(this.resourceUrl, tD002Consommations, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD002Consommations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD002Consommations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
