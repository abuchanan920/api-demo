package com.thoughtworks.demo.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.thoughtworks.demo.model.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Path("/greeting")
@Api(value = "/greeting")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
@PermitAll
public class GreetingResource {
	private String greeting;
	private String person;
	
	public GreetingResource(String greeting, String person) {
		this.greeting = greeting;
		this.person = person;
	}
	
	@GET
	@Timed
	public Response getGreeting(@ApiParam(value = "The name of the person to greet", required=false) @QueryParam("person") String person) {
		if (person == null) person = this.person;
		
		String result = "{ \"greeting\": \"" + greeting + "\", \"person\": \"" + person + "\" }";
		return Response.ok(result).build();
	}

	@GET
	@Path("/class")
	@Timed
	public Greeting getGreetingClass(@ApiParam(value = "The name of the person to greet", required=false) @QueryParam("person") String person) {
		Greeting result = new Greeting(greeting, person == null ? this.person : person);
		return result;
	}
	
}
