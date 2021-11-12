package Management_System.Management_System_19I_2008;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class oracleDbHandler extends presistentHandler {
	
	@Override
	public void saveAccount(Account Acc) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.43.49:1521:xe","system","12345");
			
			String sql="insert into Account values (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1, Acc.getaccountNumber());
			statement.setDouble(2, Acc.getBalance());
			statement.setString(3, Acc.getdateCreated());
			statement.setString(4, Acc.Gettype());
			int rowsUpdated=statement.executeUpdate();
			if (rowsUpdated>0) {
				System.out.print("Sucessfull saving the Account Information");
			}
			else {
				System.out.println("Not Sucessfull");
			}
			
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.print("Driver not found\n");
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
			System.out.print("####################################");
			e.printStackTrace();
			System.out.print("####################################");
			System.out.print("Connection Not Established\n");
		}
	}
}
