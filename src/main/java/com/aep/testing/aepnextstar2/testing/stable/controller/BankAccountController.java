package com.aep.testing.aepnextstar2.testing.stable.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aep.testing.aepnextstar2.testing.stable.exception.BankAccountException;
import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;
import com.aep.testing.aepnextstar2.testing.stable.service.BankAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountService service;
	
	@Operation(summary="Se despliegan todas las cuentas existentes en nuestra BD")
	@ApiResponse(responseCode = "200", description="La operación fue exitosa, se obtuvieron todas las cuentas existentes.",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@ApiResponse(responseCode = "500", description="No fue posible obtener las cuentas existentes se presentó un problema con el servicio.",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@GetMapping("/findAll")
	public List<BankAccount> findAll(){
		return service.getAll();
	}
	
	@Operation(summary="Se deposita la cantidad para la cuenta con correspondiente id")
	@ApiResponse(responseCode = "200", description="La operación fue exitosa, fue posible depositar la cantidad para la cuenta solicitada",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@ApiResponse(responseCode = "400", description="No fue posible depositar la cantidad en la cuenta debido a que una cuenta con el id proporcionado no existe",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	
	@PostMapping("/deposit/{amount}/{id}")
	public BankAccount deposit(@PathVariable Double amount,@PathVariable int id) throws BankAccountException {
		Optional<BankAccount> result = service.deposit(id, amount);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new BankAccountException("Account with id: " + id + " not found.");
		}
	}
	
	@Operation(summary="Se retira la cantidad para la cuenta con correspondiente id")
	@ApiResponse(responseCode = "200", description="La operación fue exitosa, fue posible retirar la cantidad para la cuenta solicitada",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@ApiResponse(responseCode = "400", description="No fue posible retirar la cantidad en la cuenta debido a que una cuenta con el id proporcionado no existe",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@PostMapping("/withdraw/{amount}/{id}")
	public BankAccount withdraw(@PathVariable double amount,@PathVariable int id) throws BankAccountException {
		Optional<BankAccount> result = service.withdraw(id, amount);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new BankAccountException("Account with id: " + id + " not found.");
		}
	}
	
	@Operation(summary="Se crean cuatro cuentas de prueba con montos variables")
	@ApiResponse(responseCode = "200", description="La operación fue exitosa, fue posible crear las cuentas bancarias de prueba.",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@ApiResponse(responseCode = "500", description="No fue posible crear las cuentas de prueba se presentó un problema con el servicio.",
	content= { @Content(mediaType= "application/json",
	schema=@Schema(implementation=BankAccount.class))
	})
	@PostMapping("/createAccounts")
	public ResponseEntity<Void> createAccounts(){
		service.createAccounts();
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	

}
