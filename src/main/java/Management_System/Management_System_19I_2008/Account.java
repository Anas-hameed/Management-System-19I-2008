package Management_System.Management_System_19I_2008;
import java.time.LocalDate;
public abstract class Account {
	int accountNumber;
	double balance;
	LocalDate dateCreated;
	String type; 
	Customer Onner; 
	Transection[] transections;
	public Account(int id,double bal,String Type,String CID,String name, String Address,String phoneNumber) {
		accountNumber= id;
		balance= bal;
		type= Type;
		dateCreated= LocalDate.now();
		Onner= new Customer(CID,name, Address,phoneNumber);
		transections= new Transection[0];
	}

	public final void checkBalance() {
		System.out.printf("%-15s %-12s \n",Onner.name,balance );
	}
	public final void printStatement() {
		System.out.printf("%-10s %-12s %-13s %-15s %-12s %-18s %-13s %-12s %-12s \n","Account-No","Balance","Name","Adress","Contact","Time","Date","Amount","Rem_Balance");
		System.out.printf("%-10s %-12s %-13s %-15s %-12s ",accountNumber,balance, Onner.name,Onner.Address, Onner.phoneNumber);
		if(transections!=null)
		{
			for(int i=0; i<transections.length; i++) {
				transections[i].display();
				System.out.printf("%-10s %-12s %-13s %-15s %-12s ","","","","","");
			}
		}
	}
	public void AddTransection(double transAmount,double remBalance, String transtype) {
		Transection[] flag= new Transection[transections.length+1];
		for(int i=0; i<transections.length; i++) {
			flag[i]= transections[i];
		}
		Transection temp= new Transection(transAmount,remBalance, transtype);
		flag[transections.length]= temp;
		transections= flag;
	}
	public void makeDeposit(double deposit) {
		balance+= deposit;
		AddTransection(deposit, balance, "Deposit");
	}
	public boolean transferAmount(double transAmount) {
		if(balance-transAmount>0) {
			balance-= transAmount;
			AddTransection(transAmount, balance, "Transfer");
			return true;
		}
		return false;
	}
	public void displayAllDeductions() {
		for(int i=0; i<transections.length; i++ ) {
			if(transections[i].transType=="withdraw" || transections[i].transType=="Zakat_Deducted" ||  transections[i].transType=="Tax_Deducted" || transections[i].transType=="Transfer")
			{		
				System.out.printf("%-20s %-12s %-25s %-15s \n",transections[i].transType,transections[i].dateOfTransaction, transections[i].timeOfTransection,transections[i].transactionAmount);
			}
		}	
	}
	public void displayAllDeductions(String temp) {
		for(int i=0; i<transections.length; i++ ) {
			if(transections[i].transType=="withdraw" || transections[i].transType=="Zakat_Deducted" ||  transections[i].transType=="Tax_Deducted" || transections[i].transType=="Transfer")
			{		
				System.out.printf("%-20s %-12s %-25s %-15s \n",transections[i].transType,transections[i].dateOfTransaction, transections[i].timeOfTransection,transections[i].transactionAmount);
				System.out.printf(temp, "", "", "");
			}
		}	
	}
	
	//	this Method will be over-written by the respective Account Type class
	abstract boolean makeWithdrawal(double Amount);
	//	This function will calculate or tax respectively in account derived class
	abstract double calculateZakat_Tax();

}