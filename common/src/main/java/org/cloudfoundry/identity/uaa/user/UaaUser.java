/*
 * Cloud Foundry 2012.02.03 Beta
 * Copyright (c) [2009-2012] VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product includes a number of subcomponents with
 * separate copyright notices and license terms. Your use of these
 * subcomponents is subject to the terms and conditions of the
 * subcomponent's license, as noted in the LICENSE file.
 */
package org.cloudfoundry.identity.uaa.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * User data for authentication against UAA's internal authentication provider.
 *
 * @author Luke Taylor
 * @author Dave Syer
 * @author Joel D'sa
 */
public class UaaUser {

	private final String id;

	private final String username;

	private final String password;

	private final String email;

	private final String givenName;

	private final String familyName;

	private final Date created;

	private final Date modified;
	
	private final Date dob;
	
	private final String billing_address1;

	private final String city;

    private final String state;
	
	private final String country;

	private final List<? extends GrantedAuthority> authorities;

	public UaaUser(String username, String password, String email, String givenName, String familyName, Date dob, String billing_address, String city , String state, String country) {
		this("NaN", username, password, email, UaaAuthority.USER_AUTHORITIES, givenName, familyName,
				new Date(), new Date(), "NaN","NaN","NaN","NaN", new Date());
	}

	public UaaUser(String id, String username, String password, String email, List<? extends GrantedAuthority> authorities,
			String givenName, String familyName, Date created, Date modified,  String billing_address1, String city, String state, String country, Date dob) {
		Assert.hasText(username, "Username cannot be empty");
		Assert.hasText(id, "Id cannot be null");
		Assert.hasText(email, "Email is required");
		this.id = id;
		this.username = username;
		this.password = password;
		// TODO: Canonicalize email?
		this.email = email;
		this.familyName = familyName;
		this.givenName = givenName;
		this.created = created;
		this.modified = modified;
		this.authorities = authorities;
		this.billing_address1 = billing_address1;
		this.city = city;
		this.state = state;
		this.country = country;
		this.dob = dob;
	}
	


	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public List<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public UaaUser id(String id) {
		if (!"NaN".equals(this.id)) {
			throw new IllegalStateException("Id already set");
		}
		return new UaaUser(id, username, password, email, authorities, givenName, familyName, created, modified, state, billing_address1, city, country, dob);
	}

	public UaaUser authorities(Collection<? extends GrantedAuthority> authorities) {
		ArrayList<GrantedAuthority> values = new ArrayList<GrantedAuthority>(authorities);
		for (int i = 0; i < values.size(); i++) {
			GrantedAuthority authority = values.get(i);
			values.set(i, UaaAuthority.authority(authority.toString()));
		}
		if (!values.contains(UaaAuthority.UAA_USER)) {
			values.add(UaaAuthority.UAA_USER);
		}
		UaaUser user = new UaaUser(id, username, password, email, values, givenName, familyName, created, modified, billing_address1, city, country, state, dob);
		return user;
	}

	@Override
	public String toString() {
		return "[UaaUser {id=" + id + ", username=" + username + ", email=" + email + ", givenName=" + givenName
				+ ", familyName=" + familyName + "}]";
	}

	public Date getModified() {
		return modified;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @return the billing_address1
	 */
	public String getBilling_address1() {
		return billing_address1;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

}
