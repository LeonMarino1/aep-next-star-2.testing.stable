package com.aep.testing.aepnextstar2.testing.stable.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;
import com.aep.testing.aepnextstar2.testing.stable.service.BankAccountService;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Matchers.any;

public class BankAccountServiceTest {
	
	private List<BankAccount> accounts = null;
	private BankAccountService service = null;
	@Before
	void setUp() {
		accounts = new ArrayList<>();
		BankAccount account = new BankAccount("Blanca Acosta",210.0d);
		accounts.add(account);
		account = new BankAccount("Leonardo Targo", 167.0d);
		accounts.add(account);
		account = new BankAccount("Teresa Riva", 65.0d);
		accounts.add(account);
		service = new BankAccountService();
	}
	
	@Test
	void saveAllTest() {
		
		doAnswer((arguments) -> {
	        System.out.println("Inside doAnswer block");
	        assertEquals(accounts, arguments.getArgumentAt(0, List.class));
	        return null;
	    }).when(service).saveAll(any(List.class));
		
		service.saveAll(accounts);
	}
	
	@Test
	void bankAccountServiceTest() {
		
		service.saveAll(accounts);
		
		List<BankAccount> savedAccounts = service.getAll();
		
		assertEquals(accounts,savedAccounts);
		
		savedAccounts = service.createAccounts();
		
		for(BankAccount account: savedAccounts) {
			assertTrue(valid(account));
		}
		
		double formerBalance = savedAccounts.get(0).getBalance();
		
		Optional<BankAccount> account = service.deposit(savedAccounts.get(0).getId(), 20.0d);
		
		assertTrue(account.isPresent());
		
		assertEquals(formerBalance+20.0d,account.get().getBalance());
		
		formerBalance = savedAccounts.get(1).getBalance();
		
		account = service.withdraw(savedAccounts.get(0).getId(), 50.0d);
		
		assertTrue(account.isPresent());
		
		assertEquals(formerBalance-50.0d, account.get().getBalance());
		
		
	}
	
	private boolean valid(BankAccount account) {
		return account.getId()==1 || account.getId()==2 || account.getId()==3 || account.getId()==4;
	}

}
