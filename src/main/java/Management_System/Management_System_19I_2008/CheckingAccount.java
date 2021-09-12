package Management_System.Management_System_19I_2008;

public class CheckingAccount extends Account{
	int transections; 
	public CheckingAccount(int AccNO,double balance,String name,String Address,String phoneNumber,String type) {
		super(AccNO,balance,name,Address,phoneNumber);
		type = "checking";
		transections= 0; 
	}
	
}
