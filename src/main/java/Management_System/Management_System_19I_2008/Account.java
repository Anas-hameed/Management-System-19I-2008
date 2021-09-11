package Management_System.Management_System_19I_2008;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;
//import java.time.Period;
public class Account {
	Vector<String>transections;
	int accountNumber;
	double balance;
	LocalDate date;
	LocalTime time;
	Customer Onner; 
	public Account(int Number) {
		accountNumber = Number;
		balance= 0;
		date= LocalDate.now();
		time= LocalTime.now();
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