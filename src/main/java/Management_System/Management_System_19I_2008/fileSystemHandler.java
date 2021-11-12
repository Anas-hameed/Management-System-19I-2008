package Management_System.Management_System_19I_2008;

import java.io.FileWriter;
import java.io.IOException;

public class fileSystemHandler extends presistentHandler{
	
	// This function save the account information to the file System database
	@Override
	public void saveAccount(Account Acc){
		try {
			FileWriter fw= new FileWriter("AccountData.csv", true);
			String str= Integer.toString(Acc.getaccountNumber())+","+Double.toString(Acc.getBalance())+","+Acc.getdateCreated()+","+ Acc.Gettype();
			fw.write(str);
			fw.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

}