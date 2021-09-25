package Management_System.Management_System_19I_2008;

import static org.junit.Assert.*;
import org.junit.Test;

public class SavingAccountTest {
	@Test public void calculatelnteresttest() {
		SavingAccount Acc= new SavingAccount(1, 1000.0,"100", "Anas", "Isb", "035555555555", "saving"); 
		double intrest = Acc.calculatelnterest();
		// Since account is created write know.[no prev. data, so intrest will be zero by formula]
		assertEquals(0.0, intrest, 0.0001);	
	}

}
