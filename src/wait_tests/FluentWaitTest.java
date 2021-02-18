package wait_tests;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Set property for location of chromedriver.exe file
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
				
		// Create driver object for Chrome browser
		WebDriver driver = new ChromeDriver();
		
		// Load website with hidden "hello world" web element
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		
		// Click on Start button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		
		// Wait 6 seconds for the "hello world" element to become visible on the page, checking if it's displayed once every 2 seconds
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	        .withTimeout(Duration.ofSeconds(6)) // Set timeout to 10 seconds
	        .pollingEvery(Duration.ofSeconds(2)) // Poll every 2 seconds
	        .ignoring(NoSuchElementException.class); // If "hello world" element is not found, then ignore and re-check until timeout

	    // Use instance of FluentWait to check if "hello world" element has become visible
	    WebElement helloWorld = wait.until(new Function<WebDriver, WebElement>()
	    {
	    	// Customised function for checking if "hello world" element has become visible 
	    	public WebElement apply(WebDriver driver)
	    	{
	    		// Create WebElement instance for "hello world" element
	    		WebElement hW = driver.findElement(By.cssSelector("div[id='finish'] h4"));
	    		
	    		// If "hello world" element is visible
	    		if (hW.isDisplayed())
	    			// Return "hello world" element
	    			return hW;
	    		// Otherwise
	    		else
	    			// Return null
	    			return null;
	    	}
	    });
	    
	    // Print "hello world" element
	    System.out.println(helloWorld.getText());
		   
	}

}
