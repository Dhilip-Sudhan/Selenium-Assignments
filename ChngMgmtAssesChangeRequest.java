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

public class ChngMgmtAssesChangeRequest {

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
		driver.findElement(By.id("filter")).sendKeys("Change", Keys.ENTER);
		
		// Click Open
		driver.findElement(By.xpath("(//span[text()='Change']/following::div[text()='Open'])[1]")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the change Number
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Change Auto updated", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(2000);
		
		// Select the state as Asses
		WebElement stateDropDown = driver.findElement(By.xpath("//select[@id='change_request.state']"));
		Select steDropdown = new Select(stateDropDown); 
		steDropdown.selectByVisibleText("Assess");
		Thread.sleep(1000);
		
		// Select the Assignment group
		driver.findElement(By.xpath("//span[text()='Assignment group']//following::span[2]")).click();
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
		
		// Enter Template Management from the text box and click Enter
		driver.findElement(By.xpath("//a[text()='Software']")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch to Iframe
		iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);

		// b) Select the Assignment To and select ITIL user
		driver.findElement(By.xpath("//input[@id='sys_display.change_request.assigned_to']")).sendKeys("ITIL User", Keys.ENTER);
		Thread.sleep(3000);
		
		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(3000);
		
		// Capture the value which has been updated
		String stateUpdated = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[6]")).getText();
		String assignedTo = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[10]")).getText();
		System.out.println(stateUpdated);
		System.out.println(assignedTo);
	
		// Validate the Requested By is displayed as Software
		if (stateUpdated.equals("Assess")) {
			System.out.println("The State should be displayed as : " + stateUpdated);
		} else {
			System.out.println("The Requested ByState is not reflecting as expected");
		}
		
		// Validate the Assigned To is displayed as ITIL User
		if (assignedTo.equals("ITIL User")) {
			System.out.println("The Assigned To should be displayed as : " + assignedTo);
		} else {
			System.out.println("The Assigned To is not displayed as Expected");
		}
		
		// Close the Browser
		driver.close();
				
		
	}	
		
}		
