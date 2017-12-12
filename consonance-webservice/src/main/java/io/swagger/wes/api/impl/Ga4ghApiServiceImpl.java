package io.swagger.wes.api.impl;

import com.google.gson.Gson;

import io.swagger.wes.api.*;
import io.swagger.wes.api.NotFoundException;
import io.swagger.wes.model.*;
import io.swagger.wes.model.Ga4ghWesParameter;
import io.swagger.wes.model.Ga4ghWesServiceInfo;
import io.swagger.wes.model.Ga4ghWesWorkflowListResponse;
import io.swagger.wes.model.Ga4ghWesWorkflowLog;
import io.swagger.wes.model.Ga4ghWesWorkflowRequest;
import io.swagger.wes.model.Ga4ghWesWorkflowRunId;
import io.swagger.wes.model.Ga4ghWesState;
import io.swagger.wes.model.Ga4ghWesWorkflowStatus;
import io.swagger.workflow.model.JobStatus;

import java.util.List;
import java.util.Map;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.consonance.webservice.ConsonanceWebserviceConfiguration;
import io.consonance.webservice.core.ConsonanceUser;
import io.consonance.webservice.resources.OrderResource;

import io.consonance.arch.beans.Job;
import io.consonance.arch.beans.JobState;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

        Job workflowRun = orderResource.getWorkflowRun(user, workflowId);
        // no-op, Consonance doesn't really support cancellation
        Ga4ghWesWorkflowRunId id = new Ga4ghWesWorkflowRunId();
        id.setWorkflowId(workflowRun.getUuid());
        return Response.ok().entity(workflowId).build();
    }

    @Override
    public Response getServiceInfo(ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getServiceInfo()");
        String serviceInfo = "BS";
        // '{\n\t"workflow_type_versions": {\n\t\t"CWL": ["v1.0"]\n\t},\n\t"supported_wes_versions": "0.1.0",\n\t"supported_filesystem_protocols": ["file"],\n\t"engine_versions": "cwl-runner",\n\t"system_state_counts": {},\n\t"key_values": {}\n}';

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, serviceInfo)).build();
    }

    @Override
    public Response getWorkflowLog(String workflowId, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getWorkflowLog()");
        LOG.info("Getting workflow log for " + workflowId);

        Ga4ghWesWorkflowLog log = new Ga4ghWesWorkflowLog();
        log.setWorkflowId(workflowId);
        //log.setRequest(); // Uhhhhh store the workflow request object?
        //log.setState(); // Ga4ghWES State...

        Ga4ghWesLog workflowLog =  new Ga4ghWesLog();
        log.workflowLog(workflowLog); // Ga4ghWesLog
        //log.setTaskLogs(); // List<Ga4ghWesLog> taskLogs
        //log.setOutputs(); // List<Ga4ghWesParameter> outputs

        // This is tricky and requires some unpacking with 
        // orderResource.getWorkflowRunStreamingLog(consonanceUser, workflowId)

        return Response.ok().entity(log).build();
    }

    @Override
    public Response getWorkflowStatus(String workflowId, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.getWorkflowStatus()");
        LOG.info("Getting workflow status for " + workflowId);

        Ga4ghWesWorkflowStatus status = new Ga4ghWesWorkflowStatus();
        status.setWorkflowId(workflowId);

        Job requestedJob = orderResource.getWorkflowRun(user, workflowId);

        JobState consonanceState = requestedJob.getState();
        Ga4ghWesState state;
        switch (consonanceState) {
        	case START:
        		state = Ga4ghWesState.INITIALIZING;
        		break;
        	case PENDING:
        		state = Ga4ghWesState.QUEUED;
        		break;
        	case RUNNING:
        		state = Ga4ghWesState.RUNNING;
        		break;
        	case SUCCESS:
        		state = Ga4ghWesState.COMPLETE;
        		break;
        	case FAILED:
        		state = Ga4ghWesState.ERROR;
        		break;
        	case LOST:
        		state = Ga4ghWesState.SYSTEMERROR;
        		break;
        	default:
        		state = Ga4ghWesState.UNKNOWN;
        		break;
        }
        /* 
        Consonance status to the WES State constructor
        Available states:
			Enum:
			Array[9]
			0:"Unknown"
			1:"Queued"
			2:"Running"
			3:"Paused"
			4:"Complete"
			5:"Error"
			6:"SystemError"
			7:"Canceled"
			8:"Initializing"

			Consonance states:
			START -> Initializing
			PENDING -> Queued
			RUNNING -> Running
			SUCCESS -> Complete
			FAILED ->  Error [SystemError, Canceled]
			LOST -> [SystemError, Canceled]
        */
        status.setState(state);

        return Response.ok().entity(status).build();
    }

    @Override
    public Response listWorkflows(Long pageSize, String pageToken, String keyValueSearch, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.listWorkflows()");
        LOG.info("Listing workflows with page size " + pageSize + ", pageToken=" + pageToken + ", and keyValueSearch=" + keyValueSearch);

		/*
		Long pageSize
		String pageToken
		String keyValueSearch
		ConsonanceUser user */

        List<Job> jobListing = orderResource.listOwnedWorkflowRuns(user);

        Ga4ghWesWorkflowListResponse list = new Ga4ghWesWorkflowListResponse();
        return Response.ok().entity(list).build();
    }

    @Override
    public Response runWorkflow(Ga4ghWesWorkflowRequest body, ConsonanceUser user) throws NotFoundException {
        LOG.info("Hit WES API! Called Ga4ghApiServiceImpl.runWorkflow()");
        LOG.info("Running workflow request " + body.toString());

     	// The following things are for validation, not for building the job
     	//
        // workflow descriptor type, must be CWL or WDL...
        // workflow descriptor type version, must be one supported by this tool
        String workflowType = body.getWorkflowType();
        String workflowTypeVersion = body.getWorkflowTypeVersion();

        // Optional, arbitrary metadata and not strictly necessary
        Map<String, String> keyValues = body.getKeyValues();

        // Create new job object from the above values
        Job job = new Job();
        job.setEndUser(user.getName());

        // TODO: Get from getKeyValues
        // Ask Brian about requirement for flavour in getKeyValues?
        //
        // Possibly add as json parameter...
        String flavour = "c4.8xlarge";
        job.setFlavour(flavour);

        // Should be workflow descriptor, 
        // but it's uncertain whether it's a URL or a file...
        //
        // Required Worfklow CWL or WDL document
        String imageDescriptor = body.getWorkflowDescriptor();
		job.setContainerImageDescriptor(imageDescriptor);
        
        // Should be workflow params
        // Required the workflow paratermization document with all parameterizations for the workflow including input and output file locations
        String runtimeDescriptor = body.getWorkflowParams();
        job.setContainerRuntimeDescriptor(runtimeDescriptor);

        // Denis tes code, verify this works...
        Job orderedJob = orderResource.addOrder(user, job);

        Ga4ghWesWorkflowRunId runID = new Ga4ghWesWorkflowRunId();
        String consonanceUuid = orderedJob.getUuid();
        // How to set Consonance ID as runID
        runID.setWorkflowId(consonanceUuid);
        return Response.ok().entity(runID).build();
    }
}
