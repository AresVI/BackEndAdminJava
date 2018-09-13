import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AuditTask } from './audit-task.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AuditTaskService {

    private resourceUrl = SERVER_API_URL + 'api/audit-tasks';

    constructor(private http: Http) { }

    create(auditTask: AuditTask): Observable<AuditTask> {
        const copy = this.convert(auditTask);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(auditTask: AuditTask): Observable<AuditTask> {
        const copy = this.convert(auditTask);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AuditTask> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    findAuditTasksByContainerId(container_id: number): Observable<ResponseWrapper> {
        return this.http.get(`${this.resourceUrl}/container=${container_id}`)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(auditTask: AuditTask): AuditTask {
        const copy: AuditTask = Object.assign({}, auditTask);
        return copy;
    }
}
