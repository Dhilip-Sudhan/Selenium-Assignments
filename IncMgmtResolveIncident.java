package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IncMgmtResolveIncident {

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
		
		// Switch back to Iframe
		//driver.switchTo().frame(iframe);
		
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
		
		// Select State and update it as In-Progress
		WebElement stateDropDown = driver.findElement(By.xpath("//select[@id='incident.state']"));
		Select steDropdown = new Select(stateDropDown); 
		steDropdown.selectByVisibleText("Resolved");
		Thread.sleep(1000);

		// Update in Work Notes
		driver.findElement(By.id("activity-stream-work_notes-textarea")).sendKeys("Updated the State and Urgency");
		
		// Click Resolution Information
		driver.findElement(By.xpath("//span[text()='Resolution Information']")).click();
		Thread.sleep(1000);
		
		// Select Resolution code
		WebElement resCodeDropDown = driver.findElement(By.xpath("//select[@id='incident.close_code']"));
		Select resDropdown = new Select(resCodeDropDown); 
		resDropdown.selectByVisibleText("Solved (Work Around)");
		Thread.sleep(1000);
		
		// Enter Resolution Notes
		driver.findElement(By.id("incident.close_notes")).sendKeys("Resolution notes");
		
		// Click Resolve button
		driver.findElement(By.xpath("(//button[text()='Resolve'])[1]")).click();
		Thread.sleep(3000);
				
		// Capture the value which has been updated
		String stateStatus = driver.findElement(By.xpath("//table[@id='incident_table']//tbody[1]//tr[1]//td[8]")).getText();
		System.out.println(stateStatus);
		
		// Validate the State as Resolved
		if (stateStatus.equals("Resolved")) {
			System.out.println("The Status should be displayed as : " + stateStatus);
		} else {
			System.out.println("The Status is not reflecting as expected");
		}
		
		// Close the Browser
		driver.close();
				
		
		
	}

}
