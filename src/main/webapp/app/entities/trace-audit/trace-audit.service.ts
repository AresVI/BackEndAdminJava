import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { TraceAudit } from './trace-audit.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TraceAuditService {

    private resourceUrl = SERVER_API_URL + 'api/trace-audits';
    private resourceListAllUrl = SERVER_API_URL + 'api/traceability-audits/';

    constructor(private http: Http) { }

    create(traceAudit: TraceAudit): Observable<TraceAudit> {
        const copy = this.convert(traceAudit);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(traceAudit: TraceAudit): Observable<TraceAudit> {
        const copy = this.convert(traceAudit);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<TraceAudit> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(traceabilityAuditId: number): Observable<ResponseWrapper> {
        const options = createRequestOption(null);
        return this.http.get(this.resourceListAllUrl + traceabilityAuditId + '/trace-audits', options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(traceAudit: TraceAudit): TraceAudit {
        const copy: TraceAudit = Object.assign({}, traceAudit);
        return copy;
    }
}
