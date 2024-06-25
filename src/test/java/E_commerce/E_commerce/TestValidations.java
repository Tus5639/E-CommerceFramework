package E_commerce.E_commerce;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Ecommerce.pageObjects.BaseClass;
import Ecommerce.pageObjects.CheckOutPage;
import Ecommerce.pageObjects.ConfirmationPage;
import Ecommerce.pageObjects.OrdersAndCartButtonPage;
import Ecommerce.pageObjects.PaymentPage;
import Ecommerce.pageObjects.Products_Catalogue;
import Ecommerce.pageObjects.landingPage;

public class TestValidations extends BaseClass {

	@Test(dataProvider = "getData", priority = 1, groups = { "Purchase", "E2E" })
	public void FirstOrder(HashMap<String, String> output) {
		landingPage login = new landingPage(driver);
		login.Ecommerce_login(output.get("email"), output.get("password"));
		Products_Catalogue pc = new Products_Catalogue(driver);
		List<WebElement> product_list = pc.product_visible();
		pc.getProductName(output.get("product"));
		pc.AddtoCart(output.get("product"));
		// pc.AddProduct(productToBeSelected);

		OrdersAndCartButtonPage ord = new OrdersAndCartButtonPage(driver);
		ord.OrderClick();

		CheckOutPage cop = new CheckOutPage(driver);
		cop.CheckOut(output.get("product"));
		cop.CheckButton();

		PaymentPage pg = new PaymentPage(driver);
		pg.payment("Ind", "India");
		pg.placeOrder();

		ConfirmationPage cp = new ConfirmationPage(driver);
		Assert.assertEquals(cp.ConfirmationMessage(), "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> input_data = convertJsontoString(
				System.getProperty("user.dir") + "\\src\\main\\java\\Ecommerce\\data\\OrdersFile.json");
		return new Object[][] { { input_data.get(0) }, { input_data.get(1) } };
	}

	@Test(dataProvider = "getData2", priority = 2, groups = { "Invalid", "Regression" })
	public void loginPage(HashMap<String, String> input) {
		landingPage lp = new landingPage(driver);
		lp.Ecommerce_login(input.get("email"), input.get("password"));
		WebElement errorMessage = lp.error();
		Assert.assertEquals(errorMessage.getText(), "Incorrect email or password.");
	}

	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String, String>> data = convertJsontoString(
				System.getProperty("user.dir") + "\\src\\main\\java\\Ecommerce\\data\\credentials.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
