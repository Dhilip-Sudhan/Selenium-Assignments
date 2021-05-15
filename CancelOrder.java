package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CancelOrder {

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
		aproveDropdown.selectByVisibleText("Rejected");
		Thread.sleep(1000);
		
		// Update Requested State field to Approved
		WebElement requestStateDropDown = driver.findElement(By.xpath("sc_request.request_state"));
		Select requestDropdown = new Select(requestStateDropDown); 
		requestDropdown.selectByVisibleText("Closed Cancelled");
		Thread.sleep(1000);
		
		// Click Cancel Request
		driver.findElement(By.xpath("(//button[text()='Cancel Request'])[1]")).click();
		Thread.sleep(2000);
		
		// Enter the Request number to Cancel the Order
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("REQ0010058", Keys.ENTER);
		Thread.sleep(2000);
		String noRecordMessage = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		System.out.println(noRecordMessage);
		
		if (noRecordMessage.equals("No records to display")) {
			System.out.println("No Records to display message should be displayed");
		} else {
			System.out.println("No Records to display message is not displayed");
		}
			
		// Close the Browser
		driver.close();
		
		
		
	}
}


