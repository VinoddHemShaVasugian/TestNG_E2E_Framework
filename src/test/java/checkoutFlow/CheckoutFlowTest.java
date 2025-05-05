package checkoutFlow;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.LoginPage;

public class CheckoutFlowTest extends SuperTestNG {
	LoginPage loginPage = new LoginPage();
	ProjectSpecific projectSpecific = new ProjectSpecific();
	String sExpectedResult = "Swag Labs";

	@Test()
	public void testCheckoutFlow() {
		projectSpecific.login();
	}
}
