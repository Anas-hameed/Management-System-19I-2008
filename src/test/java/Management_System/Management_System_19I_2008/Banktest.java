package Management_System.Management_System_19I_2008;

import static org.junit.Assert.*;
import org.junit.Test;

public class Banktest {

	//	Testing of adding a accounts.
	@Test public void AddAccount() {
		Bank bank= new Bank("BankName");                         
		int Ac1= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null);    // Can open a saving account 
		int Ac3= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null);    // 1 person cannot have two savings  accounts
		int Ac2= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","checking",null);  // can open a checking account
		int Ac4= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null);    // 1 person cannot have two checking accounts
		int Ac5= bank.AddAccount(1000,"101", "Anas", "Isb","055555555555","balance",null);    // trying to open a not exiting type account


		assertNotEquals(Ac1 , -1);
		assertNotEquals(Ac2 , -1);
		assertEquals   (Ac3 , -1);
		assertEquals   (Ac4 , -1);
		assertEquals   (Ac5 , -1);
	}

	//	Depositing a Amount and verifying balance of the account with actual account balance
	@Test public void DepositAmounttest() {
		Bank bank= new Bank("BankName");                         
		int AccNo1= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null); 
		bank.DepositAmount(AccNo1, 0, 1000);
		int AccNo2=bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","checking",null); 
		bank.DepositAmount(AccNo1, 0, 1000);
		bank.DepositAmount(AccNo2, 0, 1000);
		assertEquals(3000.0,bank.userAcounts[0][0].balance,0.001);
		assertEquals(2000.0,bank.userAcounts[0][1].balance,0.001);

	}


	//	Withdrawing a Amount and verifying balance of the account with actual account balance
	@Test public void withdrawAmountTest() {
		Bank bank= new Bank("BankName");  
		int AccNo1= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","checking",null); 
		bank.withdrawAmount(AccNo1, 0, 1000);
		int AccNo2= bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null); 	
		bank.withdrawAmount(AccNo1, 0, 1000);     // From saving account we can withdraw even when balance is 0[up to 5000] 
		bank.withdrawAmount(AccNo2, 0, 1000);
		assertEquals(-1000.0,bank.userAcounts[0][0].balance,0.001);
		assertEquals(0.0,bank.userAcounts[0][1].balance,0.001);
	}

	//	Checking closing  of the account
	@Test public void deleteAccoutTest() {
		Bank bank= new Bank("BankName");  
		int AccNo1  = bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null);
		int AccNo2  = bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","checking",null);
		boolean case1= bank.deleteAccount(AccNo1);   // Deleting a saving  account.
		int AccNo3  = bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving",null); // adding saving to check wheather it is allowed after delete or not

		boolean case3= bank.deleteAccount(AccNo3);   // Deleting a saving    account.
		boolean case2= bank.deleteAccount(AccNo2);   // Deleting a checking  account.
		boolean case4= bank.deleteAccount(AccNo1) ;  // Deleting a account that does not exit.Must return false.
		assertEquals(true  , case1);
		assertEquals(true  , case2);
		assertEquals(true  , case3);
		assertEquals(false , case4);
	}

	//	Bank account transfer test
	@Test public void AccountTransferTest() {
		Bank bank= new Bank("BankName");                         
		int AccNo1  = bank.AddAccount(1000,"100", "Anas", "Isb","055555555555","saving"  ,null); 
		int AccNo1_2= bank.AddAccount(100,"100", "Anas", "Isb","055555555555","checking"  ,null); //user 1 have two type of accounts

		int AccNo2= bank.AddAccount(1000,"101", "Asim", "Isb","066666666666","checking",null); // user 2.
		boolean case1= bank.AccountTransfer(AccNo1,0, 1000,AccNo2 );

		assertEquals(true  , case1);
		assertEquals(0.0   ,bank.userAcounts[0][0].balance,0.001);    // user1 must have 0    balance after transfer
		assertEquals(2000.0,bank.userAcounts[1][0].balance,0.001);    // user2 must have 2000 balance after transfer 	

		// Negative case. Not sufficient balance checking		
		boolean case2= bank.AccountTransfer(AccNo1_2, 0, 1000, AccNo2);
		assertEquals(false  , case2);
		assertEquals(100.0   ,bank.userAcounts[0][1].balance,0.001);    // user1 must have 0    balance after transfer
		assertEquals(2000.0,bank.userAcounts[1][0].balance,0.001);      // user2 must have 2000 balance after transfer 	

		// Transferring the money back
		boolean case3= bank.AccountTransfer(AccNo2, 1, 1000, AccNo1);
		assertEquals(true  , case3);
		assertEquals(1000.0  ,bank.userAcounts[0][0].balance,0.001);  
		assertEquals(1000.0  ,bank.userAcounts[1][0].balance,0.001);    	

	}

	// Zakat deduction form an account 
	@Test public void deductZakat() {
		Bank bank= new Bank("BankName");                         
		int Acc1  = bank.AddAccount(20000,"100", "Anas", "Isb","055555555555","saving"   ,null); 
		int Acc2  = bank.AddAccount(10000,"101", "sham", "Isb","066666666666","saving"   ,null); 
		int Acc3  = bank.AddAccount(10000,"101", "sham", "Isb","066666666666","checking" ,null); 
		bank.deductZakat(Acc1, 0);
		bank.deductZakat(Acc2, 1);
		bank.deductZakat(Acc3, 1);

		assertEquals(19500.0 , bank.userAcounts[0][0].balance, 0.0001); // Acc1-> zakat deducted= (2.5*20000)/100=500.
		assertEquals(10000.0 , bank.userAcounts[1][0].balance, 0.0001); // Acc2-> No zakat deducted. Insufficient balance
		assertEquals(9500.0  , bank.userAcounts[1][1].balance, 0.0001); // Acc3-> tax= 1000*5/100 = 500.
	}


	//	Checking account balance of different users.
	@Test public void CheckAccountBalancetest(){
		Bank bank= new Bank("BankName");                         
		int Acc1  = bank.AddAccount(20000,"100", "Anas", "Isb","055555555555","saving"   ,null); 
		int Acc2  = bank.AddAccount(50000,"100", "Anas", "Isb","055555555555","checking" ,null); 
		int Acc3  = bank.AddAccount(10000,"101", "sham", "Isb","066666666666","saving"   ,null); 

		double balance1 =bank.CheckAccountBalance(0, Acc1);
		double balance2 =bank.CheckAccountBalance(0, Acc2);
		double balance3 =bank.CheckAccountBalance(1, Acc3);

		assertEquals(20000.0, balance1, 0.0001);
		assertEquals(50000.0, balance2, 0.0001);
		assertEquals(10000.0, balance3, 0.0001);
	}

	//	Checking different print functions
	@Test public void PrintAllAccountDetailsTest(){
		Bank bank= new Bank("BankName");                         
		bank.AddAccount(20000.0,"100", "Anas", "Isb","055555555555","saving"   ,null); 
		bank.AddAccount(50000.0,"100", "Anas", "Isb","055555555555","checking" ,null); 
		bank.AddAccount(10000.0,"101", "sham", "Isb","066666666666","saving"   ,null); 
		//		The output comparison is without any space of above added data
		String Expectedstr1= "20000.0100AnasIsb055555555555saving50000.0100AnasIsb055555555555checking10000.0101shamIsb066666666666saving";                    		
		String str= bank.AllAccountDetails();

		assertEquals(Expectedstr1,str);
	}

	// Testing the PrintStatement function.
	@Test public void PrintStatementTest(){
		Bank bank= new Bank("BankName");                         
		int Acc1=bank.AddAccount(20000.0,"100", "Anas", "Isb","055555555555","saving"   ,null);  // user1 -- Saving
		int Acc2= bank.AddAccount(50000.0,"100", "Anas", "Isb","055555555555","checking" ,null); // user1 -- checking
		int Acc3=bank.AddAccount(10000.0,"101", "sham", "Isb","066666666666","saving"   ,null);           // user2 -- saving
		//		Performing transaction
		bank.DepositAmount(Acc1, 0, 1000);
		bank.DepositAmount(Acc2, 0, 5000);
		bank.DepositAmount(Acc3, 1, 10000);
		//		The output comparison is without any space of above added data
		//      Note that the intial balance is updated after the Deposit of amount
		String Expectedstr1= "21000.0100AnasIsb055555555555saving";
		String Expectedstr2= "55000.0100AnasIsb055555555555checking";
		String Expectedstr3= "20000.0101shamIsb066666666666saving"; 		
		String Actualstr1= bank.PrintStatementofAccount(Acc1,0);
		String Actualstr2= bank.PrintStatementofAccount(Acc2,0);
		String Actualstr3= bank.PrintStatementofAccount(Acc3,1);
		assertEquals(Expectedstr1,Actualstr1);
		assertEquals(Expectedstr2,Actualstr2);
		assertEquals(Expectedstr3,Actualstr3);
	}

	//	Display all sort of duduction test case
	@Test public void displayAllDeductionstest(){
		Bank bank= new Bank("BankName");                         
		int Acc1=bank.AddAccount(20000.0,"100", "Anas", "Isb","055555555555","saving"   ,null);  // user1 -- Saving
		int Acc2= bank.AddAccount(50000.0,"100", "Anas", "Isb","055555555555","checking" ,null); // user1 -- checking
		int Acc3=bank.AddAccount(50000.0,"101", "sham", "Isb","066666666666","saving"   ,null);           // user2 
		//	Performing transactions
		bank.AccountTransfer(Acc1, 0, 10000, Acc2);
		bank.withdrawAmount(Acc2, 0, 10000);
		bank.deductZakat(Acc3, 1);		
		//		Expected result for each type of account and user.
		String Expectedstr1="Transfer10000.0";
		String Expectedstr2="withdraw10000.0";
		String Expectedstr3="Zakat_Deducted1250.0";// zakat= 50000*0.025 = 1250
		// Getting all sort of deduction 
		String str1= bank.displayAlldeduction(Acc1, 0);
		String str2= bank.displayAlldeduction(Acc2, 0);
		String str3= bank.displayAlldeduction(Acc3, 1);
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
	
		assertEquals(Expectedstr1,str1);
		assertEquals(Expectedstr2,str2);
		assertEquals(Expectedstr3,str3);
	}
}
