package com.aep.testing.aepnextstar2.testing.stable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_accounts")
public class BankAccount {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="holder", nullable = false)
	private String holder;
	@Column( name="balance", nullable = false)
	private double balance;
	
	
	public BankAccount(String holder, double balance) {
		this.holder = holder;
		this.balance = balance;
	}
	
	public void withdraw(double amount) {
		balance-=amount;
	}
	
	public void deposit(double amount) {
		balance+=amount;
	}
	
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
