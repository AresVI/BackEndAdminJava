import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditTaskStandardObservation } from './audit-task-standard-observation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';
import {createRequestOptionAllElements} from '../../shared/model/request-util';

@Injectable()
export class AuditTaskStandardObservationService {

    private resourceUrl = SERVER_API_URL + 'api/audit-task-standard-observations';

    constructor(private http: Http) { }

    create(auditTaskStandardObservation: AuditTaskStandardObservation): Observable<AuditTaskStandardObservation> {
        const copy = this.convert(auditTaskStandardObservation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditTaskStandardObservation: AuditTaskStandardObservation): Observable<AuditTaskStandardObservation> {
        const copy = this.convert(auditTaskStandardObservation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditTaskStandardObservation> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    queryAll(req?: any): Observable<ResponseWrapper>  {
        const options = createRequestOptionAllElements(req);
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

    private convert(auditTaskStandardObservation: AuditTaskStandardObservation): AuditTaskStandardObservation {
        const copy: AuditTaskStandardObservation = Object.assign({}, auditTaskStandardObservation);
        return copy;
    }
}
