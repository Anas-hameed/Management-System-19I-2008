package Management_System.Management_System_19I_2008;
import java.time.LocalTime;
import java.time.LocalDate;
public class Transection {
	LocalTime timeOfTransection; 
	LocalDate dateOfTransaction;
	double	TransactionAmount;
	public Transection(double Amount) {
		timeOfTransection= LocalTime.now();
		dateOfTransaction= LocalDate.now();
		TransactionAmount= Amount;
	}
}
