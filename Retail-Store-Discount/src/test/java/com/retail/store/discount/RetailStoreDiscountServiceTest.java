package com.retail.store.discount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.model.Item;
import com.retail.store.discount.model.ItemType;
import com.retail.store.discount.model.RetailStoreDiscountResponse;
import com.retail.store.discount.model.User;
import com.retail.store.discount.model.UserType;
import com.retail.store.discount.service.RetailStoreDiscountService;

public class RetailStoreDiscountServiceTest {

	private RetailStoreDiscountService classUnderTest;
	private User user;
	private RetailStoreDiscountResponse response;

	@Before
	public void setUp() {
		classUnderTest = new RetailStoreDiscountService();
		user = new User();
		populateUser(user);
		response = new RetailStoreDiscountResponse();
	}

	@After
	public void tearDown() {
		classUnderTest = null;
		user = null;
		response = null;
	}

	/**
	 * Method to test map of user details
	 */
	@Test
	public void retrieveSalesTest() {
		Map<Integer, User> map = classUnderTest.retrieveSales();
		System.out.println(map.size());
		assertEquals(2, map.size());
	}

	/**
	 * Method to test API response Expected: RetailStoreDiscountResponse should be
	 * populated
	 * 
	 * @throws RetailStoreDiscountException
	 */
	@Test
	public void addSaleTest() throws RetailStoreDiscountException {
		response = classUnderTest.addSale(user);
		assertNotNull(response);
		assertEquals(260.0, response.getGrandTotal(), 0.0f);
	}

	/**
	 * Method to when User object is null Expected: RetailStoreDiscountResponse
	 * should be populated
	 * 
	 * @throws RetailStoreDiscountException
	 */
	@Test
	public void addSaleForNullUserTest() throws RetailStoreDiscountException {
		response = classUnderTest.addSale(null);
		assertEquals(0, response.getId());
	}

	/**
	 * Method to test calculation of the discount amount Expected: Should return the
	 * discounted amount
	 */
	@Test
	public void getAmountAfterDiscountTest() {
		float amountAfterDiscount = classUnderTest.getAmountAfterDiscount(30, 100);
		assertEquals(70.0, amountAfterDiscount, 0.0f);
	}

	/**
	 * Method to test discount for EMPLOYEE Expected: Should populate
	 * RetailStoreDiscountResponse fields and calculate amount based on discount
	 */
	@Test
	public void getDiscountForEmployeeTest() {
		response.setNonGroceryAmount(1000);
		RetailStoreDiscountResponse result = classUnderTest.getDiscount(user, response);
		assertNotNull(result);
		assertEquals("EMPLOYEE", result.getUserType());
		assertEquals(30, result.getDiscountPercentage());
		assertEquals(300.0, result.getDiscountedAmount(), 0.0f);
		assertEquals(700.0, result.getAmountAfterDiscount(), 0.0f);
	}

	/**
	 * Method to test discount for AFFILIATE Expected: Should populate
	 * RetailStoreDiscountResponse fields and calculate amount based on discount
	 */
	@Test
	public void getDiscountForAffiliateTest() {
		response.setNonGroceryAmount(1000);
		user.setUserType(UserType.AFFILIATE);
		RetailStoreDiscountResponse result = classUnderTest.getDiscount(user, response);
		assertNotNull(result);
		assertEquals("AFFILIATE", result.getUserType());
		assertEquals(10, result.getDiscountPercentage());
		assertEquals(100.0, result.getDiscountedAmount(), 0.0f);
		assertEquals(900.0, result.getAmountAfterDiscount(), 0.0f);
	}

	/**
	 * Method to test discount for CUSTOMER Expected: Should populate
	 * RetailStoreDiscountResponse fields and calculate amount based on discount
	 */
	@Test
	public void getDiscountForCustomerTest() {
		response.setNonGroceryAmount(1000);
		user.setUserType(UserType.CUSTOMER);
		RetailStoreDiscountResponse result = classUnderTest.getDiscount(user, response);
		assertNotNull(result);
		assertEquals("CUSTOMER", result.getUserType());
		assertEquals(5, result.getDiscountPercentage());
		assertEquals(50.0, result.getDiscountedAmount(), 0.0f);
		assertEquals(950.0, result.getAmountAfterDiscount(), 0.0f);
	}

	/**
	 * Method to get the sum of discounted and non-discounted items Expected: Should
	 * return the sum of discounted and non-discounted items
	 * 
	 * @throws RetailStoreDiscountException
	 */
	@Test
	public void getTotalAmountTest() throws RetailStoreDiscountException {
		float totalAmount = classUnderTest.getTotalAmount(400, 100);
		assertEquals(500.0, totalAmount, 0.0f);
	}

	/**
	 * Method to calculate the total user bill Expected: Should return the grand
	 * total amount after deducting 5 per 100 bill
	 */
	@Test
	public void getGrandTotalTest() {
		response.setTotalAmount(1000);
		RetailStoreDiscountResponse result = classUnderTest.getGrandTotal(response);
		assertEquals(950.0, result.getGrandTotal(), 0.0f);
	}

	/**
	 * Method to test when response is null Expected: Should return the grand total
	 * amount after deducting 5 per 100 bill
	 */
	@Test
	public void getGrandTotalNullResponseTest() {
		RetailStoreDiscountResponse result = classUnderTest.getGrandTotal(null);
		assertNull(result);
	}

	/**
	 * Method to test when total amount is zero Expected: Should return the grand
	 * total amount after deducting 5 per 100 bill
	 */
	@Test
	public void getGrandTotalTotalAmountZeroTest() {
		response.setTotalAmount(0);
		RetailStoreDiscountResponse result = classUnderTest.getGrandTotal(response);
		assertEquals(0.0, result.getGrandTotal(), 0.0f);
	}

	/**
	 * Method to get the total amount of non-grocery items Expected: Should return
	 * the amount of non-grocery items
	 */
	@Test
	public void nonGroceryAmountTest() {
		RetailStoreDiscountResponse result = classUnderTest.nonGroceryAmount(user.getItems(), response);
		assertEquals(100.0, result.getNonGroceryAmount(), 0.0f);
		assertEquals(200.0, result.getGroceryItemAmount(), 0.0f);

	}

	/**
	 * Method to test if customer is over 2 years
	 */
	@Test
	public void isCustomerOverTwoYearsTest() {
		user.setUserType(UserType.CUSTOMER);
		boolean result = classUnderTest.isCustomerOverTwoYears(user.getCreatedDate());
		assertTrue(result);
	}

	/**
	 * Method to test if customer is under 2 years
	 */
	@Test
	public void isCustomerUnderTwoYearsTest() {
		user.setUserType(UserType.CUSTOMER);
		user.setCreatedDate(LocalDate.of(2018, 01, 01));
		boolean result = classUnderTest.isCustomerOverTwoYears(user.getCreatedDate());
		assertFalse(result);
	}

	/**
	 * Method to test when created date is null
	 */
	@Test
	public void nullCreatedDate() {
		user.setUserType(UserType.CUSTOMER);
		user.setCreatedDate(LocalDate.of(2018, 01, 01));
		boolean result = classUnderTest.isCustomerOverTwoYears(null);
		assertFalse(result);
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
