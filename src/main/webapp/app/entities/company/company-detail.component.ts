import {Component, OnInit, OnDestroy, Pipe} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import { Company } from './company.model';
import { CompanyService } from './company.service';
import {TraceabilityAudit} from '../traceability-audit/traceability-audit.model';
import {TraceabilityAuditService} from '../traceability-audit/traceability-audit.service';
import {Principal} from '../../shared/auth/principal.service';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';
import {ITEMS_PER_PAGE} from '../../shared/constants/pagination.constants';

import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Pipe({ name: 'safe' })
@Component({
    selector: 'jhi-company-detail',
    templateUrl: './company-detail.component.html'
})
export class CompanyDetailComponent implements OnInit, OnDestroy {

    currentAccount: Account;
    company: Company;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    traceabilityAudits: TraceabilityAudit[];

    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    lat: number;
    lng: number;
    mapZoom: number;

    title = 'app';
    zoom = 16;

    urlMap: SafeResourceUrl;
    linkGoogleMaps: SafeResourceUrl;

    constructor(
        private traceabilityAuditService: TraceabilityAuditService,
        private companyService: CompanyService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private sanitizer: DomSanitizer
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCompanies();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTraceabilityAudits();
        this.lat = -33.066796;
        this.lng = -68.5094036;
        this.mapZoom = 16;

        const url = 'https://maps.googleapis.com/maps/api/staticmap?key=AIzaSyB1cBm4iFiova0nbWSXHMKg0473TzCqcEI' +
            '&center=' + this.lat + ',' + this.lng + '&zoom=' +  this.zoom + '&size=400x400' +
            '&markers=color:red|' + this.lat + ',' + this.lng ;

        this.urlMap =  this.sanitizer.bypassSecurityTrustResourceUrl(url);

        const googleMapsUrl = 'https://www.google.com.ar/maps/dir//' + this.lat + ',' + this.lng + '/@' + this.lat + ',' + this.lng + ',14z';

        this.linkGoogleMaps =  this.sanitizer.bypassSecurityTrustResourceUrl(googleMapsUrl);

    }

    load(id) {
        this.companyService.find(id).subscribe((company) => {
            this.company = company;
            this.loadAll();
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompanies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'companyListModification',
            (response) => this.load(this.company.id)
        );
    }

    loadAll() {
        this.traceabilityAuditService.queryFinished(
            {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()},
            {
                category: null,
                company_id: this.company.id
            }).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/company', this.company.id], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/company', this.company.id, {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    trackId(index: number, item: TraceabilityAudit) {
        return item.id;
    }
    registerChangeInTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe('traceabilityAuditListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.traceabilityAudits = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
