package com.thoughtworks.demo.resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreetingResourceTest {

	@Test
	public void test() {
		String greeting = "test-greeting";
		String person = "test-person";
		GreetingResource res = new GreetingResource(greeting, person);
		String s = (String)res.getGreeting(null).getEntity();
		assertTrue(s.contains(greeting));
		assertTrue(s.contains(person));
	}

}
