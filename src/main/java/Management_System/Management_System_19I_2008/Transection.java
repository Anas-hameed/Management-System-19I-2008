package Management_System.Management_System_19I_2008;
import java.time.LocalTime;
import java.time.LocalDate;
public class Transection {
	LocalTime timeOfTransection; 
	LocalDate dateOfTransaction;
	double	  transactionAmount;
	double 	  remainingAmount;
	String    transType;
	public Transection(double Amount,double rembalance, String type) {
		timeOfTransection= LocalTime.now();
		dateOfTransaction= LocalDate.now();
		transactionAmount= Amount;
		remainingAmount	 = rembalance; 
		transType		 = type; 
		
	}
	public void display() {
		System.out.printf("%-18s %13s %-12s %-12s \n",timeOfTransection,dateOfTransaction,transactionAmount,remainingAmount);
	}
}
