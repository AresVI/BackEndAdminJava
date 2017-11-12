import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {SearchTraceabilityAuditComponent} from './traceability_audit.component';
import {JhiPaginationUtil} from 'ng-jhipster';
import {Injectable} from '@angular/core';
import {UserRouteAccessService} from '../../shared/auth/user-route-access-service';

@Injectable()
export class SearchTraceabilityResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,desc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
        };
    }
}

export const searchTraceabilityAuditRoute: Routes = [
    {
        path: 'search/traceability_audit',
        component: SearchTraceabilityAuditComponent,
        resolve: {
            'pagingParams': SearchTraceabilityResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR_EXTERNAL', 'ROLE_AUDITOR_INTERNAL'],
            pageTitle: 'aresViApp.flow-audit.finished.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
