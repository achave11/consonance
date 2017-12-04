package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Ga4ghWesServiceInfo;
import io.swagger.model.Ga4ghWesWorkflowListResponse;
import io.swagger.model.Ga4ghWesWorkflowLog;
import io.swagger.model.Ga4ghWesWorkflowRequest;
import io.swagger.model.Ga4ghWesWorkflowRunId;
import io.swagger.model.Ga4ghWesWorkflowStatus;

import java.util.Arrays;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-29T15:53:35.382-08:00")
public class Ga4ghApiServiceImpl extends Ga4ghApiService {
    private static final Logger LOG = LoggerFactory.getLogger(Ga4ghApiServiceImpl.class);

    @Override
    public Response cancelJob(String workflowId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getServiceInfo(SecurityContext securityContext) throws NotFoundException {

        // do some magic!

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

//        LOG.info(String.valueOf(serviceInfo));
//            Response.accepted(serviceInfo);
//            Response.ResponseBuilder builder = Response.ok(String.valueOf(serviceInfo));
//            return Response.accepted().header("Service Info", serviceInfo).build();
//            builder.language("EN").header("Response", "Registered");
//            return builder.build();

//            return Response.ok().build();
        return Response.ok().entity(serviceInfo).build();
    }
    @Override
    public Response getWorkflowLog(String workflowId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getWorkflowStatus(String workflowId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response listWorkflows( Long pageSize,  String pageToken,  String keyValueSearch, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response runWorkflow(Ga4ghWesWorkflowRequest body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
