/*
 *     Consonance - workflow software for multiple clouds
 *     Copyright (C) 2016 OICR
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.consonance.client.cli;

import io.consonance.arch.containerProvisioner.ContainerProvisionerThreads;
import io.consonance.arch.coordinator.Coordinator;
import io.consonance.arch.jobGenerator.JobGenerator;
import io.consonance.arch.worker.Worker;
import io.consonance.arch.utils.CommonServerTestUtilities;
import io.consonance.client.WebClient;
import io.consonance.webservice.ConsonanceWebserviceApplication;
import io.consonance.webservice.ConsonanceWebserviceConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.swagger.client.api.OrderApi;
import io.swagger.client.model.Job;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

/**
 * Test the main method in conjunction with a real workflow.
 *
 * @author dyuen
 */
public class SystemMainIT {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @ClassRule
    public static final DropwizardAppRule<ConsonanceWebserviceConfiguration> RULE =
            new DropwizardAppRule<>(ConsonanceWebserviceApplication.class, ResourceHelpers.resourceFilePath("run-fox.yml"));

    @BeforeClass
    public static void setup() throws IOException, TimeoutException {

        // clears the PostgreSQL DB and the rabbitMQ message queue
        CommonServerTestUtilities.clearState();

    }

    @Test
    public void testGetConfiguration() throws Exception {
        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        main.runMain(new String[] { "--metadata" });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("database"));
    }

    @Test
    public void testQuietGetConfiguration() throws Exception {
        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        main.runMain(new String[] { "--quiet", "--metadata" });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("database"));
    }

    @Test
    public void testDebugGetConfiguration() throws Exception {
        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        main.runMain(new String[] { "--debug", "--metadata" });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("database"));
    }

    @Test
    public void testScheduleAndCheckStatus() throws Exception{
        final WebClient webClient = SystemClientIT.getWebClient();

        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        final File file = Files.createTempFile("test", "test").toFile();

        // submitting a job via the web service
        main.runMain(new String[] { "run","--flavour","m1.test","--image-descriptor", file.getAbsolutePath() ,
                "--run-descriptor", file.getAbsolutePath(),
                "--format", "cwl",
                "--extra-file", "node-engine.cwl="+file.getAbsolutePath()+"=true",
                "--extra-file", "pointless.txt="+file.getAbsolutePath()+"=false"});
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        // lookup the order via web service
        OrderApi api = new OrderApi(webClient);
        final List<Job> jobs = api.listWorkflowRuns();
        assertTrue(jobs.size() == 1);
        Job job = jobs.get(0);

        // only the file with keep=true should have been kept
        assertTrue(job.getExtraFiles().size() == 1);

        //reset
        systemOutRule.clearLog();
        // status check the UUID
        main.runMain(new String[] { "status", "--job_uuid", job.getJobUuid() });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        // clear things out
        CommonServerTestUtilities.clearState();
    }

    @Test
    public void testHTTPScheduleAndCheckStatus() throws Exception{
        final WebClient webClient = SystemClientIT.getWebClient();

        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        final File file = Files.createTempFile("test", "test").toFile();
        final File cwlFile = FileUtils.getFile("src", "test", "resources", "hello.cwl");
        final File cwlJsonFile = FileUtils.getFile("src", "test", "resources", "hello.cwl.json");
        main.runMain(new String[] { "run","--flavour","m1.test","--image-descriptor", cwlFile.getAbsolutePath() ,
                "--run-descriptor",  cwlJsonFile.getAbsolutePath(), "--format", "cwl"});
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        OrderApi api = new OrderApi(webClient);
        final List<Job> jobs = api.listWorkflowRuns();
        assertTrue(jobs.size() == 1);
        Job job = jobs.get(0);

        // assert that readme looks ok
        assertTrue(!job.getContainerImageDescriptor().isEmpty());
        assertTrue(!job.getContainerRuntimeDescriptor().isEmpty());

        //reset
        systemOutRule.clearLog();
        // status check the UUID
        main.runMain(new String[] { "status", "--job_uuid", job.getJobUuid() });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        // clear things out
        CommonServerTestUtilities.clearState();
    }

    @Test
    public void testScheduleAndRunWdlLocally() throws Exception{
        final WebClient webClient = SystemClientIT.getWebClient();

        // Consonance Client io.consonance.client.cli.Main
        Main main = new Main();
        main.setWebClient(SystemClientIT.getWebClient());
        final File file = Files.createTempFile("test", "test").toFile();
        final File wdlFile = FileUtils.getFile("src", "test", "resources", "hello.wdl");
        final File wdlJsonFile = FileUtils.getFile("src", "test", "resources", "hello.wdl.json");

        // submitting a job via the web service
        main.runMain(new String[] { "run","--flavour","m1.test2","--image-descriptor", wdlFile.getAbsolutePath() ,
                "--run-descriptor", wdlJsonFile.getAbsolutePath(),
                "--format", "wdl",
                "--extra-file", "node-engine.cwl="+file.getAbsolutePath()+"=true",
                "--extra-file", "pointless.txt="+file.getAbsolutePath()+"=false"});
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        // lookup the order via web service
        OrderApi api = new OrderApi(webClient);
        final List<Job> jobs = api.listWorkflowRuns();
        assertTrue(jobs.size() == 1);
        Job job = jobs.get(0);

        // only the file with keep=true should have been kept
        assertTrue(job.getExtraFiles().size() == 1);

        //reset
        systemOutRule.clearLog();
        // status check the UUID
        main.runMain(new String[] { "status", "--job_uuid", job.getJobUuid() });
        // reset system.out
        // check out the output
        assertTrue(systemOutRule.getLog().contains("job_uuid"));

        // setup the coordinator to prep the order in to job and vm orders
        final File file2 = FileUtils.getFile("src", "test", "resources", "config");
        File iniDir = FileUtils.getFile("ini");
        // so this should take an order and split it into a VM order and a job in the m1.test2 queue
        Coordinator.main(new String[] { "--config", file2.getAbsolutePath() });

        // this runs the worker daemon directly and executes the command locally when in local mode
        // if we used --test instead it would run the worker daemon locally too *but* it wouldn't actually execute anything
        ContainerProvisionerThreads.main(new String[] { "--config", file2.getAbsolutePath(), "--flavour", "m1.test2", "--local"});

        // cleanup jobs
        // I have to loop here because, without endless mode which wouldn't run in an integration test, I need multiple calls to empty the status queue
        for (int i=0; i<15; i++) {
            Coordinator.main(new String[]{"--config", file2.getAbsolutePath()});
        }

        // status
        main.runMain(new String[] { "status", "--job_uuid", job.getJobUuid() });

        // clear things out
        CommonServerTestUtilities.clearState();
    }

    // TODO: above with Docker-based WDL, the example WDL we have now only executes echo locally (not a docker-based tool)

    @AfterClass
    public static void cleanup() throws IOException, TimeoutException {

        // clears the PostgreSQL DB and the rabbitMQ message queue
        CommonServerTestUtilities.clearState();

    }


}
