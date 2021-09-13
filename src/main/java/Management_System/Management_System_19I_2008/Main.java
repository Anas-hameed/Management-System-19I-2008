package Management_System.Management_System_19I_2008;
import java.util.Scanner;
public class Main {	
	public static void main(String[] args) {
		Bank bank= new Bank("HBL");
		Scanner sc= new Scanner(System.in);
		char condition='5';
		do {
			System.out.println("------------------------------------------------");
			System.out.println("Select the Desired Option from  these  Options  ");
			System.out.println("------------------------------------------------");
			System.out.println("1) To Open   a   New   use-defined     Account |");
			System.out.println("2) To login  for  a  specific  user    Account |");
			System.out.println("3) Specify Interest Rate for all Saving Acount |");
			System.out.println("4) Display all Account details  for a Accounts |");
			System.out.println("5) Display all Account     Deductions  Details |");
			System.out.println("6) To close  a             given       Account |");
			System.out.println("7) To  	   Exit           the   Admin     Menu |");
			System.out.println("################################################");
			condition= sc.next().charAt(0);
			switch(condition) {
			case '1':{
				double balance; 
				String name, Address, phoneNumber, type, CustomerID;
				System.out.print("Add Intial Account balance :: ");
				balance= sc.nextDouble();
				System.out.print("Enter the customer   Name  :: ");
				sc.nextLine();
				name= sc.nextLine();
				System.out.print("Enter  customer Unique ID  :: ");
				CustomerID= sc.nextLine();
				System.out.print("Enter the customer  Adress :: ");
				Address= sc.nextLine();
				System.out.print("Enter the customer  number :: ");
				phoneNumber= sc.next();
				System.out.print("Enter the type  of Account :: ");
				type= sc.next();
				int check= bank.AddAccount(balance,CustomerID,name, Address, phoneNumber,type,null); 
				if(check!=-1) {
					System.out.println("Accout Sucessfully Added.");
					System.out.println("Remember your Account Number :: "+check);
				}
				break;
			}
			case '2':{
				char temp;
				int AccId; 
				System.out.print("Enter your Accout Id to login ::");
				AccId= sc.nextInt();
				int index= bank.VerifyAccount(AccId);
				if(index!=-1) {
					System.out.println("Account login was Sucessfull");
					do {
						System.out.println("--------------------------------------------");
						System.out.println("Select the desired Operation  to   perform  ");
						System.out.println("--------------------------------------------");
						System.out.println("Press 1 to deposit  Amount in your  Account |");
						System.out.println("Press 2 to Withdraw Amount from the Account |");
						System.out.println("Press 3 to check   balance  of  the Account |");
						System.out.println("Press 4 to print Statement  of  the Account |");
						System.out.println("Press 5 to transfer Amount to other Account |");
						System.out.println("Press 6 to deduct zakat or tax from Account |");
						System.out.println("Press 7 to print all deduction of   Account |");
						System.out.println("Press 8 to  logout  from      this  Account |");
						System.out.println("#############################################");
						temp=sc.next().charAt(0);
						switch(temp){
						case '1':{
							double depositAmount; 
							System.out.print("Enter Amount to deposit into the Account :: ");
							depositAmount= sc.nextDouble();
							while(depositAmount<0) {
								System.out.println("Enter deposit Amount greater thans Zero :: ");
								depositAmount= sc.nextDouble();
							}
							bank.DepositAmount(AccId, index, depositAmount);
							System.out.println("Deposit Was Sucessfully Added");
							break;
						}
						case '2':{
							double withdraw; 
							System.out.print("How much Amount to Withdraw from the Account :: ");
							withdraw= sc.nextDouble();
							while(withdraw<0) {
								System.out.print("Enter withdraw Amount greater thans Zero :: ");
								withdraw= sc.nextDouble();
							}
							boolean check= bank.withdrawAmount(AccId, index, withdraw);
							if(check) {
								System.out.println("Withdraw Was Sucessfully Completed");
							}
							else{
								System.out.println("Insufficient balance, Withdraw UnSuccessfull");
							}
							break;
						}
						case '3':{
							bank.CheckAccountBalance(index, AccId);
							break;
						}
						case '4':{
							bank.PrintStatementofAccount(AccId, index);
							break;
						}
						case '5':{
							double Amount;
							int accountNo;
							System.out.print("Enter the Amount to transfer :: ");
							Amount= sc.nextDouble();
							while(Amount<0) {
								System.out.print("Enter transfer Amount greater thans Zero ::");
								Amount= sc.nextDouble();
							}	
							System.out.print("Enter the Account Number to whom Amount is transfered :: ");
							accountNo= sc.nextInt();
							int val= bank.VerifyAccount(accountNo);
							if(val!=-1) {								
								boolean check= bank.AccountTransfer(AccId, index,Amount ,accountNo );
								if(check==true) {
									bank.DepositAmount(accountNo, index,Amount);
									System.out.println("Ammount has been transfered Successfully");
								}else {
									System.out.println("Insufficent Account balance for transfer");
								}
							}
							else{
								System.out.println("No Account has Account Number :: "+ accountNo);
							}
							break;
						}
						case '6':{
							bank.deductZakat(AccId, index);
							break;
						}
						case '7':{
							bank.displayAlldeduction(AccId, index);
							break;
						}
						case '8':{
							temp= '8';
						}
						}
					}while(temp!='8');
				}
				else {
					System.out.println("No Account Exist with this Account Number");
				}
				break;
			}
			case '3':{
				double intrestRate;
				System.out.print("Specify the Intrest rate Applicable to All Accounts :: ");
				intrestRate= sc.nextDouble();
				while(intrestRate<0) {
					System.out.print("Enter intrest_Rate greater thans Zero :: ");
					intrestRate= sc.nextDouble();
				}
				SavingAccount.IntrestRate= intrestRate;
				System.out.println("Instrest Rate for All Account Updated Sucessfully");
				break;
			}
			case '4':{
				bank.AllAccountDetails();
				break;
			}
			case '5':{
				bank.AllAccountDeductionDetails();
				break;
			}
			case '6':{
				int AcNo; 
				System.out.print("Enter the Account-Number of the Acount to Close ::");
				AcNo= sc.nextInt();
				bank.deleteAccount(AcNo);
				break;
			}
			case '7':{
				condition= '7';
				break;
			}
			default:{
				System.out.println("Select correct option from the Given Options");
			}
			}

		}while(condition!='7');
		System.out.println("################################################");
		System.out.println("------------------------------------------------");
		sc.close();
	}
}
