import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AuditAttributeAnalysisService {

    private resourceUrl = SERVER_API_URL + 'api/audit-attribute-analyses';

    constructor(private http: Http) { }

    create(auditAttributeAnalysis: AuditAttributeAnalysis): Observable<AuditAttributeAnalysis> {
        const copy = this.convert(auditAttributeAnalysis);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditAttributeAnalysis: AuditAttributeAnalysis): Observable<AuditAttributeAnalysis> {
        const copy = this.convert(auditAttributeAnalysis);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditAttributeAnalysis> {
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

    private convert(auditAttributeAnalysis: AuditAttributeAnalysis): AuditAttributeAnalysis {
        const copy: AuditAttributeAnalysis = Object.assign({}, auditAttributeAnalysis);
        return copy;
    }
}
