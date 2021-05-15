package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewCaller {

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
		Thread.sleep(2000);
		
		// Click Caller
		driver.findElement(By.xpath("//div[text()='Callers']")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		//Click New button
		driver.findElement(By.id("sysverb_new")).click();
		Thread.sleep(2000);
		
		// Enter First Name
		driver.findElement(By.id("sys_user.first_name")).sendKeys("Dhilip Sudhan");
		
		// Enter Last Name
		driver.findElement(By.id("sys_user.last_name")).sendKeys("Muralidharan");
		
		// Capture Firstname and Lastname
		String firstName = driver.findElement(By.id("sys_user.first_name")).getAttribute("value");
		String lastName = driver.findElement(By.id("sys_user.last_name")).getAttribute("value");
		
		// Enter Email ID
		driver.findElement(By.id("sys_user.email")).sendKeys("abcd@xyz.com");
		
		// Enter Business Phone
		driver.findElement(By.id("sys_user.phone")).sendKeys("1234");
		
		// Capture Business Phone
		String businessPhone = driver.findElement(By.id("sys_user.phone")).getAttribute("value");
		
		// Enter Title
		driver.findElement(By.id("sys_user.title")).sendKeys("Automation");
		
		// Enter Mobile number
		driver.findElement(By.id("sys_user.mobile_phone")).sendKeys("13579");
		
		// Click Submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		Thread.sleep(3000);
		
		// Enter the First Name  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(lastName, Keys.ENTER);
		Thread.sleep(2000);
		String lastNameCreated = driver.findElement(By.xpath("//table[@id='sys_user_table']//tbody[1]//tr[1]//td[3]")).getText();
		System.out.println(lastNameCreated);
		
		if (lastName.equals(lastNameCreated)) {
			System.out.println("Caller should be created successfully : " + lastName);
		} else {
			System.out.println("Caller not created");

		}

		// Close the Browser
		//driver.close();
				
	}

}
