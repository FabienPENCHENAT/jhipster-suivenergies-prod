import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

type EntityResponseType = HttpResponse<ITD012GenerateurChauffage>;
type EntityArrayResponseType = HttpResponse<ITD012GenerateurChauffage[]>;

@Injectable({ providedIn: 'root' })
export class TD012GenerateurChauffageService {
  public resourceUrl = SERVER_API_URL + 'api/td-012-generateur-chauffages';

  constructor(protected http: HttpClient) {}

  create(tD012GenerateurChauffage: ITD012GenerateurChauffage): Observable<EntityResponseType> {
    return this.http.post<ITD012GenerateurChauffage>(this.resourceUrl, tD012GenerateurChauffage, { observe: 'response' });
  }

  update(tD012GenerateurChauffage: ITD012GenerateurChauffage): Observable<EntityResponseType> {
    return this.http.put<ITD012GenerateurChauffage>(this.resourceUrl, tD012GenerateurChauffage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD012GenerateurChauffage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD012GenerateurChauffage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
