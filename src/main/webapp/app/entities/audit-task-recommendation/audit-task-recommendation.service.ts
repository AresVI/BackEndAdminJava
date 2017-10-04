import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AuditTaskRecommendationService {

    private resourceUrl = SERVER_API_URL + 'api/audit-task-recommendations';

    constructor(private http: Http) { }

    create(auditTaskRecommendation: AuditTaskRecommendation): Observable<AuditTaskRecommendation> {
        const copy = this.convert(auditTaskRecommendation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditTaskRecommendation: AuditTaskRecommendation): Observable<AuditTaskRecommendation> {
        const copy = this.convert(auditTaskRecommendation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditTaskRecommendation> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(auditTaskRecommendation: AuditTaskRecommendation): AuditTaskRecommendation {
        const copy: AuditTaskRecommendation = Object.assign({}, auditTaskRecommendation);
        return copy;
    }
}
