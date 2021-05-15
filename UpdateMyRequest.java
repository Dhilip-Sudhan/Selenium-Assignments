package AssignmentWeek4Day2;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateMyRequest {

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
		Thread.sleep(5000);
		
		// Click My Work
		driver.findElement(By.xpath("(//*[text()='My Work'])[1]")).click();
		Thread.sleep(3000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the change Number
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Request number", Keys.ENTER);
		//driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Dhilip Test", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(5000);
		
		//Select Rhode Island
		driver.findElement(By.xpath("//input[@id='sys_display.sc_request.location']")).sendKeys("Rhode Island", Keys.ENTER);
		Thread.sleep(2000);
		
		// Click Date Icon
		driver.findElement(By.xpath("(//span[@class='icon-calendar icon'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@aria-selected='true']//following::td/a)[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='GwtDateTimePicker_ok']")).click();
		Thread.sleep(2000);
		
		// Enter Short Description
		driver.findElement(By.id("sc_request.short_description")).clear();
		driver.findElement(By.id("sc_request.short_description")).sendKeys("Dhilip Request Creation");
		Thread.sleep(2000);
		String updatedDesc = driver.findElement(By.id("sc_request.short_description")).getAttribute("value");
		System.out.println(updatedDesc);
		
		// click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		
		String descriptionCreated = driver.findElement(By.xpath("//table[@id='task_table']//tbody[1]//tr[1]//td[7]")).getText();
		System.out.println(descriptionCreated);
		
		
		if (descriptionCreated.equals(updatedDesc)) { 
			System.out.println("The Updated Description is displayed");
		} else {
			System.out.println("The Updated Description is not displayed");
		}
		
		// Close the Browser
		driver.close();
				
				
}

}