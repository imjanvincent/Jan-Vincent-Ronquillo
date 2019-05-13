package com.retail.store.discount.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.model.Item;
import com.retail.store.discount.model.ItemType;
import com.retail.store.discount.model.RetailStoreDiscountResponse;
import com.retail.store.discount.model.User;
import com.retail.store.discount.model.UserType;

public abstract class AbstractRetailStoreDiscount {

	private static final Logger LOGGER = Logger.getLogger(AbstractRetailStoreDiscount.class.getName());

	/**
	 * Method to get the sum of discounted and non-discounted items
	 * 
	 * @param amountAfterDiscount
	 * @param nonGroceryAmount
	 * @return
	 * @throws RetailStoreDiscountException 
	 */
	public float getTotalAmount(float amountAfterDiscount, float nonGroceryAmount) {
		return amountAfterDiscount + nonGroceryAmount;
	}

	/**
	 * Method to get the percentage discount of a user
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	public RetailStoreDiscountResponse getDiscount(User user, RetailStoreDiscountResponse response) {

		float discountedAmount, amountAfterDiscount;
		
		/**
		 * if user is an EMPLOYEE, 30% discount applies
		 */
		if (UserType.EMPLOYEE.equals(user.getUserType())) {
			response.setDiscountPercentage(30);
			response.setUserType(user.getUserType().name());
			discountedAmount = (response.getNonGroceryAmount() / 100) * response.getDiscountPercentage();
			response.setDiscountedAmount(discountedAmount);
			amountAfterDiscount = getAmountAfterDiscount(response.getDiscountPercentage(),
					response.getNonGroceryAmount());
			response.setAmountAfterDiscount(amountAfterDiscount);
		}
		
		/**
		 * if user is an AFFILIATE, 10% discount applies
		 */
		if (UserType.AFFILIATE.equals(user.getUserType())) {
			response.setDiscountPercentage(10);
			response.setUserType(user.getUserType().name());
			discountedAmount = (response.getNonGroceryAmount() / 100) * response.getDiscountPercentage();
			response.setDiscountedAmount(discountedAmount);
			amountAfterDiscount = getAmountAfterDiscount(response.getDiscountPercentage(),
					response.getNonGroceryAmount());
			response.setAmountAfterDiscount(amountAfterDiscount);
		}
		
		/**
		 * if user has been a customer for over 2 years, 5% discount applies
		 */
		if (UserType.CUSTOMER.equals(user.getUserType())) {
			response.setUserType(user.getUserType().name());
			if (isCustomerOverTwoYears(user.getCreatedDate())) {
				response.setDiscountPercentage(5);
				discountedAmount = (Float.valueOf(response.getNonGroceryAmount()) / 100)
						* response.getDiscountPercentage();
				response.setDiscountedAmount(discountedAmount);
				amountAfterDiscount = getAmountAfterDiscount(response.getDiscountPercentage(),
						response.getNonGroceryAmount());
				response.setAmountAfterDiscount(amountAfterDiscount);
			}
		}

		return response;
	}

	/**
	 * Method to get the amount after discount
	 * 
	 * @param discountPercentage
	 * @param amount
	 * @return
	 */
	public float getAmountAfterDiscount(int discountPercentage, float amount) {
		float s = 100 - discountPercentage;
		return (s * Float.valueOf(amount)) / 100;
	}

	/**
	 * Method to get the total amount of non-grocery items
	 * 
	 * @param items
	 * @param response
	 * @return
	 */
	public RetailStoreDiscountResponse nonGroceryAmount(List<Item> items, RetailStoreDiscountResponse response) {
		float nonGroceryAmount = 0, groceryItemAmount = 0;
		for (Item item : items) {
			if (!item.getItemType().equals(ItemType.GROCERY)) {
				LOGGER.info("Item ID: " + item.getId() + "-Item name: " + item.getName());
				nonGroceryAmount += Float.valueOf(item.getAmount());
			} else {
				LOGGER.info("Item ID: " + item.getId() + "-Item name: " + item.getName());
				groceryItemAmount += Float.valueOf(item.getAmount());
			}
		}
		response.setNonGroceryAmount(nonGroceryAmount);
		response.setGroceryItemAmount(groceryItemAmount);
		return response;
	}

	/**
	 * Method to get the total bill of the user
	 * 
	 * @param response
	 * @return
	 */
	public RetailStoreDiscountResponse getGrandTotal(RetailStoreDiscountResponse response) {
		if (response == null || response.getTotalAmount() == 0) {
			return response;
		}
		
		// No discount if total amount is less than 100
		if (response.getTotalAmount() < 100) {
			return response;
		}
		// For every $100 on the bill, there would be a $ 5 discount
		float discount = ((int) response.getTotalAmount() / 100) * 5;
		LOGGER.info("Total discount for every $100 bill : " + discount);
		// grandTotal = totalAmount - discount
		response.setGrandTotal(response.getTotalAmount() - discount);
		return response;
	}

	/**
	 * Method to validate if Customer is over 2 years
	 * 
	 * @param createdDate
	 * @return
	 */
	public boolean isCustomerOverTwoYears(LocalDate createdDate) {
		if (createdDate == null) {
			LOGGER.log(Level.WARNING, "Please input user created date.");
			return false;
		}
		LocalDate pDate = LocalDate.of(createdDate.getYear(), createdDate.getMonthValue(), createdDate.getDayOfMonth());
		LocalDate now = LocalDate.now();
		Period diff = Period.between(pDate, now);
		if (diff.getYears() >= 2) {
			LOGGER.info("Customer is over 2 years.");
			return true;
		} else {
			LOGGER.info("Customer is under 2 years. Not eligible for discount");
			return false;
		}
	}

}
