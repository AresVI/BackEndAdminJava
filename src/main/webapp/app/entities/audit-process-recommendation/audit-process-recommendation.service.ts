import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditProcessRecommendation } from './audit-process-recommendation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AuditProcessRecommendationService {

    private resourceUrl = SERVER_API_URL + 'api/audit-process-recommendations';

    private resource_recommendation_next_category = SERVER_API_URL + 'api/recommendation-next-category';

    constructor(private http: Http) { }

    create(auditProcessRecommendation: AuditProcessRecommendation): Observable<AuditProcessRecommendation> {
        const copy = this.convert(auditProcessRecommendation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditProcessRecommendation: AuditProcessRecommendation): Observable<AuditProcessRecommendation> {
        const copy = this.convert(auditProcessRecommendation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditProcessRecommendation> {
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

    getRecommendationNextCategory(company_id: number): Observable<AuditProcessRecommendation[]> {
        return this.http.get(`${this.resource_recommendation_next_category}/${company_id}`).map((res: Response) => {
            return res.json();
        });
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(auditProcessRecommendation: AuditProcessRecommendation): AuditProcessRecommendation {
        const copy: AuditProcessRecommendation = Object.assign({}, auditProcessRecommendation);
        return copy;
    }
}
