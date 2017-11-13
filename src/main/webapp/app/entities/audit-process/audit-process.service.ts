import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditProcess } from './audit-process.model';
import { ResponseWrapper, createRequestOption } from '../../shared';
import {createRequestOptionAllElements} from '../../shared/model/request-util';

@Injectable()
export class AuditProcessService {

    private resourceUrl = SERVER_API_URL + 'api/audit-processes';

    constructor(private http: Http) { }

    create(auditProcess: AuditProcess): Observable<AuditProcess> {
        const copy = this.convert(auditProcess);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditProcess: AuditProcess): Observable<AuditProcess> {
        const copy = this.convert(auditProcess);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditProcess> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    findComplete(id: number): Observable<AuditProcess> {
        return this.http.get(`${this.resourceUrl}/${id}/complete`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    queryAll(req?: any): Observable<ResponseWrapper> {
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

    private convert(auditProcess: AuditProcess): AuditProcess {
        const copy: AuditProcess = Object.assign({}, auditProcess);
        return copy;
    }
}
