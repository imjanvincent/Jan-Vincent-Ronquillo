package com.retail.store.discount.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Jan Vincent
 *
 */
@Component
public class RetailStoreDiscountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5536348093516877935L;

	private int id;
	private String userType;
	// percentage per user type
	private int discountPercentage;
	// total amount purchase by user
	private float totalAmountBeforeDiscount;
	// amount deducted from the discountPercentage
	private float discountedAmount;
	// amountAfterDiscount = nonGroceryAmount - discountPercentage
	private float amountAfterDiscount;
	// total amount for grocery items(no discount)
	private float groceryItemAmount;
	// total amount for non-grocery items
	private float nonGroceryAmount;
	// totalAmount = amountAfterDiscount + nonGroceryAmount
	private float totalAmount;
	// grandTotal = totalAmount - $5 per 100 bill
	private float grandTotal;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the discountPercentage
	 */
	public int getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * @param discountPercentage the discountPercentage to set
	 */
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	/**
	 * @return the totalAmountBeforeDiscount
	 */
	public float getTotalAmountBeforeDiscount() {
		return totalAmountBeforeDiscount;
	}

	/**
	 * @param totalAmountBeforeDiscount the totalAmountBeforeDiscount to set
	 */
	public void setTotalAmountBeforeDiscount(float totalAmountBeforeDiscount) {
		this.totalAmountBeforeDiscount = totalAmountBeforeDiscount;
	}

	/**
	 * @return the discountedAmount
	 */
	public float getDiscountedAmount() {
		return discountedAmount;
	}

	/**
	 * @param discountedAmount the discountedAmount to set
	 */
	public void setDiscountedAmount(float discountedAmount) {
		this.discountedAmount = discountedAmount;
	}

	/**
	 * @return the amountAfterDiscount
	 */
	public float getAmountAfterDiscount() {
		return amountAfterDiscount;
	}

	/**
	 * @param amountAfterDiscount the amountAfterDiscount to set
	 */
	public void setAmountAfterDiscount(float amountAfterDiscount) {
		this.amountAfterDiscount = amountAfterDiscount;
	}

	/**
	 * @return the groceryItemAmount
	 */
	public float getGroceryItemAmount() {
		return groceryItemAmount;
	}

	/**
	 * @param groceryItemAmount the groceryItemAmount to set
	 */
	public void setGroceryItemAmount(float groceryItemAmount) {
		this.groceryItemAmount = groceryItemAmount;
	}

	/**
	 * @return the nonGroceryAmount
	 */
	public float getNonGroceryAmount() {
		return nonGroceryAmount;
	}

	/**
	 * @param nonGroceryAmount the nonGroceryAmount to set
	 */
	public void setNonGroceryAmount(float nonGroceryAmount) {
		this.nonGroceryAmount = nonGroceryAmount;
	}

	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the grandTotal
	 */
	public float getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RetailStoreDiscountResponse [id=" + id + ", userType=" + userType + ", discountPercentage="
				+ discountPercentage + ", totalAmountBeforeDiscount=" + totalAmountBeforeDiscount
				+ ", discountedAmount=" + discountedAmount + ", amountAfterDiscount=" + amountAfterDiscount
				+ ", groceryItemAmount=" + groceryItemAmount + ", nonGroceryAmount=" + nonGroceryAmount
				+ ", totalAmount=" + totalAmount + ", grandTotal=" + grandTotal + "]";
	}

}
