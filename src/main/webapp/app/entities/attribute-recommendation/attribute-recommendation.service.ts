import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AttributeRecommendation } from './attribute-recommendation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AttributeRecommendationService {

    private resourceUrl = SERVER_API_URL + 'api/attribute-recommendations';

    constructor(private http: Http) { }

    create(attributeRecommendation: AttributeRecommendation): Observable<AttributeRecommendation> {
        const copy = this.convert(attributeRecommendation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(attributeRecommendation: AttributeRecommendation): Observable<AttributeRecommendation> {
        const copy = this.convert(attributeRecommendation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AttributeRecommendation> {
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

    private convert(attributeRecommendation: AttributeRecommendation): AttributeRecommendation {
        const copy: AttributeRecommendation = Object.assign({}, attributeRecommendation);
        return copy;
    }
}
