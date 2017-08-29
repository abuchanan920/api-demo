package com.thoughtworks.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class APIConfiguration extends Configuration {
	private String greeting;
	private String person;
	
	@JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;
	
	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		SwaggerBundleConfiguration config = new SwaggerBundleConfiguration();
		config.setResourcePackage("com.thoughtworks.demo.resources");
		return config;
	}

	@JsonProperty
	public String getGreeting() {
		greeting = getEnvironmentVar(greeting, "GREETING", "Hello");
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@JsonProperty
	public String getPerson() {
		person = getEnvironmentVar(person, "PERSON", "World!");
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	private String getEnvironmentVar(String currentValue, String environmentVariable, String defaultValue) {
		String result = currentValue;
		if (result == null) {
			result = System.getenv(environmentVariable);
			if (result == null) {
				result = defaultValue;
			}
		}
		return result;
	}

}
