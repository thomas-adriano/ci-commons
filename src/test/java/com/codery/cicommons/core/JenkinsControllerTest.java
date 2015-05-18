package com.codery.cicommons.core;

import static org.junit.Assert.*;

import java.io.File;

import static org.easymock.EasyMock.*;

import org.easymock.Capture;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class JenkinsControllerTest {

	private CIController controller;
	private Job job;
	@Mock
	private RestApi restApi;

	@Before
	public void setUp() {
		controller = new JenkinsController(restApi);
		job = new Job("test");
	}

	@Test
	public void should_CreateJob() {
		Capture<Job> capturedJob = verifyRestApiMethodCall("job", "create");

		controller.createJob(job);
		
		assertEquals(
				"O Job enviado para a interface REST é diferente do job recebido pela interface REST",
				job, capturedJob.getValue());
	}

	@Test
	public void should_CreateJob_With_Workspace() {
		Capture<Job> capturedJob = verifyRestApiMethodCall("job", "create");

		job = job.builder().workspace(new File("")).build();

		controller.createJob(job);

		assertEquals(
				"O Job enviado para a interface REST é diferente do job recebido pela interface REST",
				job, capturedJob.getValue());

	}

	private Capture<Job> verifyRestApiMethodCall(String... path) {
		Capture<Job> capturedJob = Capture.newInstance();
		restApi.request(capturedJob, path);
		replay(restApi);
		try {
			verify(restApi);
		} catch (AssertionError error) {
			throw new AssertionError(
					"Nenhum request foi executado na interface REST.", error);
		}
		return capturedJob;
	}

}
