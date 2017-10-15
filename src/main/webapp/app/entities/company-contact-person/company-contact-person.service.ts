import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { CompanyContactPerson } from './company-contact-person.model';
import { ResponseWrapper, createRequestOption } from '../../shared';
import {createRequestOptionAllElements} from "../../shared/model/request-util";

@Injectable()
export class CompanyContactPersonService {

    constructor(private http: Http) { }

    getResourceUrl( company_id: number ){

        return SERVER_API_URL + 'api/company/'+ company_id +'/company-contact-people';

    }

    create(company_id: number, company_contact_person: CompanyContactPerson): Observable<CompanyContactPerson> {
        const copy = this.convert(company_contact_person);
        return this.http.post(this.getResourceUrl( company_id ), copy).map((res: Response) => {
            return res.json();
        });
    }

    update(company_id: number, company_contact_person: CompanyContactPerson): Observable<CompanyContactPerson> {
        const copy = this.convert(company_contact_person);
        return this.http.put(this.getResourceUrl( company_id ), copy).map((res: Response) => {
            return res.json();
        });
    }

    find(company_id: number, id: number): Observable<CompanyContactPerson> {
        return this.http.get(`${ this.getResourceUrl(company_id) }/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(company_id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.getResourceUrl(company_id), options)
            .map((res: Response) => this.convertResponse(res));
    }

    queryAll(company_id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOptionAllElements(req);
        return this.http.get(this.getResourceUrl(company_id), options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(company_id: number, id: number): Observable<Response> {
        return this.http.delete(`${this.getResourceUrl(company_id)}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(company_contact_person: CompanyContactPerson): CompanyContactPerson {
        const copy: CompanyContactPerson = Object.assign({}, company_contact_person);
        return copy;
    }
}
