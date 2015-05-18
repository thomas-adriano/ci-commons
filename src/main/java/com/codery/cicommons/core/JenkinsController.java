package com.codery.cicommons.core;

public class JenkinsController implements CIController {

	private final RestApi api;
	
	
	public JenkinsController(RestApi api) {
		this.api = api;
	}
	
	public void createJob(Job job) {
		api.request(job, "job", "create");
	}

}
