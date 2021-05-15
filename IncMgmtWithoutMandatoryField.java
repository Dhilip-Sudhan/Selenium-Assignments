package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class IncMgmtWithoutMandatoryField {

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
		driver.findElement(By.xpath("//span[text()='Incident']/following::div[text()='Create New']")).click();
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Click Submit button
		driver.findElement(By.xpath("(//button[@value='sysverb_insert'])[2]")).click();
		Thread.sleep(2000);
		
		String errorMessage = driver.findElement(By.xpath("//div[@class='outputmsg_div']//following::span[1]")).getText();
		if (errorMessage.contains("Short description, Caller")) {
			System.out.println("Valid Error message should be displayed");
		} else {
			System.out.println("Valid Error message not displayed");
		}
	
					
	}

}
