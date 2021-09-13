package Management_System.Management_System_19I_2008;
import java.time.LocalDate;
public class Bank 
{
	String bankName;
	Account[][] userAcounts;
	int totalAccount;
	Bank(String Name)
	{
		bankName= Name;
		totalAccount=0; 
		userAcounts  = null;
	}

	private int CheckAccountExistance(String customerId) {
		int index=-1;
		for(int i=0; i<totalAccount; i++) {
			for(int j=0; j<userAcounts[i].length; j++ ) {
				if(userAcounts[i][j].Onner.CustomerID.equalsIgnoreCase(customerId)) {
					index=i; 
				}
			}
		}
		return index;
	}
	public int VerifyAccount(int Acc_ID) {
		int index=-1;
		for(int i=0; i<totalAccount; i++) {
			for(int j=0; j<userAcounts[i].length; j++ ) {
				if(userAcounts[i][j].accountNumber== Acc_ID) {
					index=i; 
				}
			}
		}
		return index;
	}
	public int AddAccount(double balance,String customerId,String name,String Address,String phoneNumber,String type,LocalDate Date ) 
	{
		int index=  CheckAccountExistance(customerId);
		// Not allowing user to create more than two Account With same Account number
		if(index!=-1) 
		{
			int Id = 1000+index*2+1; 
			if(userAcounts[index].length==2) {
				System.out.println("A Same user can have have maximun of two Accounts");
				return -1;
			}
			else
			{
				if(!(userAcounts[index][0].type.equalsIgnoreCase(type))) 
				{	
					Account[] temp= new Account[2];
					temp[0]= userAcounts[index][0];
					if(type.equalsIgnoreCase("saving")) {					
						Account temp1= new SavingAccount(Id,balance,customerId,name,Address,phoneNumber,type);
						temp[1]= temp1;
					}
					else {
						Account temp1= new CheckingAccount(Id,balance,customerId,name,Address,phoneNumber,type);
						temp[1]= temp1;
					}
					userAcounts[index]= temp; 
					return Id;
				}
				else {
					System.out.println("Limit Exceeds! A Same user can not have multiple "+type+ " Accounts");
					return -1;
				}
			}

		}
		//	Add a account
		int Id = 1000+totalAccount*2; 
		totalAccount++; 
		Account[][] flag=new Account[totalAccount][];
		for(int i=0; i<totalAccount-1; i++ ) {
			flag[i]= new Account[userAcounts[i].length];
			for(int j=0; j<userAcounts[i].length; j++ ) {
				flag[i][j]= userAcounts[i][j];
			}
		}
		flag[totalAccount-1]=new Account[1];
		if(type.equalsIgnoreCase("saving")) {					
			Account temp1= new SavingAccount(Id,balance,customerId,name,Address,phoneNumber,type);
			flag[totalAccount-1][0]= temp1;
		}
		else if(type.equalsIgnoreCase("checking")) {
			Account temp1= new CheckingAccount(Id,balance,customerId,name,Address,phoneNumber,type);
			flag[totalAccount-1][0]= temp1;
		}
		else {
			System.out.println("Only two Type of Account are allowed Saving and Checking");
		}
		userAcounts= flag;
		return Id; 
	}
	public void deleteAccount(int AcNo) {
		int index= VerifyAccount(AcNo);
		if(index!=-1) {
			if(userAcounts[index].length==2) {
				Account[] flag= new Account[1];
				if(userAcounts[index][0].accountNumber==AcNo) {
					flag[0]= userAcounts[index][1];
				}
				else{
					flag[0]= userAcounts[index][0];
				}
				userAcounts[index]=flag;
			}
			else{
				//Ignoring the whole copy senario in case of deleting				
				userAcounts[index]=new Account[0];
			}
			System.out.println("Closing of the Account was Sucessfull");
		}
		else {
			System.out.println("No such Account with the given Account Number found");
		}
	}
	public void DepositAmount(int AN, int index, double Amount) {
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AN) {
				userAcounts[index][0].makeDeposit(Amount);
			}
			else {
				userAcounts[index][1].makeDeposit(Amount);
			}
		}
		else {
			userAcounts[index][0].makeDeposit(Amount);
		}
	}
	public boolean withdrawAmount(int AN, int index, double Amount) {
		boolean check;
		int j;
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AN) {
				check=userAcounts[index][0].makeWithdrawal(Amount);
				j=0; 
			}
			else {
				check=userAcounts[index][1].makeWithdrawal(Amount);
				j=1; 
			}
		}
		else {
			check=userAcounts[index][0].makeWithdrawal(Amount);
			j=0;
		}
		if(check) {
			double bal=userAcounts[index][j].balance;
			userAcounts[index][j].AddTransection(Amount,bal,"withdraw");
		}
		return check;
	}
	public void CheckAccountBalance(int index, int AN){
		System.out.printf("%-15s %-12s \n","Name", "Balance");
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AN) {
				userAcounts[index][0].checkBalance();
			}
			else {
				userAcounts[index][1].checkBalance();
			}
		}
		else {
			userAcounts[index][0].checkBalance();
		}
	}
	public void PrintStatementofAccount(int AccNo, int index) {
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AccNo) {
				userAcounts[index][0].printStatement();
			}
			else {
				userAcounts[index][1].printStatement();
			}
		}
		else {
			userAcounts[index][0].printStatement();
		}
	}
	public boolean AccountTransfer(int AccNo, int index,double transAmount, int tranferAcNo ) {
		boolean check;
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AccNo) {
				check=userAcounts[index][0].transferAmount(transAmount);
			}
			else {
				check=userAcounts[index][1].transferAmount(transAmount);
			}
		}
		else {
			check=userAcounts[index][0].transferAmount(transAmount);
		}
		return check;
	}
	public void displayAlldeduction(int AccNo, int index) {
		System.out.printf("%-20s %-12s %-25s %-15s \n","Transection_Type","Date","Time","Deduction_Amount");
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AccNo) {
				userAcounts[index][0].displayAllDeductions();
			}
			else {
				userAcounts[index][1].displayAllDeductions();
			}
		}
		else {
			userAcounts[index][0].displayAllDeductions();
		}
	}

	public void deductZakat(int AccNo, int index) {
		int j; 
		if(userAcounts[index].length==2) {
			if(userAcounts[index][0].accountNumber==AccNo) {
				j=0;
			}
			else {
				j=1;
			}
		}
		else {
			j=0;
		}
		double balance   = userAcounts[index][j].balance;
		double AmountDeduct = userAcounts[index][j].calculateZakat_Tax();
		if(userAcounts[index][j].type.equalsIgnoreCase("saving")) {
			if(AmountDeduct!=0) {
				userAcounts[index][j].AddTransection(AmountDeduct, balance, "Zakat_Deducted");
				System.out.println("Zakat deducted from the Account Sucessfully");
			}
			else {
				System.out.println("User don't Have Required banlance to deduct the Zakat");
			}
		}
		else{
			userAcounts[index][j].AddTransection(AmountDeduct, balance, "Tax_Deducted");
			System.out.println("Tax deducted from the Account Sucessfully");
		}
	}
	public void AllAccountDeductionDetails() {
		System.out.printf("%-15s %-16s %-17s %-19s %-12s %-25s %-15s \n","Name", "Acount_Number", "Date_Created","Transection_Type","Date","Time","Deduction_Amount");
		for(int i=0; i<userAcounts.length; i++ ) {
			for(int j=0; j<userAcounts[i].length; j++) {
				System.out.printf("%-15s %-16s %-17s",userAcounts[i][j].Onner.name,userAcounts[i][j].accountNumber,userAcounts[i][j].dateCreated );
				userAcounts[i][j].displayAllDeductions("%-15s %-16s %-17s");
			}
		}
	}
	public void AllAccountDetails() {
		System.out.printf("%-20s %-17s %-17s %-10s %-10s \n","AccountHolder_Name","Account_Number","Date_Created", "Type","Balance");
		for(int i=0; i<totalAccount; i++ ) {
			for(int j=0; j<userAcounts[i].length;j++ ){
				System.out.printf("%-20s %-17s %-17s %-10s %-10s \n", userAcounts[i][j].Onner.name, userAcounts[i][j].accountNumber,userAcounts[i][j].dateCreated,userAcounts[i][j].type ,userAcounts[i][j].balance);
			}
		}
	}

}
