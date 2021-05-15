package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingChatQueueEntry {

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
		
		// click My Work
		driver.findElement(By.xpath("(//*[text()='My Work'])[1]")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the Chat queue number  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Chat Queue",Keys.ENTER);
		Thread.sleep(2000);
		
		// Select the Created Request
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(3000);
		
		// Select State
		WebElement stateDropDown = driver.findElement(By.id("chat_queue_entry.state"));
		Select steDropdown = new Select(stateDropDown); 
		steDropdown.selectByVisibleText("Work In Progress");
		Thread.sleep(1000);
		
		// Select Prioity
		WebElement prioityDropDown = driver.findElement(By.id("chat_queue_entry.priority"));
		Select priorDropdown = new Select(prioityDropDown); 
		priorDropdown.selectByVisibleText("3 - Moderate");
		Thread.sleep(1000);
		
		// Click Update button
		driver.findElement(By.xpath("(//button[text()='Update'])[1]")).click();
		Thread.sleep(3000);
		
		// Validate the values are reflected
		String priorityCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[4]")).getText();
		String stateCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[5]")).getText();
		System.out.println(priorityCreated);
		System.out.println(stateCreated);
		
		if (priorityCreated.equals("3 - Moderate")) {
			System.out.println("Prioity should be created successfully : " + priorityCreated);
		} else {
			System.out.println("Prioity not created");
		}

		if (stateCreated.equals("Work In Progress")) {
			System.out.println("State should be created successfully : " + stateCreated);
		} else {
			System.out.println("State not created");
		}
		
		// Close the Browser
		driver.close();
				
		
		
		


	}

}
