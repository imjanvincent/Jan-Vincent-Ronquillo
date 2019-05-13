package com.retail.store.discount.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.model.Item;
import com.retail.store.discount.model.ItemType;
import com.retail.store.discount.model.RetailStoreDiscountResponse;
import com.retail.store.discount.model.User;
import com.retail.store.discount.model.UserType;

/**
 * 
 * @author Jan Vincent
 *
 */
@Service
public class RetailStoreDiscountService extends AbstractRetailStoreDiscount {

	private static Map<Integer, User> userRepo = new HashMap<Integer, User>();

	private static final AtomicInteger count = new AtomicInteger(0);

	/**
	 * dummy User data
	 */
	static {
		User user1 = new User();
		user1.setId(count.incrementAndGet());
		user1.setUserType(UserType.EMPLOYEE);
		List<Item> itemList1 = new ArrayList<>();
		Item item1 = new Item();
		item1.setName("Candy");
		item1.setAmount("100");
		item1.setItemType(ItemType.OTHERS);
		itemList1.add(item1);
		user1.setItems(itemList1);
		user1.setCreatedDate(LocalDate.of(2017, 01, 01));
		userRepo.put(user1.getId(), user1);

		User user2 = new User();
		user2.setId(count.incrementAndGet());
		user2.setUserType(UserType.CUSTOMER);
		List<Item> itemList2 = new ArrayList<>();
		Item item2 = new Item();
		item2.setName("Pants");
		item2.setAmount("100");
		item2.setItemType(ItemType.CLOTHING);
		itemList2.add(item2);

		Item item3 = new Item();
		item3.setName("Computer");
		item3.setAmount("500");
		item3.setItemType(ItemType.OTHERS);
		itemList2.add(item3);
		user2.setItems(itemList2);
		userRepo.put(user2.getId(), user2);
	}

	/**
	 * Method to return dummy data
	 * 
	 * @return
	 */
	public Map<Integer, User> retrieveSales() {
		return userRepo;
	}

	/**
	 * Method to add new sale
	 * 
	 * @param user
	 * @return
	 * @throws RetailStoreDiscountException
	 */
	public RetailStoreDiscountResponse addSale(User user) throws RetailStoreDiscountException {
		RetailStoreDiscountResponse response = new RetailStoreDiscountResponse();
		if (user == null) {
			return response;
		}
		response.setId(user.getId());
		// get the amount for non-grocery items
		nonGroceryAmount(user.getItems(), response);
		// get total bill before discounts
		response.setTotalAmountBeforeDiscount(response.getNonGroceryAmount() + response.getGroceryItemAmount());
		// get the amount after discount
		getDiscount(user, response);
		// get the total amount by adding the discounted and non-discounted items
		response.setTotalAmount(getTotalAmount(response.getAmountAfterDiscount(), response.getGroceryItemAmount()));
		// get the grand total by deducting $5 for every $100 bill
		getGrandTotal(response);
		// add user to map
		userRepo.put(user.getId(), user);
		return response;
	}

}
