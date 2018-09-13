import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { CategoryAttribute } from './category-attribute.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CategoryAttributeService {

    private resourceUrl = SERVER_API_URL + 'api/category-attributes';

    constructor(private http: Http) { }

    create(categoryAttribute: CategoryAttribute): Observable<CategoryAttribute> {
        const copy = this.convert(categoryAttribute);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(categoryAttribute: CategoryAttribute): Observable<CategoryAttribute> {
        const copy = this.convert(categoryAttribute);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<CategoryAttribute> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    findCategoryAttributesByAuditTaskId(audit_task_id: number): Observable<ResponseWrapper> {
        return this.http.get(`${this.resourceUrl}/auditTask=${audit_task_id}`)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(categoryAttribute: CategoryAttribute): CategoryAttribute {
        const copy: CategoryAttribute = Object.assign({}, categoryAttribute);
        return copy;
    }
}
