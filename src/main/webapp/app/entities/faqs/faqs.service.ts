import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Faqs } from './faqs.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FaqsService {

    private resourceUrl = SERVER_API_URL + 'api/faqs';

    constructor(private http: Http) { }

    create(faqs: Faqs): Observable<Faqs> {
        const copy = this.convert(faqs);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(faqs: Faqs): Observable<Faqs> {
        const copy = this.convert(faqs);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Faqs> {
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

    private convert(faqs: Faqs): Faqs {
        const copy: Faqs = Object.assign({}, faqs);
        return copy;
    }
}
