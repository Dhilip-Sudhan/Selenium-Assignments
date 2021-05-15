package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrder {

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
		driver.findElement(By.id("filter")).sendKeys("Service Catalog", Keys.ENTER);
		
		// click Service catalog
		driver.findElement(By.xpath("(//span[text()='Self-Service']//following::div[text()='Service Catalog'])[1]")).click();
		Thread.sleep(3000);

		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
				
		// Click Mobile
		driver.findElement(By.xpath("//h2[text()[normalize-space()='Mobiles']]")).click();
		Thread.sleep(1000);

		// Select Apple Iphone 6s
		driver.findElement(By.xpath("//strong[text()='iPhone 6s']")).click();
		Thread.sleep(1000);
		
		// Select the Color as Pink
		WebElement colorDropDown = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[1]"));
		Select clrDropdown = new Select(colorDropDown); 
		clrDropdown.selectByVisibleText("Gold");
		Thread.sleep(2000);
		
		// Select Storgae as 128 GB
		WebElement storageDropDown = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[2]"));
		Select stroreDropdown = new Select(storageDropDown); 
		stroreDropdown.selectByVisibleText("128");
		Thread.sleep(2000);
		
		// Click Order now
		driver.findElement(By.id("oi_order_now_button")).click();
		Thread.sleep(5000);
		
		// Capture the Request number
		String reqNumber = driver.findElement(By.xpath("//*[text()='Request Number: ']//following::b[1]")).getText();
		System.out.println(reqNumber);
		
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		
		// Click Requests
		driver.findElement(By.xpath("//div[text()='Requests']")).click();
		Thread.sleep(5000);
		
		// Switch to Iframe
		iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the created Request number
		WebElement reqNumberDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select reqDropdown = new Select(reqNumberDropDown); 
		reqDropdown.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(reqNumber, Keys.ENTER);
		Thread.sleep(2000);
		
		// Select the Created Request
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(3000);
		
		// Click Delete button
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		Thread.sleep(2000);
		
		// Click Ok button
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);
		
		// Enter the Request number to Delete the Order
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(reqNumber, Keys.ENTER);
		Thread.sleep(2000);
		String noRecordMessage = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		System.out.println(noRecordMessage);
		
		if (noRecordMessage.equals("No records to display")) {
			System.out.println("No Records to display message should be displayed");
		} else {
			System.out.println("No Records to display message is not displayed");
		}
			
		// Close the Browser
		driver.close();
		
		
		


	}

}
