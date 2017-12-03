import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditProcessStandardObservation } from './audit-process-standard-observation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AuditProcessStandardObservationService {

    private resourceUrl = SERVER_API_URL + 'api/audit-process-standard-observations';

    constructor(private http: Http) { }

    create(auditProcessStandardObservation: AuditProcessStandardObservation):
        Observable<AuditProcessStandardObservation> {
        const copy = this.convert(auditProcessStandardObservation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditProcessStandardObservation: AuditProcessStandardObservation):
        Observable<AuditProcessStandardObservation> {
        const copy = this.convert(auditProcessStandardObservation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditProcessStandardObservation> {
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

    private convert(auditProcessStandardObservation: AuditProcessStandardObservation): AuditProcessStandardObservation {
        const copy: AuditProcessStandardObservation = Object.assign({}, auditProcessStandardObservation);
        return copy;
    }
}
