package E_commerce.E_commerce;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ecommerce.pageObjects.BaseClass;
import Ecommerce.pageObjects.landingPage;

public class TestNegativeScenarios extends BaseClass {

	@Test(dataProvider = "getData2", priority = 2, groups = { "Invalid", "Regression" })
	public void NegativeTesting(String email, String password) {
		landingPage lp = new landingPage(driver);
		lp.Ecommerce_login(email, password);
		WebElement errorMessage = lp.error();
		Assert.assertEquals(errorMessage.getText(), "Incorrect email or password.");
	}

	@DataProvider
	public Object[][] getData2() {
		return new Object[][] { { "tushar1234536@gmail.com", "Tushar1234" }, { "Sg11285@gmail.com", "Sh123456" } };
	}

}
