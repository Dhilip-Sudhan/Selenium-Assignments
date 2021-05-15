package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteRequest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
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
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(5000);
		
		String reqNumber = driver.findElement(By.id("sc_request.number")).getAttribute("value");
		System.out.println("The Request number is : " + reqNumber);
		Thread.sleep(2000);
		
		// click Delete button
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		Thread.sleep(2000);
		
		// click Delete button
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);

		// validate the deleted Incident is displayed
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(reqNumber, Keys.ENTER);
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