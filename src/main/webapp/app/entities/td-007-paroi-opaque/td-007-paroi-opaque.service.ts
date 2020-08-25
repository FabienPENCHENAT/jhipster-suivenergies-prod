import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

type EntityResponseType = HttpResponse<ITD007ParoiOpaque>;
type EntityArrayResponseType = HttpResponse<ITD007ParoiOpaque[]>;

@Injectable({ providedIn: 'root' })
export class TD007ParoiOpaqueService {
  public resourceUrl = SERVER_API_URL + 'api/td-007-paroi-opaques';

  constructor(protected http: HttpClient) {}

  create(tD007ParoiOpaque: ITD007ParoiOpaque): Observable<EntityResponseType> {
    return this.http.post<ITD007ParoiOpaque>(this.resourceUrl, tD007ParoiOpaque, { observe: 'response' });
  }

  update(tD007ParoiOpaque: ITD007ParoiOpaque): Observable<EntityResponseType> {
    return this.http.put<ITD007ParoiOpaque>(this.resourceUrl, tD007ParoiOpaque, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITD007ParoiOpaque>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITD007ParoiOpaque[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
