package com.aep.testing.aepnextstar2.testing.stable.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.aep.testing.aepnextstar2.testing.stable.exception.BankAccountException;

public class BankAccountExceptionTest {
	
	@Test
	void bankAccountExceptionTest() {
		BankAccountException e = new BankAccountException("The account doesn't exist");
		assertEquals("The account doesn't exist",e.getMessage());
	}

}
