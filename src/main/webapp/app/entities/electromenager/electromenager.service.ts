import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElectromenager } from 'app/shared/model/electromenager.model';

type EntityResponseType = HttpResponse<IElectromenager>;
type EntityArrayResponseType = HttpResponse<IElectromenager[]>;

@Injectable({ providedIn: 'root' })
export class ElectromenagerService {
  public resourceUrl = SERVER_API_URL + 'api/electromenagers';
  public resourceSimpleUrl = SERVER_API_URL + 'api/electromenager';

  constructor(protected http: HttpClient) {}

  create(electromenager: IElectromenager): Observable<EntityResponseType> {
    return this.http.post<IElectromenager>(this.resourceUrl, electromenager, { observe: 'response' });
  }

  update(electromenager: IElectromenager): Observable<EntityResponseType> {
    return this.http.put<IElectromenager>(this.resourceUrl, electromenager, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElectromenager>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElectromenager[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
