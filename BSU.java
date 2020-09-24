package app;

public class BSU implements Account{
	
	private double balance = 0;
	private double interestRate = 3.50;
	private int withdrawalCount = 0;
	private int withdrawalLimit = 3;
	private AccountOwner owner;
	private String name;
	
	public BSU(String name, AccountOwner owner) {
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
			throw new IllegalArgumentException("You cannot withdraw this amount!");
		}
		if (! this.withdrawalsLeft())
			throw new IllegalStateException("You do not have any withdrawals left!");
		this.balance -= amount;
		this.withdrawalCount += 1;
	}
	
	private boolean withdrawalsLeft() {
		if (this.withdrawalCount >= this.withdrawalLimit) {
			return false;
		}
		return true;
	}

	@Override
	public void transfer(Account account, double amount) {
		if (account.equals(this) || amount > balance || !(this.withdrawalsLeft()) || !(account.getOwner().equals(this.getOwner()))) {
			throw new IllegalArgumentException("Illegal operation!");
		}
		this.withdraw(amount);
		account.deposit(amount);
		this.withdrawalCount += 1;
	}

	@Override
	public AccountOwner getOwner() {
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
	
	public int getWithdrawalCount() {
		return this.withdrawalCount;
	}
	
	public int getWithdrawalLimit() {
		return this.getWithdrawalLimit();
	}
	
	public String toString() {
		return "BSU account: " + this.name;
	}

}
