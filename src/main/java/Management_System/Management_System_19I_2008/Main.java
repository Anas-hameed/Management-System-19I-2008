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
			System.out.println("1) To Open   a  New  Account, Close an account |");
			System.out.println("2) To login  for  a  specific  user    Account |");
			System.out.println("3) To perform   account  operations as earlier |");
			System.out.println("4) Specify Interest Rate for all Saving Acount |");
			System.out.println("5) Display all Account details  for a Accounts |");
			System.out.println("6) Display all Account     Deductions  Details |");
			System.out.println("7) To  	   Exit           the   Admin     Menu |");
			System.out.println("################################################");
			condition= sc.next().charAt(0);
			switch(condition) {
			case '1':{
				int AccountNo; 
				double balance; 
				String name, Address, phoneNumber, type;
				System.out.print("Enter the   Account Number :: ");
				AccountNo= sc.nextInt();
				System.out.print("Add Intial Account balance :: ");
				balance= sc.nextDouble();
				System.out.print("Enter the customer   Name  :: ");
				sc.nextLine();
				name= sc.nextLine();
				System.out.print("Enter the customer  Adress :: ");
				Address= sc.nextLine();
				System.out.print("Enter the customer  number :: ");
				phoneNumber= sc.next();
				System.out.print("Enter the type  of Account :: ");
				type= sc.next();
				boolean check= bank.AddAccount(AccountNo,balance,name, Address, phoneNumber,type,null); 
				if(check) {
					System.out.println("Accout Sucessfully Added");
				}
				break;
			}
			case '2':{
				
				break;
			}
			case '3':{
				bank.AllAccountDetails();
				break;
			}
			case '4':{
				double intrestRate;
				System.out.println("Specify the Intrest rate Applicable to All Accounts");
				intrestRate= sc.nextDouble();
				SavingAccount.IntrestRate= intrestRate;
				System.out.println("Instrest Rate for All Account Updated Sucessfully");
				break;
			}
			case '5':{
				break;
			}
			case '6':{
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
