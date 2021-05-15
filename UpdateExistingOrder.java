package AssignmentWeek4Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingOrder {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Launch URL
		driver.get("https://dev103117.service-now.com");
		driver.switchTo().frame(0);
		
		// Enter Credentials
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(2000);
		
		// Enter Incident in Filter textbox
		driver.findElement(By.id("filter")).sendKeys("Service Catalog", Keys.ENTER);
		
		// Click Requests
		driver.findElement(By.xpath("//div[text()='Requests']")).click();
		Thread.sleep(3000);
				
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
				
		// Enter the created Request number
		WebElement reqNumberDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select reqDropdown = new Select(reqNumberDropDown); 
		reqDropdown.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("REQ0010058", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select the Created Request
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(3000);
		
		// Update approval field to Requested
		WebElement approvalfieldDropDown = driver.findElement(By.xpath("//select[@id='sc_request.approval']"));
		Select aproveDropdown = new Select(approvalfieldDropDown); 
		aproveDropdown.selectByVisibleText("Requested");
		Thread.sleep(1000);
		
		// Update Requested State field to Approved
		WebElement requestStateDropDown = driver.findElement(By.xpath("sc_request.request_state"));
		Select requestDropdown = new Select(requestStateDropDown); 
		requestDropdown.selectByVisibleText("Approved");
		Thread.sleep(1000);
		
		//Enter location
		driver.findElement(By.xpath("//span[text()='Location']//following::span[2]")).click();
		Thread.sleep(2000);
		
		// Print the number of windows open
		Set<String> allwindowHandles = driver.getWindowHandles();
		System.out.println(allwindowHandles.size());

		//	Get the second window from the Set
		List<String> lstofWindows = new ArrayList<String>(allwindowHandles);

		//	Get the Second windows handle
		String SecondWindowHandle = lstofWindows.get(1);
		
		//	Move the control to Second window
		driver.switchTo().window(SecondWindowHandle);

		// Select the location
		driver.findElement(By.xpath("//a[text()='Americas']")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);
		
		// Description
		driver.findElement(By.id("sc_request.description")).sendKeys("Service Category Description");
		// Short Description
		driver.findElement(By.id("sc_request.short_description")).sendKeys("Service Category Short Description");
		// Special Instructions
		driver.findElement(By.id("sc_request.special_instructions")).sendKeys("Special Instructions");
		
		// Click Update
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		
		// Validate the values are reflected
		String reqStateCreated = driver.findElement(By.xpath("//table[@id='sc_request_table']//tbody[1]//tr[1]//td[6]")).getText();
		System.out.println(reqStateCreated);
		
		if (reqStateCreated.equals("Approved")) {
			System.out.println("Request State should be updated successfully as : "  + reqStateCreated);
		} else {
			System.out.println("Request State not updated");
		}
		
		// close the browser
		driver.close();
		
	}
}


