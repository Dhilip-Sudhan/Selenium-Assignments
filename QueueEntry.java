package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QueueEntry {

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
		
		// click My Work
		driver.findElement(By.xpath("(//*[text()='My Work'])[1]")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Click new button
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		Thread.sleep(2000);
		
		//Click Chat Queue Entry
		driver.findElement(By.linkText("Chat_queue_entry")).click();
		Thread.sleep(2000);
		
		// Capture the Chat Queue entry
		String chatNumber = driver.findElement(By.id("chat_queue_entry.number")).getAttribute("value");
		System.out.println("The Chat Queue number is : " + chatNumber);
		
		// Enter Short Description
		driver.findElement(By.id("chat_queue_entry.short_description")).sendKeys("Dhilip Chat Queue");
		
		// click Submit button
		driver.findElement(By.id("sysverb_insert")).click();
		Thread.sleep(2000);
		
		// Enter the Chat queue number  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(chatNumber, Keys.ENTER);
		Thread.sleep(2000);
		String chatQueuenumCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[3]")).getText();
		System.out.println(chatQueuenumCreated);
		
		if (chatNumber.equals(chatQueuenumCreated)) {
			System.out.println("Chat Queue number should be created successfully : " + chatNumber);
		} else {
			System.out.println("Chat Queue number not created");

		}

		
		// Close the Browser
		driver.close();
	}

}
