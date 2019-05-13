package com.retail.store.discount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.model.RetailStoreDiscountResponse;
import com.retail.store.discount.model.User;
import com.retail.store.discount.service.RetailStoreDiscountService;

/**
 * 
 * @author Jan Vincent Ronquillo
 *
 */
@RestController
public class RetailStoreDiscountController {

	@Autowired
	private RetailStoreDiscountService service;

	@RequestMapping("/")
	public String checkService() {
		return "Service is up!";
	}

	/**
	 * This method will return the list of sales
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sale")
	public ResponseEntity<Object> getUsers() {
		return new ResponseEntity<>(service.retrieveSales().values(), HttpStatus.OK);
	}

	/**
	 * This method add new sale and return the total bill of the user
	 * 
	 * @param user
	 * @return
	 * @throws RetailStoreDiscountException 
	 */
	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	@ResponseBody
	public RetailStoreDiscountResponse addSale(@RequestBody User user) throws RetailStoreDiscountException {
		if (user == null) {
			throw new RetailStoreDiscountException("RequestBody cannot be null");
		}
		return service.addSale(user);
	}

}
