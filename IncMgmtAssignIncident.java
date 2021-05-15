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

public class IncMgmtAssignIncident {

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
		driver.findElement(By.id("filter")).sendKeys("Incident", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Open']")).click();
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Search for the existing incident and click on the incident
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Auto", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(2000);
		
		// a) Select the Assignment group and select Software Group
		driver.findElement(By.xpath("//span[text()='Assignment group']//following::span[2]")).click();
		
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
		searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Software", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select one of the value from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);
		
		
		// b) Select the Assignment To and select Software Group
		driver.findElement(By.xpath("//span[text()='Assigned to']//following::span[2]")).click();
		
		// Print the number of windows open
		allwindowHandles = driver.getWindowHandles();
		System.out.println(allwindowHandles.size());

		//	Get the second window from the Set
		lstofWindows = new ArrayList<String>(allwindowHandles);

		//	Get the Second windows handle
		SecondWindowHandle = lstofWindows.get(1);
		
		//	Move the control to Second window
		driver.switchTo().window(SecondWindowHandle);
		
		// Enter Software from the text box and click Enter
		searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("ITIL User", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select one of the value from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);
		
		// b) Select the Assignment To and select ITIL user
		//driver.findElement(By.xpath("//input[@id='sys_display.incident.assigned_to']")).sendKeys("ITIL User", Keys.ENTER);
		//Thread.sleep(3000);
		
		// Update in Work Notes
		//driver.findElement(By.id("activity-stream-work_notes-textarea")).sendKeys("Updated the State and Urgency");
			
		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(3000);
		
		// Capture the value which has been updated
		String assignmentGroup = driver.findElement(By.xpath("//table[@id='incident_table']//tbody[1]//tr[1]//td[10]")).getText();
		String assignedTo = driver.findElement(By.xpath("//table[@id='incident_table']//tbody[1]//tr[1]//td[11]")).getText();
		System.out.println(assignmentGroup);
		System.out.println(assignedTo);
	
		// Validate the Assignment Group is displayed as Software
		if (assignmentGroup.equals("Software")) {
			System.out.println("The Assignment Group should be displayed as : " + assignmentGroup);
		} else {
			System.out.println("The Assignment Group is not reflecting as expected");
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
