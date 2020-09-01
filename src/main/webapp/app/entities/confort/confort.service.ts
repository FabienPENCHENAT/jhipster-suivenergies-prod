import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConfort } from 'app/shared/model/confort.model';

type EntityResponseType = HttpResponse<IConfort>;
type EntityArrayResponseType = HttpResponse<IConfort[]>;

@Injectable({ providedIn: 'root' })
export class ConfortService {
  public resourceUrl = SERVER_API_URL + 'api/conforts';
  public resourceSimpleUrl = SERVER_API_URL + 'api/confort';

  constructor(protected http: HttpClient) {}

  create(confort: IConfort): Observable<EntityResponseType> {
    return this.http.post<IConfort>(this.resourceUrl, confort, { observe: 'response' });
  }

  update(confort: IConfort): Observable<EntityResponseType> {
    return this.http.put<IConfort>(this.resourceUrl, confort, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConfort>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findOneByClient(): Observable<EntityResponseType> {
    return this.http.get<IConfort>(`${this.resourceSimpleUrl}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConfort[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  removeElectromenager(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceSimpleUrl}/${id}`, { observe: 'response' });
  }
}
