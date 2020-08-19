package com.aep.testing.aepnextstar2.testing.stable.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;
import com.aep.testing.aepnextstar2.testing.stable.service.BankAccountService;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountService service;
	
	
	@PostMapping("/deposit/{amount}/{id}")
	public BankAccount deposit(@PathVariable Double amount,@PathVariable int id) {
		return null;
	}
	
	@PostMapping("/withdraw/{amount}/{id}")
	public BankAccount withdraw(@PathVariable double amount,@PathVariable int id) {
		return null;
	}
	
	@PostMapping("/createAccounts")
	public ResponseEntity<Void> createAccounts(){
		service.createAccounts();
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	

}
