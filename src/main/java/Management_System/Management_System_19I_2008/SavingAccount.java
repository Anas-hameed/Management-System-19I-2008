package Management_System.Management_System_19I_2008;

public class SavingAccount extends Account {
	String type; 
	public SavingAccount(int Number) {
		super(Number);
		type= "saving";
	}
	public boolean calculateZakat() {
		if(balance>=20000) {
			balance-= (balance*2.5)/100;
			return true; 
		}
		return false;
	}
}
