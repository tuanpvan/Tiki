package stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageobjects.OrderTikiForm;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderproductStepdefs {
	WebDriver driver;
	OrderTikiForm tiki;

	//ham khoi tao
	public OrderproductStepdefs() {
		this.driver = Hooks.driver;
		this.tiki = new OrderTikiForm(this.driver);
	}

	@Given("^The user is ready login to Tiki\\.vn and staying in homepage$")
	public void the_user_is_ready_login_to_Tiki_vn_and_staying_in_homepage() throws Exception {
		this.tiki.OpenTikiHome();
	}

	@When("^The user add a product on \"([^\"]*)\" into shopping cart, and the user will see product on a shopping cart\\.$")
	public void the_user_add_a_product_on_into_shopping_cart_and_the_user_will_see_product_on_a_shopping_cart(
			String addProduct) throws Exception {
		this.tiki.addandCheckOut();
	}

	@Then("^The system wil be show notification \"([^\"]*)\"$")
	public void the_system_wil_be_show_notification(String CompleteMessage) throws Exception {
		this.tiki.OrderComplete(CompleteMessage);
	}
}
