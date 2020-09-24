package app;

import java.io.Serializable;

public interface Account extends Serializable{
	
	public double getBalance();
	
	public void deposit(double amount);
	
	public void withdraw(double amount);
	
	public void transfer(Account account, double amount);
	
	public Person getOwner();
	
	public void setOwner(AccountOwner owner);
	
	public double getInterestRate();
	
	public void setName(String name);
	
	public String getName();
	
	public String toString();

}