package io.swagger.wes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.consonance.arch.beans.Job;
import io.consonance.client.WebClient;
import io.consonance.common.CommonTestUtilities;
import io.consonance.common.Constants;
import io.consonance.common.Utilities;
import io.consonance.webservice.core.ConsonanceUser;
import io.dropwizard.jackson.Jackson;

import io.swagger.client.api.UserApi;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import scala.Int;
import io.swagger.wes.model.Ga4ghWesServiceInfo;

import javax.xml.ws.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.*;

/**
 * Created by AbrahamC on 11/21/17.
 */

public class Ga4ghApiTest {
//    public static WebClient getWebClient() throws IOException, TimeoutException {
//        return getWebClient(true);
//    }
//
//    public static WebClient getWebClient(boolean correctUser) throws IOException, TimeoutException {
//        CommonTestUtilities.clearState();
//        File configFile = FileUtils.getFile("src", "test", "resources", "config");
//        HierarchicalINIConfiguration parseConfig = Utilities.parseConfig(configFile.getAbsolutePath());
//        WebClient client = new WebClient();
//        client.setBasePath(parseConfig.getString(Constants.WEBSERVICE_BASE_PATH));
//        client.addDefaultHeader("Authorization", "Bearer " + (correctUser? parseConfig.getString(Constants.WEBSERVICE_TOKEN) : "foobar"));
//        return client;
//    }
//    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void cancelJob() throws Exception {
    Ga4ghApi ga4ghApi = new Ga4ghApi();


    }
    @Ignore
    @Test
    public void getServiceInfo() throws Exception {
//        WebClient client = getWebClient();
//        UserApi userApi = new UserApi(client);

        Ga4ghApi ga4ghApi = new Ga4ghApi();
        ConsonanceUser userConsonance = new ConsonanceUser();
        userConsonance.setUserID(0);

//        final String expected = MAPPER.writeValueAsString(
//                MAPPER.readValue(fixture("fixtures/serviceInfo.json"), Ga4ghWesServiceInfo.class));
//        System.out.println(expected);
//        OutboundJaxrsResponse getInfoResponse = (OutboundJaxrsResponse) ga4ghApi.getServiceInfo(userConsonance);
//        String json = getInfoResponse.toString();
        System.out.println(String.valueOf(ga4ghApi.getServiceInfo(userConsonance)));
        assertSame(ga4ghApi.getServiceInfo(userConsonance), "Lolo");

    }

    @Test
    public void getWorkflowLog() throws Exception {

    }

    @Test
    public void getWorkflowStatus() throws Exception {

    }

    @Test
    public void listWorkflows() throws Exception {

    }

    @Test
    public void runWorkflow() throws Exception {

    }

}