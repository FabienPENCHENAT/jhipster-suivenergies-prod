import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModeVie } from 'app/shared/model/mode-vie.model';

type EntityResponseType = HttpResponse<IModeVie>;
type EntityArrayResponseType = HttpResponse<IModeVie[]>;

@Injectable({ providedIn: 'root' })
export class ModeVieService {
  public resourceUrl = SERVER_API_URL + 'api/mode-vies';

  constructor(protected http: HttpClient) {}

  create(modeVie: IModeVie): Observable<EntityResponseType> {
    return this.http.post<IModeVie>(this.resourceUrl, modeVie, { observe: 'response' });
  }

  update(modeVie: IModeVie): Observable<EntityResponseType> {
    return this.http.put<IModeVie>(this.resourceUrl, modeVie, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModeVie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModeVie[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
