package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewRequest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		
		// Launch URL
		driver.get("https://dev103117.service-now.com");
		driver.switchTo().frame(0);
		
		// Enter Credentials
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(2000);
		
		// Enter Incident in Filter textbox
		driver.findElement(By.id("filter")).sendKeys("My Work", Keys.ENTER);
		Thread.sleep(5000);
		
		// Click My Work
		driver.findElement(By.xpath("(//*[text()='My Work'])[1]")).click();
		Thread.sleep(3000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		//Click New button
		driver.findElement(By.id("sysverb_new")).click();
		Thread.sleep(2000);
		
		// Click Request
		driver.findElement(By.linkText("Request")).click();
		Thread.sleep(2000);
		
		// Capture the Incident number
		String reqNumber = driver.findElement(By.id("sc_request.number")).getAttribute("value");
		System.out.println("The Request number is : " + reqNumber);
		
		// Enter Short Description
		driver.findElement(By.id("sc_request.short_description")).sendKeys("Dhilip Request number");
		
		// click Submit
		driver.findElement(By.id("sysverb_insert")).click();
		Thread.sleep(2000);
		
		// Copy the created incident and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(reqNumber, Keys.ENTER);
		Thread.sleep(2000);
		String reqNumberCreated = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println(reqNumberCreated);
		
		if (reqNumber.equals(reqNumberCreated)) {
			System.out.println("Request Number should be created successfully");
		} else {
			System.out.println("Request Number not created");

		}
		
		// Close the Browser
		driver.close();
					

	}

}
