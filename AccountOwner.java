package app;

import java.util.List;
import java.util.ArrayList;

public class AccountOwner extends Person{
	
	public AccountOwner(String givenName, String familyName, int age) {
		super(givenName, familyName, age);
		// TODO Auto-generated constructor stub
	}

	private Person owner; 
	ArrayList<Account> accounts = new ArrayList<Account>();
	private String username;
	private String password;
	
	
	public ArrayList getAccounts() {
		return this.accounts;
	}
	
	public boolean isOwner(Account account) {
		return (this.accounts.contains(account));
	}
	
	public void addAccount(Account account) {
		if (account.equals(null)) {
			throw new IllegalArgumentException ("Cant add nothing");
		}
		if (this.isOwner(account)) {
			throw new IllegalArgumentException ("Cant duplicate an account!");
		}
		if (!(account.getOwner().equals(null))) {
			throw new IllegalArgumentException("Cant change owner to an account.");
		}
		this.accounts.add(account);
		account.setOwner(this);
	}
	
	public void deleteAccount(Account account)  {
		if (! this.accounts.contains(account)) {
			throw new IllegalArgumentException("You cannot delete an account you do not have!");
		}
		if (!(account.getBalance() == 0) ) {
			throw new IllegalStateException("Empty your account before you delete it!");
		}
		this.accounts.remove(account);
	}
	
	void setUsername(String username) {
		if (username.length() < 2) {
			throw new IllegalArgumentException("Too short username, must be of at least 2 characters!");
		}
		char[]tmpList = username.toCharArray();
		for (char character: tmpList) {
			if (! Character.isLetterOrDigit(character))
				throw new IllegalArgumentException("A username can only contain letters or digits!");	
			this.username = username;
		}
		this.username = username;
	}
	
	void setPassword (String password) {
		if (password.length() < 6) {
			throw new IllegalArgumentException("Too short password, must be of at least 6 characters!");
		}
		char[]tmpList = password.toCharArray();
		for (char character: tmpList) {
			if (! Character.isLetterOrDigit(character))
				throw new IllegalArgumentException("A password can only contain letters or digits!");	
			this.password = password;
		}
		this.password = password;
	}
	
	String getPassword() {
		return this.password;
	}
	
	String getUsername() {
		return this.username;
	}
	
	public Person getOwner() {
		if (this.owner.equals(null))
			throw new NullPointerException("This account do not have an owner!");
		return this.owner;
	}
	
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	public String printAccounts() {
		if (this.accounts.isEmpty())
			return "No accounts.";
		String print = "Your accounts:\n";
		for (Account a: accounts) {
			print += a.toString()+"\n";
			
		}
		return print;
	}

}
