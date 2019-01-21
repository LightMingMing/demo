package com.demo.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JUnit4ExecutionProcessDemo {

	@ClassRule
	public static TestRule classRule = new TestRule() {
		@Override
		public Statement apply(Statement base, Description description) {
			return new Statement() {
				public void evaluate() throws Throwable {
					System.out.println("@ClassRule before");
					base.evaluate();
					System.out.println("@ClassRule after");
				};
			};
		}
	};

	@BeforeClass
	public static void beforeClass1() {
		System.out.println("@Before Class 1");
	}

	@BeforeClass
	public static void beforeClass2() {
		System.out.println("@Before Class 2");
	}

	@Rule
	public TestRule rule = new TestRule() {
		@Override
		public Statement apply(Statement base, Description description) {
			return new Statement() {
				public void evaluate() throws Throwable {
					System.out.println("@Rule 1 before");
					base.evaluate();
					System.out.println("@Rule 1 after");
				};
			};
		}
	};

	@Rule
	public TestRule rule2 = new TestRule() {
		@Override
		public Statement apply(Statement base, Description description) {
			return new Statement() {
				public void evaluate() throws Throwable {
					System.out.println("@Rule 2 before");
					base.evaluate();
					System.out.println("@Rule 2 after");
				};
			};
		}
	};

	@Before
	public void before1() {
		System.out.println("@Before 1");
	}

	@Before
	public void before2() {
		System.out.println("@Before 2");
	}

	@Test
	public void test1() {
		System.out.println("@Test 1");
	}

	@Test
	@Ignore
	public void ignore() {
		System.out.println("@Ignore");
	}

	@Test
	public void test2() {
		System.out.println("@Test 2");
	}

	@After
	public void after1() {
		System.out.println("@After 1");
	}

	@After
	public void after2() {
		System.out.println("@After 2");
	}

	@AfterClass
	public static void afterClass1() {
		System.out.println("@After Class 1");
	}

	@AfterClass
	public static void afterClass2() {
		System.out.println("@After Class 2");
	}
}


/*
Output:
@ClassRule before
@Before Class 2
@Before Class 1
@Rule 2 before
@Rule 1 before
@Before 2
@Before 1
@Test 1
@After 1
@After 2
@Rule 1 after
@Rule 2 after
@Rule 2 before
@Rule 1 before
@Before 2
@Before 1
@Test 2
@After 1
@After 2
@Rule 1 after
@Rule 2 after
@After Class 1
@After Class 2
@ClassRule after
 */
