package Management_System.Management_System_19I_2008;
import java.time.LocalDate;
public class Account {
	int accountNumber;
	double balance;
	LocalDate dateCreated;
	String type; 
	Customer Onner; 
	public Account(int AccNO,double bal,String name, String Address,String phoneNumber) {
		accountNumber= AccNO;
		balance= bal;
		dateCreated= LocalDate.now();
		Onner= new Customer(name, Address,phoneNumber);
	}
	
	final double checkBalance() {
		return balance;
	}
	public final void printStatement() {

	}
	public void makeDeposit(double deposit) {
		balance+= deposit;
	}
	public void transferAmount() {

	}

	//	 boolean makeWithdrawal(){
	//		
	//	}
}