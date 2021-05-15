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

public class IncMgmtCreateNewIncident {

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
		
		// Click Create New
		driver.findElement(By.xpath("//span[text()='Incident']/following::div[text()='Create New']")).click();
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Capture the Incident number
		String IncNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("The Created Incident number is : " + IncNumber);
		
		// Select the Caller from the dropdown
		driver.findElement(By.xpath("//span[text()='Caller']//following::span[2]")).click();
		
		//	Print the number of windows open
		Set<String> allwindowHandles = driver.getWindowHandles();
		System.out.println(allwindowHandles.size());

		//	Get the second window from the Set
		List<String> lstofWindows = new ArrayList<String>(allwindowHandles);

		//	Get the Second windows handle
		String SecondWindowHandle = lstofWindows.get(1);
		
		//	Move the control to Second window
		driver.switchTo().window(SecondWindowHandle);
		
		// Select one of the Caller from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);
		
		// Enter Short Description
		driver.findElement(By.id("incident.short_description")).sendKeys("Dhilip Auto");
		
		// Click Submit button
		driver.findElement(By.xpath("(//button[@value='sysverb_insert'])[2]")).click();
		Thread.sleep(2000);
		
		// Copy the created incident and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(IncNumber, Keys.ENTER);
		Thread.sleep(2000);
		String IncNumberCreated = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println(IncNumberCreated);
		
		if (IncNumber.equals(IncNumberCreated)) {
			System.out.println("New Incident should be created successfully");
		} else {
			System.out.println("New Incident not created");

		}
		
		// Close the Browser
		driver.close();
				

	}

}
