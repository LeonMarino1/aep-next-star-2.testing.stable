package com.aep.testing.aepnextstar2.testing.stable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;
import com.aep.testing.aepnextstar2.testing.stable.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("logging-test")
@RunWith(SpringRunner.class)
public class ApplicationTests {

	/*
	 * @Test void contextLoads() { }
	 */
	
	/*
	 * @Test void dummyTest() {
	 * 
	 * assertTrue(true); }
	 */
	
	@LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    HttpHeaders headers = new HttpHeaders();
    
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);
    
    @Autowired
    private BankAccountService service;
	
	
    @Test
    public void testWithdraw() throws Exception {
    	
    	
    	List<BankAccount> accounts = service.getAll();
    	
    	assertNotNull(accounts);
    	
    	ResponseEntity<String> response = null;
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    	BankAccount responseAccount = null;
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	for(BankAccount account: accounts) {
    		double actual = account.getBalance();
    		double withdrawAmount = randomAmount();
    		account.withdraw(withdrawAmount);
    		
    		response = restTemplate.exchange(
      	          createURLWithPort("/withdraw/"+account.getId()+"/"+withdrawAmount), HttpMethod.POST, entity, String.class);
    		
    		String responseBody = response.getBody();
    		
    		responseAccount = mapper.readValue(responseBody, BankAccount.class);
    		
    		assertTrue(actual-withdrawAmount == account.getBalance());
    		assertTrue(actual-withdrawAmount == responseAccount.getBalance());
    	}
    	
    	
    	
    }
    
    @Test
    public void testDeposit() throws Exception {
    	
    	
    	List<BankAccount> accounts = service.getAll();
    	
    	assertNotNull(accounts);
    	
    	ResponseEntity<String> response = null;
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    	BankAccount responseAccount = null;
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	for(BankAccount account: accounts) {
    		double actual = account.getBalance();
    		double depositAmount = randomAmount();
    		account.withdraw(depositAmount);
    		
    		response = restTemplate.exchange(
      	          createURLWithPort("/deposit/"+depositAmount+"/"+account.getId()), HttpMethod.POST, entity, String.class);
    		
    		String responseBody = response.getBody();
    		
    		responseAccount = mapper.readValue(responseBody, BankAccount.class);
    		
    		assertTrue(actual+depositAmount == account.getBalance());
    		assertTrue(actual+depositAmount == responseAccount.getBalance());
    	}
    	
    	
    	
    }
    
    private double randomAmount() {
    	
    	double amount = Math.random()*30+1;
    	
    	return amount;
    	
    }
    
    
    
	@Test
    public void testCreateAccounts() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/createAccounts"), HttpMethod.POST, entity, String.class);
       
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        
        LOG.info("Inicio pruebas AEP NextStar 2");
        LOG.debug("Inicio pruebas AEP NextStar 2");
        LOG.info(ApplicationTests.class.getCanonicalName());
        LOG.debug(ApplicationTests.class.getCanonicalName());
        LOG.debug("actual: ");
        LOG.debug(actual);
        LOG.info("actual: ");
        LOG.info(actual);
        assertTrue(actual.contains("/createAccounts"));
       
    } 
	
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
	
	
	
	}


