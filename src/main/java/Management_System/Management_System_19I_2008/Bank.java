package Management_System.Management_System_19I_2008;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;
public class Bank 
{
	String bankName;
	Account[][] userAcounts;
	int totalAccount;
	Bank(String Name){
		bankName= Name;
		totalAccount=0; 
		File temp = new File("AccountData.txt");
		if (temp.exists()) {
			try{	
				Scanner sc= new Scanner(temp);	
				totalAccount = sc.nextInt();
				System.out.println("Total Account:" +totalAccount);
				sc.close();
			}
			catch(FileNotFoundException e) {
				System.out.println("An Error Occur While Reading file");
				e.printStackTrace();
			}
		} 
		else{
			totalAccount = 0; 
			userAcounts  = null;
		}
	}
	public boolean AddAccount(int AccNO,double balance,String name,String Address,String phoneNumber,String type,LocalDate Date ) 
	{
		//	Logic for checking if account with this accountNo exit already.	
		boolean found=false;
		int index= 0;
		for(int i=0; i<totalAccount; i++) {
			for(int j=0; j<userAcounts[i].length; j++ ) {
				if(userAcounts[i][j].accountNumber==AccNO) {
					found=true; 
					index=i; 
				}
			}
		}
		//		Not allowing user to create more than two Account With same Account number
		if(found) 
		{
			if(userAcounts[index].length==2) {
				return false;
			}
			// here need to deal with mutiple account of same type as well. 			
			Account[] temp= new Account[2];
			temp[0]= userAcounts[index][0];
			if(type.equalsIgnoreCase("saving")) {					
				Account temp1= new SavingAccount(AccNO,balance,name,Address,phoneNumber,type);
				temp[1]= temp1;
			}
			else if(type.equalsIgnoreCase("checking")) {
				Account temp1= new CheckingAccount(AccNO,balance,name,Address,phoneNumber,type);
				temp[1]= temp1;
			}
			userAcounts[index]= temp; 
			return true;
		}
		//	Add a account
		totalAccount++; 
		Account[][] flag=new Account[totalAccount][];
		for(int i=0; i<totalAccount-1; i++ ) {
			flag[i]= new Account[userAcounts[i].length];
			for(int j=0; j<userAcounts[i].length; j++ ) {
				flag[i][j]= userAcounts[i][j];
			}
		}
		flag[totalAccount-1]=new Account[1];
		if(type.equalsIgnoreCase("saving")) {					
			Account temp1= new SavingAccount(AccNO,balance,name,Address,phoneNumber,type);
			flag[totalAccount-1][0]= temp1;
		}
		else if(type.equalsIgnoreCase("checking")) {
			Account temp1= new CheckingAccount(AccNO,balance,name,Address,phoneNumber,type);
			flag[totalAccount-1][0]= temp1;
		}
		userAcounts= flag;
		return true; 
	}
	
	
	public void AllAccountDetails() {
		System.out.println("Name      Account_Number      Balance");
		for(int i=0; i<totalAccount; i++ ) {
			for(int j=0; j<userAcounts[i].length;j++ ){
				System.out.println(userAcounts[i][j].Onner.name+"      "+userAcounts[i][j].accountNumber+"      "+userAcounts[i][j].balance);
			}
		}
	}
}
