package com.bank.ui;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.BankingAccount;
import com.bank.model.CheckingAccount;

import com.bank.model.Customer;
import com.bank.model.SavingAccount;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.services.CustomerImplementation;
import com.bank.services.EmployeeImplementation;
public class UserMenu implements Displayable {
	
	  private CustomerImplementation csi;
	  private EmployeeImplementation esi;
	  Scanner userIn;
	 
	  
	  public UserMenu(CustomerImplementation csi, EmployeeImplementation esi) {
		  this.csi = csi;
		  this.esi = esi;
		  userIn = new Scanner(System.in);
	  }
	  

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//0
	public void manageUserAccountInput() throws UserNotFoundException, InternalErrorException, SQLException,InputMismatchException {

		String email,password;	
		int accountType;
		
		System.out.println("Your banking options:\n 1. New customer \n 2. Existing customer  \n 3. Employee \n 0. End/Exit \n");
	    System.out.println("Please enter to start!");
		this.userIn.nextLine();
		System.out.println("Your choice is: ");
	    try {
	    	
	    	accountType = this.userIn.nextInt();
	    	if(accountType < 0 || accountType > 3) {
	    		System.out.println("Please select the account 0, 1, 2, or 3 ");
	    	}
	    	
	    	else {
	    		if(accountType==0) {
	    			this.userIn.nextLine();
	    			System.exit(0);
	    			
	    		}
	    		if(accountType == 1) {
	    			this.userIn.nextLine();
	    			
	    			manageNewCustomer();
	    		} if(accountType == 2) {
	    			this.userIn.nextLine();
	    			System.out.println("Welcome back to our bank!\n ");
	    			System.out.println("Please enter your email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			User customer = csi.userLogIn(email,password, true);
	    			if(customer.isCustomer()) {
	    				
	    				manageCustomerAccount(customer);
	    			}
	    		}
	    		else if(accountType == 3){
	    			System.out.println("Welcome back to our bank!\n ");
	    			this.userIn.nextLine();
	    			System.out.println("Please enter your employee email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			User employee = esi.userLogIn(email,password, false);
	    			if(!employee.isCustomer()) {
	    				 
	    				manageEmployeeAccount(employee);
	    			}
	    		}
	    	
	    			    			    		
	    	}
	    	
	    } catch(InputMismatchException e) {
	    	 System.out.println("Please select the options based on number!\n");
	    } catch(UserNotFoundException u) {
	    	System.out.println("Wrong user or password! \n");
	    }
	}
  //1
	public void manageNewCustomer() {
	
		while(true) {
			String email,password,firstName,lastName;	
			System.out.println("Choose the best options for you: \n");
			System.out.println("1. Register new account without initial deposit\n"
					+ "2. Register new account with deposit \n"
					+ "3. Return to main menu");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			if(option ==3)
				break;
			User customer = new Customer();
			if(option == 1 || option  == 2) {
				this.userIn.nextLine();
				System.out.println("Please enter your email: ");
				email = this.userIn.nextLine();
				customer.setEmail(email);
				System.out.println("Please enter your password: ");
				password = this.userIn.nextLine();
				customer.setPassword(password);
				System.out.println("Please enter your first name: ");
				firstName = this.userIn.nextLine();
				customer.setFirstName(firstName);
				System.out.println("Please enter your last name: ");
				lastName = this.userIn.nextLine();
				customer.setLastName(lastName);
				
			} 
			
			if(option == 1) {
				
				this.csi.applyNewAccountWithBalance(customer, 0);

			}
			else if (option == 2) {
				System.out.println("Please enter your first deposit: ");
				double balance = this.userIn.nextDouble();
				this.csi.applyNewAccountWithBalance(customer, balance);
			} else {
				this.userIn.nextLine();
				System.out.println("Please enter option 1 or 2! \n");
				System.out.println("Would you like to try again! \n "
						+ "Type 'y' to continue 'n' to exit this menu:\n ");
				String type = this.userIn.nextLine();
				if(type.equals("n")) {
					break;
				}

			}
		}
		
		
		System.out.println("Please press enter to confirm to back to the main menu!");
		this.userIn.nextLine();
	}
	
	
	
	//3
	public void manageEmployeeAccount(User employee) {
		   while(true) {
			   System.out.println("Please choose the option below!\n");
			   System.out.println("1. View pending customer account\n"
			   		+ "2. View a customer's bank account\n"
			   		+ "3. View a log of all transactions\n"
			   		+ "4. Return to the main menu\n");
			  
				System.out.println("Your choice is: ");
				int option = this.userIn.nextInt();
			   if(option == 4)
				   break;
			   switch(option) {
			   		case 1:
			   			viewPendingAccount();
			   			break;
			   		case 2:
			   			viewCustomerBankAccount();
			   			break;
			   		case 3:
			   			break;
			   		default: {
			   		 this.userIn.nextLine();
						System.out.println("Please enter option 1, 2, or 3 ! \n");
			   			break;
			   		}
			   			
			   
			   }
			   
			   
			   
				System.out.println("Would you like to come to the main menu! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userIn.nextInt();
				if(type == 1) {
					break;
				}
		   }
		   
	}
	
	
	//3.1
	public void viewPendingAccount() {
		while(true) {
			 this.userIn.nextLine();
			 System.out.println("This is the list of pending customers:\n");
			 List<User> list=null;
			 try {
				 list = esi.viewListPendingUser();
				 if(list != null) {
					 System.out.println(list); 
				 }
				 else {
					 System.out.println("There is no pending users!\n");
				 }
				
			} catch (InternalErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(list!=null) {
				 
				 System.out.println("Please enter the customer id!\n");
					int customerId = this.userIn.nextInt();
					System.out.println("Which option do you choose:\n"
							+ "1. Accept\n"
							+ "2. Reject\n");
					int option = this.userIn.nextInt();
					User customer=null;
					if(option == 1) {
						
						for(User u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(esi.approveCustomer(customer))
						   System.out.println("Customer is accepted!");
						else {
							System.out.println("There is something wrong");
						}
					}
					else if(option ==2) {
						for(User u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(esi.rejectCustomer(customer)) {
							System.out.println("Customer is rejected!");
						} else {
							System.out.println("There is something wrong");
						}
					} else {
						System.out.println("Please enter 1 or 2!\n");
					}
			 } 
			
			
			 System.out.println("Would you like to try another customer! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userIn.nextInt();
				if(type == 2) {
					break;
				}
			 
		}
	}
	
	//3.2
	public void viewCustomerBankAccount() {
		   this.userIn.nextLine();
		   System.out.println("Please enter the customer's email:\n");
		   String email = this.userIn.nextLine();
		   User customer = new Customer();
		   customer.setEmail(email);
		   List<Object> list = esi.viewCustomerInfo(customer);
		   if(list.size()>0)
			   System.out.println(list); 
		   else {
			   System.out.println("Customer is not found!\n");
		   }
	}
	
	//3.3
	public void viewLogOfTransaction() {
	
	}
	
	
	//2
	public void manageCustomerAccount(User user) {
		while(true)  {
			List<Object> list = csi.viewCustomerInfo(user);
			if(list == null) {
				System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n"
						+ "Your account is under review! Please wait!\n");
				System.out.println("Press enter to come back to the main menu!\n");
				this.userIn.nextLine();
				break;
			}
			BankingAccount bankingAccount = new BankingAccount();
			CheckingAccount chequeingAccount = new CheckingAccount();
			SavingAccount savingAccount = new SavingAccount();
			for(Object o: list) {
				 if(o instanceof CheckingAccount) {
					chequeingAccount = (CheckingAccount) o;
				} else if(o instanceof SavingAccount) {
					savingAccount = (SavingAccount) o;
				} else if(o instanceof BankingAccount) {
					bankingAccount = (BankingAccount)o;
				} 
			}
			
			System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n");
			
			System.out.println("Select the options below for your account:\n");
			System.out.println("1. View your account details\n"
					         + "2. Deposit money to your account\n"
					         + "3. Withdraw money from your account\n"
					         + "4. View pending transaction\n"
					         + "5. Send money to another account\n"
					         + "6. Return to the main menu\n");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			switch(option) {
			    case 1: 
			    	viewCustomerAccountDetail(chequeingAccount,savingAccount);
			    	break;
			    case 2:
			    	depositMoney(bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    case 3: 
			    	withdrawMoney(bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    case 4:
			    	viewPendingTransaction(user.getUserId(),bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    case 5:
			    	sendMoney(user.getUserId(),bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    default:
			    	break;
			}
			
			if(option==6) {
				break;
			}
			
			
		} 			
			
		
	}
	
	//2.1
	public void viewCustomerAccountDetail(CheckingAccount ca,SavingAccount sa) {
		  this.userIn.nextLine();  
		  System.out.println("Your accounts:\n");
		  System.out.println(ca);
		  System.out.println(sa);
		  System.out.println("Please enter to come bank the main menu!\n");
		  this.userIn.nextLine();
	}
	//2.2
	public void depositMoney (BankingAccount ba, CheckingAccount ca,SavingAccount sa) {
		
		System.out.println("Which account do you need to deposit?\n"
				+ "1. Chequing Account\n"
				+ "2. Saving Account\n");
		int option = this.userIn.nextInt();
		double amount;
		while(true) {
			System.out.println("Please enter your amount:\n");
			amount =this.userIn.nextDouble();
			if(amount>0)
				break;
			else {
				System.out.println("Please enter positive amount!\n");
			}
		}
		
		this.userIn.nextLine();
		if(option ==1) {
			if(csi.deposit(ba.getBankId(),ca, amount))
				System.out.println("You successfully deposit to your account!");		
		} else if(option == 2) {
			if(csi.deposit(ba.getBankId(), sa, amount))
				System.out.println("You successfully deposit to your account!");
		} else {
			System.out.println("Please try again and enter 1 or 2\n");
		}
	  System.out.println("Please enter to come bank the main menu!\n");
	  this.userIn.nextLine();
	}
	//2.3
	public void withdrawMoney(BankingAccount ba, CheckingAccount ca,SavingAccount sa) {
		System.out.println("Which account do you need to withdraw?\n"
				+ "1. Chequing Account\n"
				+ "2. Saving Account\n");
		int option = this.userIn.nextInt();
		double amount;
		//System.out.println(ca.getBalance());
//		while(true) {
//			System.out.println("Please enter your amount:\n");
//			amount =this.userIn.nextDouble();
//			if(amount>0)
//				break;
//			else {
//				System.out.println("Please enter positive amount!\n");
//			}			
//		}
		while(true) {
			System.out.println("Please enter your amount:\n");
			amount =this.userIn.nextDouble();
			if(option == 1 && (amount > ca.getBalance()) || amount <0) {
				System.out.println("Your amount cannot withdraw amount bigger than the Chequing Account balance or less than 0!\n");
			} else if(option == 2 && (amount > sa.getBalance() || amount < 0)){
				System.out.println("Your amount cannot withdraw amount bigger than the Saving Account balance or less than 0!\n");
			} else {
				break;
			}
			
		}
		//System.out.println("Please enter your amount:\n");
		//double amount =this.userIn.nextDouble();
		this.userIn.nextLine();
		if(option ==1) {
			if(csi.withdraw(ba.getBankId(),ca, amount))
				System.out.println("You successfully withraw to your account!");		
		} else if(option == 2) {
			if(csi.withdraw(ba.getBankId(), sa, amount))
				System.out.println("You successfully withraw to your account!");
		} else {
			System.out.println("Please try again and enter 1 or 2\n");
		}
	  System.out.println("Please enter to come bank the main menu!\n");
	  this.userIn.nextLine();
	}
	//2.4
	public void viewPendingTransaction(int repicientId,BankingAccount ba, CheckingAccount ca,SavingAccount sa) {
		while(true) {
			System.out.println("These below are your pending transactions: \n");
			this.userIn.nextLine();
			List<Transaction> list = csi.findRepicient(repicientId);
			if(list.size()>0) {
				System.out.println(list);
				System.out.println("Please enter the transaction ID to accept: ");
				int option = this.userIn.nextInt();
				System.out.println(option);
				for(Transaction t : list) {
					if(t.getTransactionId() == option) {
						System.out.println("Which account do you need to deposit?\n"
								+ "1. Chequing Account\n"
								+ "2. Saving Account\n");
						int choice = this.userIn.nextInt();
						this.userIn.nextLine();
						if(choice ==1) {
							if(csi.deposit(ba.getBankId(),ca, t.getTransactionAmount())) {
								csi.acceptMoneyTransfer(t);
								System.out.println("You successfully deposit to your account!");	
							}
									
						} else if(choice == 2) {
							if(csi.deposit(ba.getBankId(), sa, t.getTransactionAmount())) {
								csi.acceptMoneyTransfer(t);
								System.out.println("You successfully deposit to your account!");
							}
								
						} else {
							System.out.println("Please try again and enter 1 or 2\n");
						}
										
					}
				}
			}
			else {
				System.out.println("You don't have any pending transaction!");
			}
			
			 System.out.println("Would you like to try another transaction! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userIn.nextInt();
				if(type == 2) {
					break;
				}	
		}
		
		
	  //System.out.println("Please enter to come bank the main menu!\n");
	  //this.userIn.nextLine();
	}
	//2.5
	public void sendMoney(int userId,BankingAccount ba, CheckingAccount ca,SavingAccount sa) {
		this.userIn.nextLine();   
		System.out.println("Please enter the email of repicient:\n");
		   String email = this.userIn.nextLine();
		   System.out.println("Which account do you need to withdraw for sending money?\n"
					+ "1. Chequing Account\n"
					+ "2. Saving Account\n");
			int option = this.userIn.nextInt();
			System.out.println("Please enter your amount:\n");
			double amount =this.userIn.nextDouble();
			this.userIn.nextLine();
			if(option ==1) {
				if(csi.withdraw(ba.getBankId(),ca, amount)) {
					
					if(csi.transferMoney(email, userId, ca, amount))
						System.out.println("You successfully transfer the money!");
				}
							
			} else if(option == 2) {
				if(csi.withdraw(ba.getBankId(), sa, amount))
				{
					if(csi.transferMoney(email, userId, sa, amount))
						System.out.println("You successfully transfer the money!");
				}
			} else {
				System.out.println("Please try again and enter 1 or 2\n");
			}
		  System.out.println("Please enter to come bank the main menu!\n");
		  this.userIn.nextLine();
		   //csi.transferMoney(email, ba.getBankId(), existingAccount, amount)
		   
	}
	  
}