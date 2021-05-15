package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IncMgmtpdateExistingIncident {

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
		
		// Select Urgency and update it as high
		WebElement urgencyDropDown = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select urgDropdown = new Select(urgencyDropDown); 
		urgDropdown.selectByVisibleText("1 - High");
		Thread.sleep(2000);
		
		// Select State and update it as In-Progress
		WebElement stateDropDown = driver.findElement(By.xpath("//select[@id='incident.state']"));
		Select steDropdown = new Select(stateDropDown); 
		steDropdown.selectByVisibleText("In Progress");
		Thread.sleep(2000);
		
		// Update in Work Notes
		driver.findElement(By.id("activity-stream-work_notes-textarea")).sendKeys("Updated the State and Urgency");
			
		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(3000);
		
		// Capture the value which has been updated
		String priorityValue = driver.findElement(By.xpath("//table[@id='incident_table']//tbody[1]//tr[1]//td[7]")).getText();
		String stateValue = driver.findElement(By.xpath("//table[@id='incident_table']//tbody[1]//tr[1]//td[8]")).getText();
		System.out.println(priorityValue);
		System.out.println(stateValue);
		
		// Validate the Priority is displayed as 3 - Moderate
		if (priorityValue.equals("3 - Moderate")) {
			System.out.println("The prioity Status should be displayed as : " + priorityValue);
		} else {
			System.out.println("The Priority Status is not reflecting as expected");
		}
		
		// Validate the State is displayed as In-Progress
		if (stateValue.equals("In Progress")) {
			System.out.println("The status should be displayed as : " + stateValue);
		} else {
			System.out.println("The status is not displayed as Expected");
		}
			
		// Close the Browser
		driver.close();
				
	
	}

}
