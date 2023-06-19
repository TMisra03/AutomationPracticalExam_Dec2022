package validateWebsite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestSteps {

	String browser;
	String url;
	WebDriver driver;

	By toggleAll_Checkbox = By.xpath("//input[@name='allbox']");
	By todo1_Checkbox = By.xpath("//input[@name='todo[1]']");
	By todo2_Checkbox = By.xpath("//input[@name='todo[2]']");
	By todo3_Checkbox = By.xpath("//input[@name='todo[3]']");
	By remove_Button = By.xpath("//input[@value='Remove']");
	
	By category_TextBox = By.xpath("//input[@name='categorydata']");
	By add_Category_Button = By.xpath("//input[@value='Add category']");
	By abc123_Category = By.xpath("//span[contains(text(), 'abc123')]");
	By duplicate_Category_Message = By.xpath("//body");
	
	By month_Dropdown_Field = By.xpath("//select[@name='due_month']");

	By skyBlue_Button = By.linkText("Set SkyBlue Background");
	By white_Button = By.linkText("Set White Background");
	By webpage_BackgroundColor_SkyBlue = By.tagName("body[style = 'background-color: skyblue;']");
	By webpage_BackgroundColor_White = By.tagName("body[style = 'background-color: white;']");
	
	@BeforeClass
	public void readConfig() {

		// Scanner //FileReader //InputStream //BufferedReader
		try {
			InputStream input = new FileInputStream("src\\test\\java\\config\\config.properties");

			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Before
	public void init() {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.out.println("Driver configuration required");
		}

		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*
	 * Test 1: Validate when the toggle all check box is CHECKED, all check boxes
	 * for list items are also CHECKED ON.
	 */
//	@Test
	public void toggleCheckboxes() {
		waitForElement(driver, 20, toggleAll_Checkbox);

		driver.findElement(toggleAll_Checkbox).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue("abc checkbox is not checked.", driver.findElement(todo1_Checkbox).isSelected());
		Assert.assertTrue("techfios checkbox is not checked.", driver.findElement(todo2_Checkbox).isSelected());
		Assert.assertTrue("jobs checkbox is not checked.", driver.findElement(todo3_Checkbox).isSelected());
	}

	/*
	 * Test 2: Validate that a single list item could be removed using the remove
	 * button when a single checkbox is selected.
	 */
//	@Test
	public void removeListItem() {
		driver.findElement(toggleAll_Checkbox).click();
		driver.findElement(todo1_Checkbox).click();
		driver.findElement(remove_Button).click();
	}

	/*
	 * Test 3: Validate that all list item could be removed using the remove button
	 * and when "Toggle All" functionality is on.
	 */
//	@Test
	public void removeAllListItems() {
		driver.findElement(toggleAll_Checkbox).click();
		driver.findElement(remove_Button).click();
		Assert.assertFalse("todo1 checkbox is not present.", driver.findElements(todo1_Checkbox).isEmpty());
		Assert.assertFalse("todo2 checkbox is not present.", driver.findElements(todo2_Checkbox).isEmpty());
		Assert.assertFalse("todo3 checkbox is not present.", driver.findElements(todo3_Checkbox).isEmpty());
	}

	/*
	 * Test 1: Validate a user is able to add a category and once the category is
	 * added it should display.
	 */
//	@Test
	public void addCategory() {
		driver.findElement(category_TextBox).sendKeys("abc123");
		driver.findElement(add_Category_Button).click();
		Assert.assertTrue("abc123 category is not present.", driver.findElement(abc123_Category).isDisplayed());
	}

	/*
	 * Test 2: Validate a user is not able to add a duplicated category. If it does
	 * then the following message will display:
	 * "The category you want to add already exists: <duplicated category name>."
	 */
//	@Test
	public void addDuplicateCategory() {
		driver.findElement(category_TextBox).sendKeys("abc123");
		driver.findElement(add_Category_Button).click();
		String errorMessage = driver.findElement(duplicate_Category_Message).getText();
		System.out.println(errorMessage);
	}

	/*
	 * Test 3: Validate the month drop down has all the months (jan, feb, mar ...)
	 * in the Due Date dropdown section.
	 */
//	@Test
	public void selectMonthInDropdown() {
		for (int i = 1; i <= 12; i++) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (i == 1) {
				String month = "Jan";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 2) {
				String month = "Feb";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 3) {
				String month = "Mar";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 4) {
				String month = "Apr";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 5) {
				String month = "May";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 6) {
				String month = "Jun";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 7) {
				String month = "Jul";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 8) {
				String month = "Aug";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 9) {
				String month = "Sep";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 10) {
				String month = "Oct";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 11) {
				String month = "Nov";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			} else if (i == 12) {
				String month = "Dec";
				selectFromDropdown(driver.findElement(month_Dropdown_Field), month);
			}
		}
	}

//*****************************************************************************************
	/*
	 * Scenario 1: Sky Blue Background 
	 * Given: "Set SkyBlue Background" button exists 
	 * When: I click on the button 
	 * Then: The background color will change to sky blue
	 */
	@Given("{string} button exists")
	public void test_SkyBlue_Background_Button_Exists() {
		Assert.assertTrue("The Set SkyBlue Background button does not exist.", driver.findElement(skyBlue_Button).isDisplayed());
	}

	@When("I click on the Set SkyBlue Background button")
	public void click_SkyBlue_Background_Button() {
		driver.findElement(skyBlue_Button).click();
	}

	@Then("The background color will change to sky blue")
	public void test_Background_Color_Is_SkyBlue() {
		Assert.assertTrue("The background color of the webpage did not change to SkyBlue.", driver.findElement(webpage_BackgroundColor_SkyBlue).isDisplayed());
	}

//*****************************************************************************************
	/*
	 * Scenario 2: White Background Change 
	 * Given: "Set White Background" button exists
	 * When: I click on the button 
	 * Then: the background color will change to white
	 */
	@Given("{string} button exists")
	public void test_White_Background_Button_Exists() {
		Assert.assertTrue("The Set White Background button does not exist.", driver.findElement(white_Button).isDisplayed());
	}

	@When("I click on the Set White Background button")
	public void click_White_Background_Button() {
		driver.findElement(white_Button).click();
	}

	@Then("The background color will change to white")
	public void test_Background_Color_Is_White() {
		Assert.assertTrue("The background color of the webpage did not change to White.", driver.findElement(webpage_BackgroundColor_White).isDisplayed());
	}

//*****************************************************************************************	

	private void waitForElement(WebDriver driver, int timeInSeconds, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	private void selectFromDropdown(WebElement element, String visibleText) {

		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
//	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
