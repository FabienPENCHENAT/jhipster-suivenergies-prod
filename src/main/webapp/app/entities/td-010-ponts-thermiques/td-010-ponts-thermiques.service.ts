import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

type EntityResponseType = HttpResponse<ITD010PontsThermiques>;
type EntityArrayResponseType = HttpResponse<ITD010PontsThermiques[]>;

@Injectable({ providedIn: 'root' })
export class TD010PontsThermiquesService {
  public resourceUrl = SERVER_API_URL + 'api/td-010-ponts-thermiques';

  constructor(protected http: HttpClient) {}

  create(tD010PontsThermiques: ITD010PontsThermiques): Observable<EntityResponseType> {
    return this.http.post<ITD010PontsThermiques>(this.resourceUrl, tD010PontsThermiques, { observe: 'response' });
  }

  update(tD010PontsThermiques: ITD010PontsThermiques): Observable<EntityResponseType> {
    return this.http.put<ITD010PontsThermiques>(this.resourceUrl, tD010PontsThermiques, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD010PontsThermiques>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD010PontsThermiques[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
