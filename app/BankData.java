package app;

import java.io.Serializable;
import java.util.ArrayList;

public class BankData implements Serializable{
	
	private ArrayList<AccountOwner> users = new ArrayList<AccountOwner>();
	
	public ArrayList<AccountOwner> getUsers() {
		return this.users;
	}
	
	public  void addUser(AccountOwner owner) {
		if (! users.contains(owner)) 
			users.add(owner);
		else {
			throw new IllegalArgumentException("You can not have multiple users.");
		}
	}
	
	public void deleteUser(AccountOwner owner) {
		if (! users.contains(owner)) {
			throw new IllegalArgumentException("You can not delete a non-existent user. How did you log in????");
		}
		for (Account account: owner.accounts) {
			owner.deleteAccount(account);
		}
		users.remove(owner);
	}
	
	public  boolean findUserByName(String name) {
		if (users.isEmpty()) {
			return false;
		}
		for (int i = 0; i == users.size(); i ++) {
			if (users.get(i).getFullName().equals(name))
				return true;
		}
		return false;
	}
	
	public  boolean userExist(String username) {
		for (AccountOwner owner: users) {
			if (owner.getUsername().equals(username))
				return true;
		}
		return false;
	}
	
	public  AccountOwner findUserByUsername(String username) {
		for (AccountOwner owner: users) {
			if (owner.getUsername().equals(username)) 
				return owner;
		}
		return null;
	}
	
	public boolean isPersonal(String type) {
		return (type.equals("personal") || type.equals("Personal"));
	}
	
	public boolean isSavings(String type) {
		return (type.equals("savings") || type.equals("savings"));
	}
	
	public boolean isBSU(String type) {
		return (type.equals("bsu") || type.equals("BSU") || type.equals("Bsu"));
	}
	
	public String toString() {
		String result = "";
		for (AccountOwner owner: users) {
			result += owner.getFullName();
		}
		return result;
	}

}
