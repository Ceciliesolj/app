package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class BankAppController {
	
	private Account currentAccount;
	private AccountOwner currentUser;
	private BankData data = new BankData();
	private SaveData save = new SaveData();
	
	@FXML
	private Label welcome;
	@FXML
	private TextField givenName;
	@FXML
	private TextField surName;
	@FXML
	private TextField age;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private TextField usernameMake;
	@FXML
	private TextField passwordMake;
	@FXML
	private TextField passwordConfirm;
	@FXML
	private Label haveAccountInfo;
	@FXML
	private Button signUp;
	@FXML
	private Label userMade;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button signin;
	@FXML
	private Label signedIn;
	@FXML
	private Label makeBankAccountInfo;
	@FXML
	private TextField makeAccountName;
	@FXML
	private TextField makeAccountType;
	@FXML
	private Button makeBankAccount;
	@FXML
	private Label wasBankAccountMade;
	@FXML
	private Label accountTypes;
	@FXML
	private Label yourAccounts;
	@FXML
	private Label accessAccountInfo;
	@FXML
	private TextField accessAccountName;
	@FXML
	private Button access;
	@FXML
	private Label nowManaging;
	@FXML
	private Label accessed;
	@FXML
	private TextField amount;
	@FXML
	private TextField toAccount;
	@FXML
	private TextField reciever;
	@FXML
	private Button pay;
	@FXML
	private Button deposit;
	@FXML
	private Button withdraw;
	@FXML
	private Button transfer;
	@FXML
	private Label status;
	@FXML
	private Button delete;
	@FXML
	private Label accountAction;
	@FXML
	private Label ifDelete;
	@FXML 
	private Button deleteUser;
	@FXML
	private Button signOut;
	
	
	@FXML
	private void initialize() {
		welcome.setVisible(true);
		givenName.setVisible(true);
		surName.setVisible(true);
		age.setVisible(true);
		phone.setVisible(true);
		email.setVisible(true);
		usernameMake.setVisible(true);
		passwordMake.setVisible(true);
		passwordConfirm.setVisible(true);
		haveAccountInfo.setVisible(true);
		signUp.setVisible(true);
		password.setVisible(true);
		username.setVisible(true);
		signin.setVisible(true);
		userMade.setText("");
		userMade.setVisible(true);
		signedIn.setText("You are not signed in.");
		accountTypes.setText("You can make these types of accounts: \nType: personal with interestrate: 0.3\nType: Savings with interestrate 1.1 and 7 withdrawals.\nType: BSU with interestRate 3.5 and 3 withdrawals.");
		makeBankAccountInfo.setVisible(false);
		makeAccountName.setVisible(false);
		makeAccountType.setVisible(false);
		makeBankAccount.setVisible(false);
		wasBankAccountMade.setVisible(false);
		accountTypes.setVisible(false);
		yourAccounts.setVisible(false);
		accessAccountInfo.setVisible(false);
		accessAccountName.setVisible(false);
		access.setVisible(false);
		nowManaging.setVisible(false);
		nowManaging.setText("");
		accessed.setVisible(false);
		amount.setVisible(false);
		toAccount.setVisible(false);
		reciever.setVisible(false);
		deposit.setVisible(false);
		withdraw.setVisible(false);
		transfer.setVisible(false);
		pay.setVisible(false);
		delete.setVisible(false);
		status.setVisible(false);
		accountAction.setVisible(false);
		ifDelete.setVisible(false);
		ifDelete.setText("Delete your useraccount?\nAll accounts must be empty.");
		deleteUser.setVisible(false);
		signOut.setVisible(false);	
		boolean s = false;
		try {
			this.data = (BankData) save.readObject();
			s = true;
		} 	catch (Exception e) {
		    System.out.println(e.getMessage());
		}   finally {
				if (! s)
					this.data = new BankData();
		}
	}
	
	private void checkFieldsMakeUserAccount() {
		if (givenName.getText().isBlank())
			throw new IllegalArgumentException("Must fill in givenname.");
		if (surName.getText().isBlank())
			throw new IllegalArgumentException("Must fill in surname.");
		if (age.getText().isBlank())
			throw new IllegalArgumentException("Must fill in age.");
		if (email.getText().isBlank())
			throw new IllegalArgumentException("Must fill in email.");
		if (phone.getText().isBlank())
			throw new IllegalArgumentException("Must fill in phonenumer.");
		if (usernameMake.getText().isBlank())
			throw new IllegalArgumentException("Must fill in username.");
		if (passwordMake.getText().isBlank())
			throw new IllegalArgumentException("Must make a password.");
		if (passwordConfirm.getText().isBlank())
			throw new IllegalArgumentException("Must confirm password.");
	}
	
	public void makeUserAccount() {
		try {
			String typedGivenName = givenName.getText();
			String typedSurname = surName.getText();
			int typedAge = Integer.parseInt(age.getText());
			String typedEmail = email.getText();
			String typedPhone = phone.getText();
			String typedUsername = usernameMake.getText();
			String typedPassword1 = passwordMake.getText();
			String typedPassword2 = passwordConfirm.getText();
			checkFieldsMakeUserAccount();
			if (! (typedPassword1.equals(typedPassword2))) {
				userMade.setText("Passwords must match!");
			}
			else if (data.userExist(typedUsername)) {
				userMade.setText("There is already a user with that username!");
			}
			else if (data.findUserByName(typedGivenName + " " + typedSurname)) {
				userMade.setText("You have already made an account!");
			}
			else {
				AccountOwner p = new AccountOwner(typedGivenName, typedSurname, typedAge);
				//p.getOwner().setEmail(typedEmail);
				//p.getOwner().setPhone(typedPhone);
				p.setUsername(typedUsername);
				p.setPassword(typedPassword1);
				data.addUser(p);
				userMade.setText("Made a useraccount for " + p.getFullName() + ". You can now sign in. ");
				givenName.clear();
				surName.clear();
				age.clear();
				email.clear();
				phone.clear();
				usernameMake.clear();
				passwordMake.clear();
				passwordConfirm.clear();
			}
		} catch (Exception e){
			userMade.setText(e.getMessage());
		}
	}
	
	private void checkFieldsLogIn() {
		if (username.getText().isBlank())
			throw new IllegalArgumentException("Must fill in username.");
		if (password.getText().isBlank())
			throw new IllegalArgumentException("Must fill in password.");
	}
	
	public void logIn() {
		try {
			String typedUsername = username.getText();
			String typedPassword = password.getText();
			checkFieldsLogIn();
			if (! data.userExist(typedUsername)) {
				signedIn.setText("No such user exist");
			}
			else {
				AccountOwner owner = data.findUserByUsername(typedUsername);
				this.currentUser = owner;
				if (owner.getPassword().equals(typedPassword)) {
					signedIn.setText("You are signed in as: " + owner.getFullName());
					this.currentUser = owner;
					yourAccounts.setText(currentUser.printAccounts());
					makeBankAccountInfo.setVisible(true);
					makeAccountName.setVisible(true);
					makeAccountType.setVisible(true);
					makeBankAccount.setVisible(true);
					wasBankAccountMade.setVisible(true);
					accountTypes.setVisible(true);
					yourAccounts.setVisible(true);
					accessAccountInfo.setVisible(true);
					accessAccountName.setVisible(true);
					access.setVisible(true);
					accessed.setVisible(true);
					amount.setDisable(true);
					amount.setVisible(true);
					toAccount.setDisable(true);
					toAccount.setVisible(true);
					reciever.setVisible(true);
					reciever.setDisable(true);
					deposit.setDisable(true);
					deposit.setVisible(true);
					withdraw.setDisable(true);
					withdraw.setVisible(true);
					transfer.setDisable(true);
					transfer.setVisible(true);
					pay.setVisible(true);
					pay.setDisable(true);
					ifDelete.setVisible(true);
					delete.setVisible(true);
					delete.setDisable(true);
					deleteUser.setVisible(true);
					signOut.setVisible(true);
					welcome.setVisible(false);
					givenName.setVisible(false);
					surName.setVisible(false);
					age.setVisible(false);
					phone.setVisible(false);
					email.setVisible(false);
					usernameMake.setVisible(false);
					passwordMake.setVisible(false);
					passwordConfirm.setVisible(false);
					signUp.setVisible(false);
					haveAccountInfo.setVisible(false);
					password.setVisible(false);
					username.setVisible(false);
					userMade.setVisible(false);
					signin.setVisible(false);
					username.clear();
					password.clear();
				}
				else 
					signedIn.setText("Wrong password!");
			}
			} catch (Exception e) {
				signedIn.setText(e.getMessage());
			}	
	}
	
	private void checkFieldsMakeBankAccount() {
		if (makeAccountName.getText().isBlank())
			throw new IllegalArgumentException("Must fill in account-name");
		if (makeAccountType.getText().isBlank())
			throw new IllegalArgumentException("Must specify a type!");
	}
	
	public void makeBankAccount () {
		try {
			String typedName = makeAccountName.getText();
			String typedType = makeAccountType.getText();
			checkFieldsMakeBankAccount();
			if (! (data.isBSU(typedType) || data.isPersonal(typedType) || data.isSavings(typedType))) {
				wasBankAccountMade.setText("Can not make an account of this type!");
			}
			else if (data.isPersonal(typedType)) {
				Account a = new PersonalAccount(typedName, this.currentUser);
				this.currentUser.getAccounts().add(a);
				wasBankAccountMade.setText("A new personal account: "+ a.getName() + " was made!" );
				yourAccounts.setText(currentUser.printAccounts());
			}
			else if (data.isSavings(typedType)) {
				Account a = new SavingsAccount(typedName, this.currentUser);
				this.currentUser.getAccounts().add(a);
				wasBankAccountMade.setText("A new savings account: "+ a.getName() + " was made!" );
				yourAccounts.setText(currentUser.printAccounts());
			}
			else if (data.isBSU(typedType)) {
				Account a = new BSU(typedName, this.currentUser);
				this.currentUser.getAccounts().add(a);
				wasBankAccountMade.setText("A new BSU account: "+ a.getName() + " was made!" );
				yourAccounts.setText(currentUser.printAccounts());
			}
			makeAccountName.clear();
			makeAccountType.clear();
		} catch (Exception e) {
			wasBankAccountMade.setText(e.getMessage());
		}
	}
	
	private Account findAccountByName(String name) {
		for (Account a: this.currentUser.accounts) {
			if (a.getName().equals(name)) {
				return a;
			}	
		}
		return null;
	}
	
	private void UpdateStatus() {
		status.setText("Balance: "+this.currentAccount.getBalance()+"\nInterestRate: " + this.currentAccount.getInterestRate());
	}
	
//	public void AccessBankAccount () {
//		String typedName = accessAccountName.getText();
//		if (! this.findAccountByName(typedName).equals(null)) {
//			accessed.setText("No account with that name!");
//		}
//		else {
//			this.currentAccount = this.findAccountByName(typedName);
//			accessed.setText("Managing: " + this.currentAccount.toString());
//			accessAccountName.clear();
//			this.UpdateStatus();
//			status.setVisible(true);
//			amount.setDisable(false);
//			toAccount.setDisable(false);
//			reciever.setDisable(false);
//			deposit.setDisable(false);
//			withdraw.setDisable(false);
//			transfer.setDisable(false);
//			pay.setDisable(false);
//			delete.setDisable(false);
//			nowManaging.setVisible(true);
//			nowManaging.setText("You are now managing your account " + this.currentAccount.getName() + ". What do you want to do? ");
//		}
//	}
	
	public void AccessBankAccount() {
		String typedName = accessAccountName.getText();
		try {
			this.currentAccount = this.findAccountByName(typedName);
			accessed.setText("Managing: " + this.currentAccount.toString());
			accessAccountName.clear();
			this.UpdateStatus();
			status.setVisible(true);
			amount.setDisable(false);
			toAccount.setDisable(false);
			reciever.setDisable(false);
			deposit.setDisable(false);
			withdraw.setDisable(false);
			transfer.setDisable(false);
			pay.setDisable(false);
			delete.setDisable(false);
			nowManaging.setVisible(true);
			nowManaging.setText("You are now managing your account " + this.currentAccount.getName() + ". What do you want to do? ");
		} catch (Exception e) {
			accessed.setText(e.getMessage());
		}
	}
	
	private void checkFieldsDepositWithdraw() {
		if (amount.getText().isBlank())
			throw new IllegalArgumentException("Must type an amount.");
	}
	
	public void deposit() {
		try {
			double typedAmount = Double.parseDouble(amount.getText());
			checkFieldsDepositWithdraw();
			this.currentAccount.deposit(typedAmount);
			this.UpdateStatus();
			accountAction.setText("Deposited " + typedAmount + " to " + this.currentAccount.getName());
			accountAction.setVisible(true);
			amount.clear();
		} catch (Exception e) {
			accountAction.setText(e.getMessage());
		}
	}
	
	public void withdraw() {
		try {
			double typedAmount = Double.parseDouble(amount.getText());
			checkFieldsDepositWithdraw();
			this.currentAccount.withdraw(typedAmount);
			this.UpdateStatus();
			accountAction.setText("Withdrew " + typedAmount + " to " + this.currentAccount.getName());
			accountAction.setVisible(true);
			amount.clear();
		} catch (Exception e) {
			accountAction.setText(e.getMessage());
		}
	}
	
	private void checkFieldsTransfer() {
		checkFieldsDepositWithdraw();
		if (toAccount.getText().isBlank())
			throw new IllegalArgumentException("Must specify a recieving account.");
	}
	
	public void transfer() {
		try {
			double typedAmount = Double.parseDouble(amount.getText());
			checkFieldsTransfer();
			String typedName = toAccount.getText();
			if (this.findAccountByName(typedName).equals(null)) {
				status.setText("Cannot transfor to a non-existent account!");
			}
			else {
				this.currentAccount.withdraw(typedAmount);
				this.findAccountByName(typedName).deposit(typedAmount);
				this.UpdateStatus();
				accountAction.setText("Transferred " + typedAmount + " from " + this.currentAccount+ " to " + this.findAccountByName(typedName));
				amount.clear();
				toAccount.clear();
			}
		} catch (Exception e) {
			accountAction.setText(e.getMessage());
		}
	}
	
	private void checkFieldsPay() {
		checkFieldsDepositWithdraw();
		if (reciever.getText().isBlank())
			throw new IllegalArgumentException("Must specify a reciever.");
	}
	
	public void pay() {
		try {
			String typedUsername = reciever.getText();
			checkFieldsPay();
			double typedAmount = Double.parseDouble(amount.getText());
			if (! data.userExist(typedUsername)) {
				accountAction.setText("That user does not exist. Cannot make payment");
			}
			else {
				AccountOwner recieverperson = data.findUserByUsername(typedUsername);
				this.currentAccount.withdraw(typedAmount);
				ArrayList recieverAccounts = recieverperson.getAccounts();
				Account toAccount = (Account) recieverAccounts.get(0);
				toAccount.deposit(typedAmount);
				this.UpdateStatus();
				accountAction.setText("Payed " + typedAmount + "to " + recieverperson.getFullName());
				amount.clear();
				reciever.clear();
			}
		} catch (Exception e) {
			accountAction.setText(e.getMessage());
		}
			
	}
	
	public void deleteAccount() {
		try {
			this.currentUser.deleteAccount(this.currentAccount);
			this.currentAccount = null;
			status.setVisible(false);
			amount.setDisable(true);
			toAccount.setDisable(true);
			reciever.setDisable(true);
			deposit.setDisable(true);
			withdraw.setDisable(true);
			transfer.setDisable(true);
			pay.setDisable(true);
			delete.setDisable(true);
			nowManaging.setVisible(false);
			yourAccounts.setText(currentUser.printAccounts());
		} catch (Exception e) {
			accountAction.setText(e.getMessage());
		}
	}
	
	
	
	public void deleteUser() {
		try {
			data.deleteUser(this.currentUser);
			SignOut();
			userMade.setText("Your account was deleted.");
		} catch (Exception e){
			accountAction.setText(e.getMessage());
		}
	}
	
	public void SignOut() {
	save.writeData(this.data);
	initialize();
	}
}
	


// Hva må gjøres: 
// flere kontotyper?? 
// gå over uttaksgrense mot en utgift?? 
// se om predicate kan brukes for å finne ting i lister