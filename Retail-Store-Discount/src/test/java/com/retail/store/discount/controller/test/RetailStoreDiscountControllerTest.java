package com.retail.store.discount.controller.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.retail.store.discount.controller.RetailStoreDiscountController;
import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.model.Item;
import com.retail.store.discount.model.ItemType;
import com.retail.store.discount.model.RetailStoreDiscountResponse;
import com.retail.store.discount.model.User;
import com.retail.store.discount.model.UserType;
import com.retail.store.discount.service.RetailStoreDiscountService;

public class RetailStoreDiscountControllerTest {

	private RetailStoreDiscountController classUnderTest;
	private RetailStoreDiscountResponse response;
	private User user;
	private RetailStoreDiscountService service;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		classUnderTest = new RetailStoreDiscountController();
		response = new RetailStoreDiscountResponse();
		user = new User();
		populateUser(user);
		service = Mockito.mock(RetailStoreDiscountService.class);
	}
	
	
	@Test
	public void checkServiceTest() {
		assertEquals("Service is up!", classUnderTest.checkService());
	}
	
	@Test
	public void addSaleExceptionTest() throws RetailStoreDiscountException {
		thrown.expect(RetailStoreDiscountException.class);
		classUnderTest.addSale(null);
	}
	
	@After
	public void tearDown() {
		classUnderTest = null;
		response = null;
		user = null;
	}
	
	private User populateUser(User user) {
		user.setId(1);
		user.setCreatedDate(LocalDate.of(2017, 01, 01));
		user.setUserType(UserType.EMPLOYEE);
		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setId(1);
		item.setAmount("100");
		item.setItemType(ItemType.CLOTHING);
		item.setName("Pants");
		items.add(item);

		Item grocery = new Item();
		grocery.setId(2);
		grocery.setAmount("200");
		grocery.setItemType(ItemType.GROCERY);
		grocery.setName("Milk");
		items.add(grocery);

		user.setItems(items);

		return user;
	}


}
