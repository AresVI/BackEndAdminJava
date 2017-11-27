package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.config.ApplicationProperties;
import com.labausegtic.aresvi.domain.AuditProcessRecommendation;

import org.bonitasoft.engine.api.*;
import org.bonitasoft.engine.bpm.process.*;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;


import java.util.HashMap;
import java.util.Map;


public class BonitaBPMService {

    private LoginAPI loginAPI;
    private APISession apiSession;

    private String username;
    private String password;

    private String bonitaHost;
    private String bonitaPort;

    private static final int MAX_PROCESS_COUNT = 3;

    public BonitaBPMService(ApplicationProperties applicationProperties) {
        this.bonitaHost = applicationProperties.getBonita().getHost();
        this.bonitaPort = applicationProperties.getBonita().getPort();
        this.username = applicationProperties.getBonita().getUsername();
        this.password = applicationProperties.getBonita().getPassword();
    }

    public void startBPMProcess(AuditProcessRecommendation auditProcessRecommendation){
        try {
            loginBonita();
            //List the deployed processes
            ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(this.apiSession);
            SearchOptions searchOptions = new SearchOptionsBuilder(0, BonitaBPMService.MAX_PROCESS_COUNT).sort(ProcessDeploymentInfoSearchDescriptor.DEPLOYMENT_DATE, Order.DESC).done();
            SearchResult<ProcessDeploymentInfo> deploymentInfoResults = processAPI.searchProcessDeploymentInfos(searchOptions);
            for (int i = 0; i <deploymentInfoResults.getResult().size(); i++) {
                // start the process
                if (auditProcessRecommendation.getAuditProcess().getName().equalsIgnoreCase(deploymentInfoResults.getResult().get(i).getDisplayName())) {
                    ProcessInstance processInstance = processAPI.startProcess(deploymentInfoResults.getResult().get(i).getProcessId());
                    auditProcessRecommendation.setBonitaBpmCaseId(processInstance.getId());
                }
            }
            logoutBonita();
        } catch (Exception e) {
            auditProcessRecommendation.setBonitaBpmCaseId(0L);
        }
    }

    public void stopBPMProcess(AuditProcessRecommendation auditProcessRecommendation){
        if (auditProcessRecommendation.getBonitaBpmCaseId() != 0L) {
            try {
                loginBonita();
                ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(this.apiSession);
                processAPI.cancelProcessInstance(auditProcessRecommendation.getBonitaBpmCaseId());
                logoutBonita();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loginBonita(){
        try {
            Map<String, String> settings = new HashMap<>();
            settings.put("server.url", "http://" + bonitaHost + ":" + bonitaPort);
            settings.put("application.name", "bonita");
            APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, settings);
            // get the LoginAPI using the TenantAPIAccessor
            this.loginAPI = TenantAPIAccessor.getLoginAPI();
            // log in to the tenant to create a session
            this.apiSession = loginAPI.login(username, password);
        } catch (Exception ignore) {}
    }

    private void logoutBonita(){
        // don't forget to log out:
        try {
            this.loginAPI.logout(this.apiSession);
        } catch (Exception ignored) {}
    }

}
