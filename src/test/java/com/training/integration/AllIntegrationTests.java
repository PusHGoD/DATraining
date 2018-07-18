package com.training.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LocationControllerIntegrationTest.class, ProductControllerIntegrationTest.class })
public class AllIntegrationTests {

}
