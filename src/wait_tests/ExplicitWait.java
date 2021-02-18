package wait_tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

	public static void main(String[] args) /*throws InterruptedException*/ {
		// TODO Auto-generated method stub

		// Set property for location of chromedriver.exe file
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		
		// Create driver object for Chrome browser
		WebDriver driver = new ChromeDriver();
		
		// Load GreenKart website and maximise window
		driver.get("https://www.rahulshettyacademy.com/seleniumPractise/");
		//driver.manage().window().maximize();
		//Thread.sleep(1000);
		
		// Create string array with required vegetables
		String[] requiredVeg = {"Brocolli", "Cucumber", "Cauliflower", "Carrot", "Beetroot"};
		
		// Create list of web elements containing the vegetable text for each available vegetable within GreenKart website
		List<WebElement> availableVeg = driver.findElements(By.cssSelector("h4.product-name"));
		
		// Call the addToCart method to add required vegetables to the cart
		addToCart(driver, requiredVeg, availableVeg);
		
		// Select Cart
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		
		// Select PROCEED TO CHECKOUT button
		driver.findElement(By.cssSelector("div.cart-preview.active button[type='button']")).click();
		//Thread.sleep(1000);
		
		// Create instance of WebDriverWait with explicit wait of 1 second
		WebDriverWait explicitWait = new WebDriverWait(driver, 1);
		
		// Invoke explicit wait for promotion code (this ensures the page for the promotion code has been loaded)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		// The following doesn't seem to work
		//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input.promoCode")))); 
		
		// Enter Promotion Code
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		
		// Click Apply button
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		//Thread.sleep(5000);
		
		// Change explicit wait to 5 seconds
		explicitWait.withTimeout(Duration.ofSeconds(5));
		
		// Invoke explicit wait for promotion code response
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
		// Print text returned after applying Promotion Code, i.e. promotion code response
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
			
	}

	private static void addToCart(WebDriver d, String[] rV, List<WebElement> aV)
	{
		// For each required vegetable
		for (int i=0; i<rV.length; i++)
		{	
			// Check each web element containing the vegetable text in website 
			for (int j=0; j<aV.size(); j++)
			{
				// If vegetable text contains the required vegetable
				if (aV.get(j).getText().contains(rV[i]))
				{
					// Click on button to add matching vegetable
					d.findElements(By.xpath("//div[@class='product-action']/button")).get(j).click();
					// Exit inner for loop to move to the next required vegetable in outer for loop 
					break;
				}
			}
		}
	}
	
}