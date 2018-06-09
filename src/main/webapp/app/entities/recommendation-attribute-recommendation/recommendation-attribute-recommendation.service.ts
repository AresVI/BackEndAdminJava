import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { RecommendationAttributeRecommendation } from './recommendation-attribute-recommendation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RecommendationAttributeRecommendationService {

    private resourceUrl = SERVER_API_URL + 'api/recommendation-attribute-recommendations';

    constructor(private http: Http) { }

    create(recommendationAttributeRecommendation: RecommendationAttributeRecommendation):
        Observable<RecommendationAttributeRecommendation> {
        const copy = this.convert(recommendationAttributeRecommendation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(recommendationAttributeRecommendation: RecommendationAttributeRecommendation):
        Observable<RecommendationAttributeRecommendation> {
        const copy = this.convert(recommendationAttributeRecommendation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<RecommendationAttributeRecommendation> {
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

    private convert(recommendationAttributeRecommendation: RecommendationAttributeRecommendation): RecommendationAttributeRecommendation {
        const copy: RecommendationAttributeRecommendation = Object.assign({}, recommendationAttributeRecommendation);
        return copy;
    }
}
