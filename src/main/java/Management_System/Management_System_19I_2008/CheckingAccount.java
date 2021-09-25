package Management_System.Management_System_19I_2008;

public class CheckingAccount extends Account{
	float Rate;
	int transections; 
	public CheckingAccount(int id,double balance,String CID,String name,String Address,String phoneNumber,String type) {
		super(id,balance,type,CID,name,Address,phoneNumber);
		transections= 0; 
	}
	public boolean makeWithdrawal(double Amount){
		boolean check=false;
		if(balance+5000>=Amount) {
			balance-=Amount;
			check= true;
		}
		return check;
	}
	//	Here we have calculated the tax at the Rate of 5% Anually. 
	public double calculateZakat_Tax() {
		double Amount=0; 
		if(balance>0) {
			Amount= .05*balance;
			balance-= Amount;
			
		}
		return Amount;
	}
}
