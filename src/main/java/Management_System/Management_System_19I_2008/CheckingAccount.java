package Management_System.Management_System_19I_2008;

public class CheckingAccount extends Account{
	String type; 
	int transections; 
	public CheckingAccount(int Number) {
		super(Number);
		transections= 0; 
		type = "checking";
	}
	
}
