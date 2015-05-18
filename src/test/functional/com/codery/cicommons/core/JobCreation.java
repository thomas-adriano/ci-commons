package com.codery.cicommons.core;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class JobCreation {

	private String JENKINS_URL = "http://localhost:8080/";
	private Job job;
	private CIController controller;
	
	@Before
	public void setUp() {
		job = new Job("JOB_NAME");
		controller = new JenkinsController(new DefaultRestApi(JENKINS_URL));
	}
	
	@Test
	public void empty() {
		controller.createJob(job);
	}
	
	@Test
	public void parametrized() {
		JobParameter param = new StringJobParameter("name", "value", "desc");
		job.addParameter(param);
		controller.createJob(job);
	}
	
	@Test
	public void with_workspace() {
		job.workspace(new File("some/wks"));
		controller.createJob(job);
	}
	
}
