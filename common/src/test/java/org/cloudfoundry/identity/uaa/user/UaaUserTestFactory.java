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

import java.util.Date;

/**
 * @author Dave Syer
 * 
 */
public class UaaUserTestFactory {

	public static UaaUser getUser(String id, String name, String email, String givenName, String familyName,String billing_address1, String city, String state, String country) {
		return new UaaUser(id, name, "", email, UaaAuthority.USER_AUTHORITIES, givenName, familyName, new Date(),
				new Date(), billing_address1, city, state, country, null);
	}

	public static UaaUser getAdminUser(String id, String name, String email, String givenName, String familyName,String billing_address1, String city, String state, String country) {
		return new UaaUser(id, name, "", email, UaaAuthority.ADMIN_AUTHORITIES, givenName, familyName, new Date(),
				new Date(), billing_address1, city, state, country, null);
	}

}
