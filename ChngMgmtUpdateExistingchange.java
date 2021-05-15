package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChngMgmtUpdateExistingchange {

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
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Change Auto", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(2000);
		
		// Modify the description value
		driver.findElement(By.id("change_request.short_description")).clear();
		driver.findElement(By.id("change_request.short_description")).sendKeys("Dhilip Change Auto updated");
		Thread.sleep(3000);
		String text = driver.findElement(By.id("change_request.short_description")).getAttribute("value");
		System.out.println("the text is : " + text);
		// click Schedule
		driver.findElement(By.xpath("//span[text()='Schedule']")).click();
		Thread.sleep(2000);
		
		// Select Date from Planned Start Date
		driver.findElement(By.xpath("(//span[@class='icon-calendar icon'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='GwtDateTimePicker_ok']")).click();
		Thread.sleep(2000);
		String plannedStartDateExpected = driver.findElement(By.xpath("(//span[@class='icon-calendar icon'])[1]")).getAttribute("value");
		System.out.println("The Start Date is : " + plannedStartDateExpected);
		
		// Select Date from Planned To Date
		driver.findElement(By.xpath("(//span[@class='icon-calendar icon'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='GwtDateTimePicker_ok']")).click();
		Thread.sleep(2000);
		String plannedEndDateExpected = driver.findElement(By.xpath("(//span[@class='icon-calendar icon'])[2]")).getAttribute("value");
		System.out.println("The Start Date is : " + plannedEndDateExpected);
		
		// check the check box CAB Required
		driver.findElement(By.xpath("//label[@id='label.ni.change_request.cab_required']")).click();
		Thread.sleep(2000);
		
		//Enter CAB date and select the Date
		driver.findElement(By.xpath("//span[text()='CAB date']//following::span[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[text()='Go to Today']")).click();
		Thread.sleep(2000);
				
		// Click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(3000);
		
		// Capture the value which has been updated
		String descUpdatedActual = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[4]")).getText();
		String plannedStartDateActual = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[7]")).getText();
		String plannedEndDateActual = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[8]")).getText();
		System.out.println(plannedStartDateActual);
		System.out.println(plannedEndDateActual);
		System.out.println(descUpdatedActual);
		
		
		// Validate the Description is updated
		if (text.equals(descUpdatedActual)) {
			System.out.println("Description should be updated");
		} else {
			System.out.println("Description is not updated");
		}
		
		// Validate the Planned Start Date is displayed as Expected
		/*if (plannedStartDateExpected.equals(plannedStartDateActual)) {
			System.out.println("The Planned Start Date should be displayed as Same");
		} else {
			System.out.println("The Planned Start Date is not displayed as Same");
		}
		
		// Validate the Planned To Date is displayed as Expected
		if (plannedEndDateExpected.equals(plannedEndDateActual)) {
			System.out.println("The Planned To Date should be displayed as Same");
		} else {
			System.out.println("The Planned To Date is not displayed as Same");
		} */
		

		// Close the Browser
		driver.close();
				
		
		
	}	
		
}		
