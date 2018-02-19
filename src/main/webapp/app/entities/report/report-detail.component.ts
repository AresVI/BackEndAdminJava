import {Component, OnInit, OnDestroy, Sanitizer} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Report } from './report.model';
import { ReportService } from './report.service';

import { DomSanitizer } from '@angular/platform-browser';

@Component({
    selector: 'jhi-report-detail',
    templateUrl: './report-detail.component.html'
})
export class ReportDetailComponent implements OnInit, OnDestroy {

    report: Report;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    private sanitizer: Sanitizer;

    constructor(
        private eventManager: JhiEventManager,
        private reportService: ReportService,
        private route: ActivatedRoute
    ) {
        this.sanitizer = DomSanitizer;
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInReports();
    }

    load(id) {
        this.reportService.find(id).subscribe((report) => {
            this.report = report;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInReports() {
        this.eventSubscriber = this.eventManager.subscribe(
            'reportListModification',
            (response) => this.load(this.report.id)
        );
    }

    iframeURL() {
        return this.sanitizer.bypassSecurityTrustUrl(this.report.iframe);
    }

}
