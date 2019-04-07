package stepdefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;

	@Before
	public void startTest() {
		// hook để chạy nhiều trình duyệt
		// de chay chon folder co chua file pom.xml => cd/ link folder => mvn test (chọn
		// trình duyệt -Dbrowser = firefox)

		String webdriver = System.getProperty("browser", "chrome"); // set firefox la trinh duyet mac dinh
		switch (webdriver) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/WhiteButterfly/Downloads/chromedriver");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "/Users/WhiteButterfly/Downloads/geckodriver");
			driver = new FirefoxDriver();
			break;
		default:
			throw new RuntimeException("Unsupported webdriver: " + webdriver);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	@After
	public void AfterScrnario(Scenario scenario) {
		driver.quit();
		
		//them code screen shoot vao day
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException platformdontsupportscreenshot) {
				System.err.println(platformdontsupportscreenshot.getMessage());
			}
		}
	}
}
