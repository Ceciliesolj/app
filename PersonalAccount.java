package app;

public class PersonalAccount implements Account{
	
	private double balance = 0;
	private double interestRate = 0.20;
	private AccountOwner owner;
	private String name;
	
	public PersonalAccount(String name, AccountOwner owner) {
		this.setOwner(owner);
		this.setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	@Override
	public double getBalance() {
		return this.balance;
	}

	@Override
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("You cannot deposit a negative amount!");
		}
		this.balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if (amount <= 0 || amount > this.balance) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount or more money that you own!");
		}
		this.balance -= amount;
	}

	@Override
	public void transfer(Account account, double amount) {
		if (account.equals(this) || amount > balance  || !(account.getOwner().equals(this.getOwner()))) {
			throw new IllegalArgumentException("Illegal operation!");
		}
		this.withdraw(amount);
		account.deposit(amount);
	}

	@Override
	public Person getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(AccountOwner owner) {
		this.owner = owner;
	}


	@Override
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public String toString() {
		return "Personal account: " + this.name;
	}

}
