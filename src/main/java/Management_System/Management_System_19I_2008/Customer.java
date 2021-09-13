package Management_System.Management_System_19I_2008;

public class Customer {
	String CustomerID; 
	String name; 
	String Address;
	String phoneNumber;
	public Customer(String CustomerID,String name, String address,String phoneNumber) {
		this.CustomerID  = CustomerID;
		this.name		 = name;
		this.Address	 = address;
		this.phoneNumber = phoneNumber;
	}
}