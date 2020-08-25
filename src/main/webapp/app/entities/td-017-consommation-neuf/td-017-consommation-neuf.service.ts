import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

type EntityResponseType = HttpResponse<ITD017ConsommationNeuf>;
type EntityArrayResponseType = HttpResponse<ITD017ConsommationNeuf[]>;

@Injectable({ providedIn: 'root' })
export class TD017ConsommationNeufService {
  public resourceUrl = SERVER_API_URL + 'api/td-017-consommation-neufs';

  constructor(protected http: HttpClient) {}

  create(tD017ConsommationNeuf: ITD017ConsommationNeuf): Observable<EntityResponseType> {
    return this.http.post<ITD017ConsommationNeuf>(this.resourceUrl, tD017ConsommationNeuf, { observe: 'response' });
  }

  update(tD017ConsommationNeuf: ITD017ConsommationNeuf): Observable<EntityResponseType> {
    return this.http.put<ITD017ConsommationNeuf>(this.resourceUrl, tD017ConsommationNeuf, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD017ConsommationNeuf>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD017ConsommationNeuf[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
