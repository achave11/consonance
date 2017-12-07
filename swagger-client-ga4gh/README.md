# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.WorkflowExecutionServiceApi;

import java.io.File;
import java.util.*;

public class WorkflowExecutionServiceApiExample {

    public static void main(String[] args) {
        
        WorkflowExecutionServiceApi apiInstance = new WorkflowExecutionServiceApi();
        String workflowId = "workflowId_example"; // String | 
        try {
            Ga4ghWesWorkflowRunId result = apiInstance.cancelJob(workflowId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling WorkflowExecutionServiceApi#cancelJob");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*WorkflowExecutionServiceApi* | [**cancelJob**](docs/WorkflowExecutionServiceApi.md#cancelJob) | **DELETE** /ga4gh/wes/v1/workflows/{workflow_id} | Cancel a running workflow
*WorkflowExecutionServiceApi* | [**getServiceInfo**](docs/WorkflowExecutionServiceApi.md#getServiceInfo) | **GET** /ga4gh/wes/v1/service-info | Get information about Workflow Execution Service.  May include information related (but not limited to) the workflow descriptor formats, versions supported, the WES API versions supported, and information about general the service availability.
*WorkflowExecutionServiceApi* | [**getWorkflowLog**](docs/WorkflowExecutionServiceApi.md#getWorkflowLog) | **GET** /ga4gh/wes/v1/workflows/{workflow_id} | Get detailed info about a running workflow
*WorkflowExecutionServiceApi* | [**getWorkflowStatus**](docs/WorkflowExecutionServiceApi.md#getWorkflowStatus) | **GET** /ga4gh/wes/v1/workflows/{workflow_id}/status | Get quick status info about a running workflow
*WorkflowExecutionServiceApi* | [**listWorkflows**](docs/WorkflowExecutionServiceApi.md#listWorkflows) | **GET** /ga4gh/wes/v1/workflows | List the workflows, this endpoint will list the workflows in order of oldest to newest.  There is no guarantee of live updates as the user traverses the pages, the behavior should be decided (and documented) by each implementation.
*WorkflowExecutionServiceApi* | [**runWorkflow**](docs/WorkflowExecutionServiceApi.md#runWorkflow) | **POST** /ga4gh/wes/v1/workflows | Run a workflow, this endpoint will allow you to create a new workflow request and retrieve its tracking ID to monitor its progress.  An important assumption in this endpoint is that the workflow_params JSON will include parameterizations along with input and output files.  The latter two may be on S3, Google object storage, local filesystems, etc.  This specification makes no distinction.  However, it is assumed that the submitter is using URLs that this system both understands and can access. For Amazon S3, this could be accomplished by given the credentials associated with a WES service access to a particular bucket.  The details are important for a production system and user on-boarding but outside the scope of this spec.


## Documentation for Models

 - [Ga4ghWesLog](docs/Ga4ghWesLog.md)
 - [Ga4ghWesParameter](docs/Ga4ghWesParameter.md)
 - [Ga4ghWesParameterTypes](docs/Ga4ghWesParameterTypes.md)
 - [Ga4ghWesServiceInfo](docs/Ga4ghWesServiceInfo.md)
 - [Ga4ghWesServiceInfoRequest](docs/Ga4ghWesServiceInfoRequest.md)
 - [Ga4ghWesState](docs/Ga4ghWesState.md)
 - [Ga4ghWesWorkflowDesc](docs/Ga4ghWesWorkflowDesc.md)
 - [Ga4ghWesWorkflowListRequest](docs/Ga4ghWesWorkflowListRequest.md)
 - [Ga4ghWesWorkflowListResponse](docs/Ga4ghWesWorkflowListResponse.md)
 - [Ga4ghWesWorkflowLog](docs/Ga4ghWesWorkflowLog.md)
 - [Ga4ghWesWorkflowRequest](docs/Ga4ghWesWorkflowRequest.md)
 - [Ga4ghWesWorkflowRunId](docs/Ga4ghWesWorkflowRunId.md)
 - [Ga4ghWesWorkflowStatus](docs/Ga4ghWesWorkflowStatus.md)
 - [Ga4ghWesWorkflowTypeVersion](docs/Ga4ghWesWorkflowTypeVersion.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



