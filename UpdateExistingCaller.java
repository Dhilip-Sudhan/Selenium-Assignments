package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingCaller {

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
		
		// Enter Caller in Filter textbox
		driver.findElement(By.id("filter")).sendKeys("Caller", Keys.ENTER);
		
		// Click Caller
		driver.findElement(By.xpath("//div[text()='Callers']")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		
		// Enter the First Name  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Sudhan", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Caller
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(2000);
		
		// Enter Business Phone
		driver.findElement(By.id("sys_user.phone")).clear();
		driver.findElement(By.id("sys_user.phone")).sendKeys("2684");
		
		// Capture Business Phone
		String businessPhoneUpdated = driver.findElement(By.id("sys_user.phone")).getAttribute("value");
		
		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		
		// Verify the modified Business phone is displayed
		String buisnessPhoneModified = driver.findElement(By.xpath("//table[@id='sys_user_table']//tbody[1]//tr[1]//td[5]")).getText();
		System.out.println(buisnessPhoneModified);
		
		if (businessPhoneUpdated.equals(buisnessPhoneModified)) {
			System.out.println("Updated Business Phone should be created successfully : " + businessPhoneUpdated);
		} else {
			System.out.println("Updated Business Phone not updated");

		}

		// Close the Browser
		driver.close();
				
	}

}
