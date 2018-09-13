import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Attribute } from './attribute.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AttributeService {

    private resourceUrl = SERVER_API_URL + 'api/attributes';

    constructor(private http: Http) { }

    create(attribute: Attribute): Observable<Attribute> {
        const copy = this.convert(attribute);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(attribute: Attribute): Observable<Attribute> {
        const copy = this.convert(attribute);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Attribute> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    findAttributeByCategoryAttributeId(category_attribute_id: number): Observable<ResponseWrapper> {
        return this.http.get(`${this.resourceUrl}/categoryAttribute=${category_attribute_id}`)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(attribute: Attribute): Attribute {
        const copy: Attribute = Object.assign({}, attribute);
        return copy;
    }
}
