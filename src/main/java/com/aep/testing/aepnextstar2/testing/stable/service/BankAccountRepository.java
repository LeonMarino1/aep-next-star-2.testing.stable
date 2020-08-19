package com.aep.testing.aepnextstar2.testing.stable.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aep.testing.aepnextstar2.testing.stable.model.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer>{

}
