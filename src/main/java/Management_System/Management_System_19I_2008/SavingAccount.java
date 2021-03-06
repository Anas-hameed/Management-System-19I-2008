package Management_System.Management_System_19I_2008;
import java.time.LocalDate;
public class SavingAccount extends Account {
	public static double IntrestRate;
	public SavingAccount(int id,double balance, String CID,String name,String Address,String phoneNumber,String type) {
		super(id,balance,type, CID,name,Address,phoneNumber);
	}
	// Here we have calculate Zakat	
	public double calculateZakat_Tax() {
		double Amount=0;
		if(balance>=20000) {
			Amount= (balance*2.5)/100;
			balance-=Amount;
		}
		return Amount;
	}
	
	public boolean makeWithdrawal(double Amount){
		boolean check=false;
		if(balance>=Amount) {
			balance-=Amount;
			check= true;
		}
		return check;
	}
	//	Calculating the if required
	//	Note Interest is calculate compounded Annually Using formula;- Principle*Rate*time
	public double calculatelnterest() {
		LocalDate now= LocalDate.now();
		int year1    = now.getYear();
		int year2    = dateCreated.getYear();
		int month1   = now.getMonthValue();
		int month2   = dateCreated.getMonthValue();
		double time=(double) (((year1-year2)*12+ (month1-month2))/12.0);
		double intrest= (balance*IntrestRate*time);
		balance+= intrest; 
		return intrest;
	}
}