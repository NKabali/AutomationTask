package StepDefinition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Scenario;

import automationtask.Classes.Screenshots;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.*;

public class StepDef {
	ExtentTest test;
	
	ResourceBundle Config = ResourceBundle.getBundle("Config");
	public static WebDriver driver;
	private String browser = Config.getString("browser");
	private String Testurl = Config.getString("Testurl");

	ResourceBundle OR = ResourceBundle.getBundle("OR");
	private String SearchBar = OR.getString("SearchBar");
	private String Searchicon = OR.getString("Searchicon");
	private String Signin = OR.getString("Signin");
	private String email = OR.getString("email");
	private String Continue = OR.getString("Continue");
	private String password = OR.getString("password");
	private String Signinclick = OR.getString("Signinclick");

	private String product1search = OR.getString("product1search");
	private String product2search = OR.getString("product2search");
	private String product3search = OR.getString("product3search");
	private String product4search = OR.getString("product4search");

	private String Addtolist = OR.getString("Addtolist");
	private String Accountlist = OR.getString("Accountlist");
	private String Yourlist = OR.getString("Yourlist");
	private String Youridealist = OR.getString("Youridealist");
	
	private String Signout = OR.getString("Signout");
	private String Menutosignout = OR.getString("Menutosignout");
	
	private String Viewcart = OR.getString("Viewcart");
	private String Clearcart = OR.getString("Clearcart");
	private String Togetcountofcart = OR.getString("Togetcountofcart");
	
	private String Itemsinwishlist = OR.getString("Itemsinwishlist");
	private String AddtoCart = OR.getString("AddtoCart");
	private String Clearwishlist = OR.getString("Clearwishlist");
	private String Cartcontainercount = OR.getString("Cartcontainercount");
	private String Togetcountofwishlist = OR.getString("Togetcountofwishlist");

	@Given("Customer opens URL")
	public void customer_opens_url() {
		System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Testurl);
	}

	@Given("Customer Land on Home page")
	public void Customer_Land_on_Home_page() {
		String actualtitle = driver.getTitle();
		String Expectedtitle = "Amazon.com. Spend less. Smile more.";
		Assert.assertEquals(Expectedtitle, actualtitle);
	}

	@When("Mouse Hover sign in and clicks on sign in")
	public void Mouse_Hover_sign_in_and_clicks_on_sign_in() {
		driver.findElement(By.id(Signin)).click();
	}

	@When("Enters credential to Login")
	public void user_enters_testuser__and_Test(DataTable usercredentials) throws Throwable {
		List<List<String>> data = usercredentials.asLists();
		driver.findElement(By.name(email)).sendKeys(data.get(0).get(0));
		driver.findElement(By.id(Continue)).click();
		driver.findElement(By.id(password)).sendKeys(data.get(0).get(1));
		driver.findElement(By.className(Signinclick)).click();
	}

	@Given("Customer refresh the page")
	public void Customer_refresh_the_page() {
		driver.navigate().refresh();
	}

	@When("search for a product and add to wishist")
	public void search_for_a_product_and_add_to_wishlist(io.cucumber.datatable.DataTable table) {
		List<Map<String, String>> listdata = table.asMaps(String.class, String.class);

		for (Map<String, String> e : listdata) {
			driver.navigate().refresh();
			WebElement searchbox = driver.findElement(By.cssSelector(SearchBar));
			searchbox.clear();
			searchbox.sendKeys(e.get("product"));
			driver.findElement(By.cssSelector(Searchicon)).click();

			String product = e.get("product");
			switch (product) {
			case "TCL":
				driver.findElement(By.xpath(product1search)).click();
				break;
			case "Samsung":
				driver.findElement(By.xpath(product2search)).click();
				break;
			case "Nokia":
				driver.findElement(By.xpath(product3search)).click();
				break;
			case "Motorola":
				driver.findElement(By.xpath(product4search)).click();
				break;
			}
			driver.findElement(By.xpath(Addtolist)).click();
		}
	}

	@Then("view  my wishlist")
	public void view_my_wishlist() {

		driver.navigate().refresh();
		driver.findElement(By.id(Accountlist)).click();
		driver.findElement(By.xpath(Yourlist)).click();
		driver.findElement(By.xpath(Youridealist)).click();
	}

	@Then("find four selected items in my wishlist")
	public void find_four_selected_items_in_my_wishlist() {

		List<WebElement> price = driver.findElements(By.xpath(Itemsinwishlist));
		Assert.assertEquals(price.size(), 4);
	}

	@Given("find lowest price item and add to my cart")
	public void find_lowest_price_item_and_add_to_my_cart() {

		List<WebElement> price = driver.findElements(By.xpath(Itemsinwishlist));
		
		float Allprice[] = new float[price.size()];
		for (int i = 0; i < price.size(); i++) {
			Allprice[i] = Float.valueOf(price.get(i).getText().replace("$", "").trim());
		}
		Arrays.sort(Allprice);
		

		for (int i = 0; i < price.size(); i = i + 1) {
			if (Allprice[0] == Float.valueOf(price.get(i).getText().replace("$", "").trim())) {
				driver.findElement(By.xpath(AddtoCart)).click();
			}
		}
	}

	@Then("verify the item in my cart")
	public void verify_the_item_in_my_cart() {
		driver.navigate().refresh();
		String Countofcartitems = driver.findElement(By.xpath(Cartcontainercount)).getText();
		Assert.assertEquals(Countofcartitems,"1" );
	}

	@Then("Clear Wishlist")
	public void Clear_Wishlist() {
		List<WebElement> Wishlistitems = driver.findElements(By.cssSelector(Togetcountofwishlist));
		for (int i = 0; i < Wishlistitems.size(); i++) {
			driver.findElement(By.xpath(Clearwishlist)).click();
		}
	}

	@Then("Clear cart")
	public void Clear_cart() {
		int countofcartitems = Integer.parseInt(driver.findElement(By.xpath(Togetcountofcart)).getText());
		System.out.println(countofcartitems);
		driver.findElement(By.id(Viewcart)).click();
		for (int i = 0; i <= countofcartitems; i++) {
			driver.findElement(By.xpath(Clearcart)).click();
		}
	}

	@Then("Sign out of the account")
	public void Sign_out_of_the_account() throws InterruptedException {
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(Menutosignout)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(Signout)).click();
	}
	
	@AfterMethod
	public void tearDown(ITestResult testresult) throws IOException {
		if(testresult.getStatus()==ITestResult.FAILURE) {
			String path=Screenshots.takeScreenshot(driver, testresult.getName());
			ExtentTest imagepath=test.addScreenCaptureFromPath(path);
			test.addScreenCaptureFromPath(path);
		}
	}
}
