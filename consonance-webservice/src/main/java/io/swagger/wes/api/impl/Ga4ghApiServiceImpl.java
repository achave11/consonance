package io.swagger.wes.api.impl;

import com.google.gson.Gson;

import io.swagger.wes.api.*;
import io.swagger.wes.model.*;

import io.swagger.wes.model.Ga4ghWesServiceInfo;
import io.swagger.wes.model.Ga4ghWesWorkflowListResponse;
import io.swagger.wes.model.Ga4ghWesWorkflowLog;
import io.swagger.wes.model.Ga4ghWesWorkflowRequest;
import io.swagger.wes.model.Ga4ghWesWorkflowRunId;
import io.swagger.wes.model.Ga4ghWesWorkflowStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.swagger.wes.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.consonance.webservice.ConsonanceWebserviceConfiguration;
import io.consonance.webservice.core.ConsonanceUser;
import io.consonance.webservice.resources.OrderResource;
import io.consonance.arch.beans.Job;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-09-15T17:06:31.319-07:00")
public class Ga4ghApiServiceImpl extends Ga4ghApiService {

    private static final Logger LOG = LoggerFactory.getLogger(Ga4ghApiServiceImpl.class);

    private static ConsonanceWebserviceConfiguration config;
    private static OrderResource orderResource;
    private static Gson gson = new Gson();

    public static void setConfig(ConsonanceWebserviceConfiguration config) {
        Ga4ghApiServiceImpl.config = config;
    }

    public static void setOrderResource(OrderResource orderResource) {
        Ga4ghApiServiceImpl.orderResource = orderResource;
    }

    @Override
    public Response cancelJob(String workflowId, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.cancelJob()");
        LOG.info("Cancelling job " + workflowId);

        final Job workflowRun = orderResource.getWorkflowRun(user, workflowId);
        // no-op, Consonance doesn't really support cancellation
        Ga4ghWesWorkflowRunId id = new Ga4ghWesWorkflowRunId();
        id.setWorkflowId(workflowRun.getUuid());
        return Response.ok().entity(workflowId).build();
    }

    @Override
    public Response getServiceInfo(ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getServiceInfo()");
        Ga4ghWesServiceInfo serviceInfo = new Ga4ghWesServiceInfo();
        // Supported formats
        String cwl = "CWL";
        String wdl = "WDL";
        // Version of each
        String wdlVersion = "0.14"; // Latest release 2017-08-16T00:00:00.000-00:00
        String cwlVersion = "v1.0.2";

        // Supported WDL version
        Ga4ghWesWorkflowTypeVersion versionTypeWDL = new Ga4ghWesWorkflowTypeVersion();
        versionTypeWDL.addWorkflowTypeVersionItem(wdlVersion);
        // Add to Map the WDL specifics
        serviceInfo.putWorkflowTypeVersionsItem(wdl, versionTypeWDL);

        // Supported CWL version
        Ga4ghWesWorkflowTypeVersion versionTypeCWL = new Ga4ghWesWorkflowTypeVersion();
        versionTypeCWL.addWorkflowTypeVersionItem(cwlVersion);
        // Add it to the map
        serviceInfo.putWorkflowTypeVersionsItem(cwl, versionTypeCWL);

        // WES version supported
        String wesApiVersion = "v1.0";
        // Add to map
        serviceInfo.addSupportedWesVersionsItem(wesApiVersion);

        // Supported file system protocol
        List<String> supportedFileProtocols = Arrays.asList("http", "https", "sftp", "s3", "gs", "file", "synapse");
        // Add to map
        serviceInfo.setSupportedFilesystemProtocols(supportedFileProtocols);

        // Engine and version
        String cwltool = "cwltool";
        String cwltoolVersion = "1.0.20171107133715";
        serviceInfo.putEngineVersionsItem(cwltool, cwltoolVersion);
        String dockstore = "dockstore";
        String dockstoreVersion = "1.3.0";
        serviceInfo.putEngineVersionsItem(dockstore, dockstoreVersion);

        // System state counts
        // TODO: cordinate this message for endpoint, each state requires to be acknowledge
        serviceInfo.putSystemStateCountsItem("Unknown", (long) 0);
        serviceInfo.putSystemStateCountsItem("Queued", (long) 0);
        serviceInfo.putSystemStateCountsItem("Running", (long) 0);
        serviceInfo.putSystemStateCountsItem("Paused", (long) 0);
        serviceInfo.putSystemStateCountsItem("Complete", (long) 0);
        serviceInfo.putSystemStateCountsItem("Error", (long) 0);
        serviceInfo.putSystemStateCountsItem("SystemError", (long) 0);
        serviceInfo.putSystemStateCountsItem("Canceled", (long) 0);
        serviceInfo.putSystemStateCountsItem("Initializing", (long) 0);

        //TODO: properly design the report-back metadata parametes.
        serviceInfo.putKeyValuesItem("Metadata", "Nothing to report");

        //        String serviceInfo = "\n{"+
//    "\n\"workflow_type_versions\": {" +
//        "\n\"CWL\": [\"v1.0\"]"+
//    "\n}," +
//    "\n\"supported_wes_versions\": \"0.1.0\"," +
//    "\n\"supported_filesystem_protocols\": [\"file\"]," +
//    "\n\"engine_versions\": \"cwl-runner\"," +
//    "\n\"system_state_counts\": {}," +
//    "\n\"key_values\": {}" +
//    "\n}" ;
//        LOG.info(String.valueOf(serviceInfo));
        return Response.ok(String.valueOf(serviceInfo)).build();
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, String.valueOf(serviceInfo))).build();
    }

    @Override
    public Response getWorkflowLog(String workflowId, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getWorkflowLog()");
        LOG.info("Getting workflow log for " + workflowId);

        return Response.ok().entity(workflowId).build();
    }

    @Override
    public Response getWorkflowStatus(String workflowId, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getWorkflowStatus()");
        LOG.info("Getting workflow status for " + workflowId);

        return Response.ok().entity(workflowId).build();
    }

    @Override
    public Response listWorkflows(Long pageSize, String pageToken, String keyValueSearch, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.listWorkflows()");
        LOG.info("Listing workflows with page size " + pageSize + ", pageToken=" + pageToken + ", and keyValueSearch=" + keyValueSearch);

        Ga4ghWesWorkflowListResponse list = new Ga4ghWesWorkflowListResponse();
        return Response.ok().entity(list).build();
    }

    @Override
    public Response runWorkflow(Ga4ghWesWorkflowRequest body, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.runWorkflow()");
        LOG.info("Running workflow request " + body.toString());

        Job job = new Job();
        job.setEndUser(user.getName());
        orderResource.addOrder(user, job);

//        Ga4ghWesWorkflowRunId runID = new Ga4ghWesWorkflowRunId();
        Ga4ghWesWorkflowRequest request = new Ga4ghWesWorkflowRequest();
        return Response.ok().entity(request).build();
    }
}
