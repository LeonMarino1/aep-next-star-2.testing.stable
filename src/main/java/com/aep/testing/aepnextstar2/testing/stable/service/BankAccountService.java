package com.aep.testing.aepnextstar2.testing.stable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;

import org.springframework.stereotype.Component;



@Component
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository accountRepository;
	
	
	public void saveAll(List<BankAccount> toSave) {
		
		accountRepository.saveAll(toSave);
	}
	
	public List<BankAccount> getAll() {
		
		List<BankAccount> found = new ArrayList<>();
		Iterable<BankAccount> accounts = accountRepository.findAll();
		
		accounts.forEach(found::add);
		
		return found;
		
	}
	
	public List<BankAccount> createAccounts(){
		
		List<BankAccount> testAccounts = new ArrayList<>();
		List<BankAccount> createdAccounts = new ArrayList<>();
		
		testAccounts.add(new BankAccount("Manuel Ramirez",300.0d));
		testAccounts.add(new BankAccount("María de la Luz Cruz Farías",150.0d));
		testAccounts.add(new BankAccount("Mercedes Pérez Andrade", 210.0d));
		testAccounts.add(new BankAccount("Benito Hernández Valverde", 320.0d));
		
		Iterable<BankAccount> itrAccounts = accountRepository.saveAll(testAccounts);
		
		itrAccounts.forEach(createdAccounts::add);
		
		
		return createdAccounts;
	}
	
	public Optional<BankAccount> withdraw(int id, double amount) {
		
		Optional<BankAccount> optAccount = accountRepository.findById(id);
		
		BankAccount account = null;
		if(optAccount.isPresent()) {
			account = optAccount.get();
			account.withdraw(amount);
			accountRepository.save(account);
			optAccount = accountRepository.findById(id);
		}
		
		return optAccount;
	}
	
public Optional<BankAccount> deposit(int id, double amount) {
		
		Optional<BankAccount> optAccount = accountRepository.findById(id);
		
		BankAccount account = null;
		if(optAccount.isPresent()) {
			account = optAccount.get();
			account.deposit(amount);
			accountRepository.save(account);
			optAccount = accountRepository.findById(id);
		}
		
		return optAccount;
	}

}
