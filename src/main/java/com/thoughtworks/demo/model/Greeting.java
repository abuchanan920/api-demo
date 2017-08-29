package com.thoughtworks.demo.model;

public class Greeting {
	private final String greeting;
	private final String person;
	
	public Greeting(String greeting, String person) {
		this.greeting = greeting;
		this.person = person;
	}
	
	public String getGreeting() {
		return greeting;
	}
	public String getPerson() {
		return person;
	}
}
