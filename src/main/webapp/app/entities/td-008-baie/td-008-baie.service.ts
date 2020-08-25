import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD008Baie } from 'app/shared/model/td-008-baie.model';

type EntityResponseType = HttpResponse<ITD008Baie>;
type EntityArrayResponseType = HttpResponse<ITD008Baie[]>;

@Injectable({ providedIn: 'root' })
export class TD008BaieService {
  public resourceUrl = SERVER_API_URL + 'api/td-008-baies';

  constructor(protected http: HttpClient) {}

  create(tD008Baie: ITD008Baie): Observable<EntityResponseType> {
    return this.http.post<ITD008Baie>(this.resourceUrl, tD008Baie, { observe: 'response' });
  }

  update(tD008Baie: ITD008Baie): Observable<EntityResponseType> {
    return this.http.put<ITD008Baie>(this.resourceUrl, tD008Baie, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD008Baie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD008Baie[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
