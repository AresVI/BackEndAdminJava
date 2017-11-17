import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { TraceabilityAudit } from './traceability-audit.model';
import { ResponseWrapper, createRequestOption } from '../../shared';
import {createRequestOptionWithQuery} from '../../shared/model/request-util';

@Injectable()
export class TraceabilityAuditService {

    private resourceUrl = SERVER_API_URL + 'api/traceability-audits';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(traceabilityAudit: TraceabilityAudit): Observable<TraceabilityAudit> {
        const copy = this.convert(traceabilityAudit);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(traceabilityAudit: TraceabilityAudit): Observable<TraceabilityAudit> {
        const copy = this.convert(traceabilityAudit);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<TraceabilityAudit> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any, query_object?: object): Observable<ResponseWrapper> {
        const options = createRequestOptionWithQuery(req, query_object);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    queryFinished(req?: any, query_object?: object): Observable<ResponseWrapper> {
        const options = createRequestOptionWithQuery(req, query_object);
        return this.http.get(`${this.resourceUrl}/finished`, options)
            .map((res: Response) => this.convertResponse(res));
    }

    start(id: number): Observable<Response> {
        return this.http.post(`${this.resourceUrl}/${id}/start`, {});
    }

    finish(id: number): Observable<Response> {
        return this.http.post(`${this.resourceUrl}/${id}/finish`, {});
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.creationDate = this.dateUtils
            .convertDateTimeFromServer(entity.creationDate);
    }

    private convert(traceabilityAudit: TraceabilityAudit): TraceabilityAudit {
        const copy: TraceabilityAudit = Object.assign({}, traceabilityAudit);

        copy.creationDate = this.dateUtils.toDate(traceabilityAudit.creationDate);
        return copy;
    }
}
