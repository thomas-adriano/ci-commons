package com.codery.cicommons.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Job {

	private final String name;
	private File workspace;
	private List<JobParameter> parameters;

	public Job(String name) {
		this.name = name;
	}
	
	public Job(Job job) {
		this.name = job.name;
		this.parameters = new ArrayList<JobParameter>(parameters);
		this.workspace = new File(workspace.getAbsolutePath());
	}
	
	public JobBuilder builder() {
		return new JobBuilder(new Job(this));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Job)) {
			return false;
		}
		
		Job casted = (Job) obj;
		
		return casted.name.equals(this.name) && casted.workspace.equals(this.workspace) && casted.parameters.equals(this.parameters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, workspace, parameters);
	}
	
	public File getWorkspace() {
		return workspace;
	}

	public void setWorkspace(File workspace) {
		this.workspace = workspace;
	}

	public List<JobParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<JobParameter> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(JobParameter param) {
		this.parameters.add(param);	
	}

	public String getName() {
		return name;
	}
	
	public static class JobBuilder {

		private final Job job;

		private JobBuilder(Job job) {
			this.job = job;
		}
		
		public JobBuilder workspace(File wks) {
			this.job.setWorkspace(wks);
			return this;
		}
		
		public JobBuilder addParameter(JobParameter param) {
			this.job.addParameter(param);
			return this;
		}
		
		public Job build() {
			return job;
		}
		
	}

}
