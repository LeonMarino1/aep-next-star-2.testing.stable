package com.aep.testing.aepnextstar2.testing.stable.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;

public class BankAccountTest {
	
	
	private BankAccount account = null;
	
	
	@Test
	void bankAccountTest() {
		
		account = new BankAccount("Ruben Carmona",35.0d);
		
		assertEquals("Ruben Carmona", account.getHolder());
		assertEquals(35.0d,account.getBalance());
		
		account = null;
		
		account = new BankAccount();
		assertNotNull(account);
		
		account.setBalance(40.00d);
		
		assertEquals(40.0d, account.getBalance());
		
		account.withdraw(5.0d);
		
		assertEquals(35.0d, account.getBalance());
		
		account.deposit(5.0d);
		
		assertEquals(40.0d, account.getBalance());
		
		account.setHolder("Vera Gomez");
		
		assertEquals("Vera Gomez",account.getHolder());
		
		account.setId(10);
		
		assertEquals(10, account.getId());
	}

}
