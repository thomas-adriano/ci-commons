package com.codery.cicommons.core;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class DefaultRestApi implements RestApi {

	private Client client;
	private WebTarget target;

	public DefaultRestApi(String url) {
		client = ClientBuilder.newClient();
		target = client.target(url);
	}

	@Override
	public void request(Object obj, String... path) {
		if (path != null && path.length > 0) {
			for (String p : path) {
				target = target.path(p);
			}
		}

		target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(obj))
				.close();
	}

}
