package com.anirban.hibernatetest;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateTestApplicationTests extends TestCase {

	@Test
	void contextLoads() {
		TestResult testResult = createResult();
	}

}
