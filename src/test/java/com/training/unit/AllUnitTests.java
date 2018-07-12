package com.training.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LocationServiceUnitTest.class, ProductControllerUnitTest.class, ProductRepositoryUnitTest.class,
		ProductServiceUnitTest.class })

public class AllUnitTests {

}
