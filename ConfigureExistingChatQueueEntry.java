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

public class ConfigureExistingChatQueueEntry {

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
		steDropdown.selectByVisibleText("Closed Abandoned");
		Thread.sleep(1000);
		
		// Select Prioity
		WebElement prioityDropDown = driver.findElement(By.id("chat_queue_entry.priority"));
		Select priorDropdown = new Select(prioityDropDown); 
		priorDropdown.selectByVisibleText("1 - Critical");
		Thread.sleep(1000);
		
		// Click Configuration Item
		driver.findElement(By.xpath("//span[text()='Configuration item']//following::span[2]")).click();
		Thread.sleep(3000);
		
		// Print the number of windows open
		Set<String> allwindowHandles = driver.getWindowHandles();
		System.out.println(allwindowHandles.size());

		//	Get the second window from the Set
		List<String> lstofWindows = new ArrayList<String>(allwindowHandles);

		//	Get the Second windows handle
		String SecondWindowHandle = lstofWindows.get(1);
		
		//	Move the control to Second window
		driver.switchTo().window(SecondWindowHandle);
		
		// Enter Software from the text box and click Enter
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("PS Apache02", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select one of the value from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);

		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(3000);
		
		// Validate the values are reflected
		String priorityCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[4]")).getText();
		String stateCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[5]")).getText();
		String configItemCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[9]")).getText();
		System.out.println(priorityCreated);
		System.out.println(stateCreated);
		System.out.println(configItemCreated);
		
		if (priorityCreated.equals("1 - Critical")) {
			System.out.println("Prioity should be created successfully : " + priorityCreated);
		} else {
			System.out.println("Prioity not created");
		}

		if (stateCreated.equals("Closed Abandoned")) {
			System.out.println("State should be created successfully : " + stateCreated);
		} else {
			System.out.println("State not created");
		}
		
		if (configItemCreated.equals("PS Apache02")) {
			System.out.println("Configuration Item should be created successfully : " + configItemCreated);
		} else {
			System.out.println("Configuration Item not created");
		}
		
		
		// Close the Browser
		driver.close();

	}

}
