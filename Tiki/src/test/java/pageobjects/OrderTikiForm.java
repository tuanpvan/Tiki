package pageobjects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By; //import mvn selenium 3.1.4 vào pom 
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class OrderTikiForm {
	WebDriver driver;
	//pageobject chua cac Element
	By txtalert = By.cssSelector("body > div.wrap > div > div > div.col-lg-8 > div > div > div > div.col-lg-8.col-md-9");
	//By txtEmail = By.cssSelector("#popup-login-email");
	//By txtPassword = By.cssSelector("#login_password");
	
	public OrderTikiForm(WebDriver driver) {
		this.driver = driver;
	}
	
	//define actions
	public void OpenTikiHome() throws InterruptedException {
		this.driver.get("http://tiki.vn/");
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//header user login
		this.driver.findElement(By.cssSelector("#header-user")).click();
		//after popup click login
		this.driver.findElement(By.cssSelector("#login_link > a")).click();
		//wait
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//start login
		this.driver.findElement(By.cssSelector("#popup-login-email")).sendKeys("tiki@ngaima.com");
		this.driver.findElement(By.cssSelector("#login_password")).sendKeys("123Tiki");
		this.driver.findElement(By.cssSelector("#login_popup_submit")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector("#onesignal-popover-dialog"), true));
		
		this.driver.findElement(By.cssSelector("<button id=\"onesignal-popover-cancel-button\" class=\"align-right secondary popover-button\">Để sau</button>"));
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		//wait

	}
	
	
	//add product to cart from Hot Deal
	
	public void addandCheckOut() {
		//open hot deal
		this.driver.findElement(By.linkText("https://tiki.vn/deal-hot?src=header_label")).click();
		//this.driver.findElement(By.cssSelector("#header > div.top-promo > div > div > a:nth-child(7)")).click();
		//choose one product (random)
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		this.driver.findElement(By.cssSelector("#deal-all-root > div > div > div.flex-container >"
												+ " div.product-listing > div.items > a:nth-child(1)")).click();
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//add product to cart
		this.driver.findElement(By.cssSelector("#\\23 mainAddToCart")).click();
		//see shopping cart
		this.driver.findElement(By.cssSelector("#header-cart")).click();
		//start checkout
		this.driver.findElement(By.cssSelector("#right-affix > div.each-row > button")).click();
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//select address (saved address)
		this.driver.findElement(By.cssSelector("#item-7207518 > div > div > p.action > button.btn.btn-default.btn-custom1.saving-address.is-blue")).click();
		//radio button choose payment method is selected
		this.driver.findElement(By.cssSelector("#choose_payment_method > div > div >"
				+ "							 	div.form-group.row.row-style-3.method_payment_cod >"
				+ "								div.col-lg-1.col-md-1.col-sm-1.col-xs-2 > label > div")).isSelected();
		
		
		//finally
		this.driver.findElement(By.cssSelector("#btn-placeorder")).click();
	}
	
	public void OrderComplete(String message) {
		//Cảm ơn bạn đã mua hàng tại Tiki!

		WebDriverWait wait = new WebDriverWait(this.driver, 5);
		WebElement ordercomplete = wait.until(ExpectedConditions.presenceOfElementLocated(txtalert));
		Assert.assertEquals(message, ordercomplete.getAttribute("Đặt hàng thành công"));
		
	}
	
	/*public void fillEmail(String email, String password) {
		this.driver.findElement(txtEmail).sendKeys(email);
	
		
	}
	
	public void fillPassword(String password) {
		this.driver.findElement(txtPassword).sendKeys(password);
		this.driver.findElement(txtPassword).sendKeys(Keys.ENTER);
	}*/
	

}
//The user provide Username "tiki@ngaima.com" and Password "123Tiki" to login and the user click on "Đăng nhập"
