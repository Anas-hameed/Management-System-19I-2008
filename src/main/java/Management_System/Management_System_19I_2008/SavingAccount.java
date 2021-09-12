package Management_System.Management_System_19I_2008;
public class SavingAccount extends Account {
	public static double IntrestRate;
	public SavingAccount(int AccNO,double balance,String name,String Address,String phoneNumber,String type) {
		super(AccNO,balance,name,Address,phoneNumber);
		type= "saving";
	}
	public boolean calculateZakat() {
		if(balance>=20000) {
			balance-= (balance*2.5)/100;
			return true; 
		}
		return false;
	}
	//	Calculating the if required
	void calculatelnterest() {

	}
}
