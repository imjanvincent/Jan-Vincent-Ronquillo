package com.retail.store.discount.exception.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retail.store.discount.exception.RetailStoreDiscountException;

public class RetailStoreDiscountExceptionTest {

	private RetailStoreDiscountException classUnderTest;

	@Before
	public void setUp() {
		classUnderTest = new RetailStoreDiscountException("Test RetailStoreDiscountException");
	}

	@After
	public void tearDown() {
		classUnderTest = null;
	}

	@Test
	public void testRetailStoreDiscountException() {
		assertEquals("Test RetailStoreDiscountException", classUnderTest.getMessage());
	}
}
