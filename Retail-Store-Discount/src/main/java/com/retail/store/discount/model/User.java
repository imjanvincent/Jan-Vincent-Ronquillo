package com.retail.store.discount.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Jan Vincent Ronquillo
 *
 */
@Component
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7416479954497312410L;
	private int id;
	private UserType userType;
	private LocalDate createdDate;
	private List<Item> items;

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
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
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
		return "User [id=" + id + ", userType=" + userType + ", createdDate=" + createdDate + ", items=" + items + "]";
	}

}
