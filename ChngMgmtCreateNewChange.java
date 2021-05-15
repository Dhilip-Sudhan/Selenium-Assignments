package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChngMgmtCreateNewChange {

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
		
		// Click Create New
		driver.findElement(By.xpath("(//span[text()='Change']/following::div[text()='Create New'])[1]")).click();
		Thread.sleep(2000);
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Click Normal
		driver.findElement(By.xpath("(//div[@class='change-model-card-component'])[2]")).click();
		Thread.sleep(3000);
		
		// Capture the Created Change number
		String chNumber = driver.findElement(By.id("change_request.number")).getAttribute("value");
		System.out.println("The Created Change number is : " + chNumber);
		
		// Enter Short Description
		driver.findElement(By.id("change_request.short_description")).sendKeys("Dhilip Change Auto");
		
		// Click Submit
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		Thread.sleep(3000);
		
		// Switch to Default Content
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		// Click Open
		driver.findElement(By.xpath("//span[text()='Change']/following::div[text()='Open']")).click();
		Thread.sleep(5000);
		
		// Switch to Iframe
		iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the Change number  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(chNumber, Keys.ENTER);
		Thread.sleep(2000);
		String changeNumberCreated = driver.findElement(By.xpath("//table[@id='change_request_table']//tbody[1]//tr[1]//td[3]")).getText();
		System.out.println(changeNumberCreated);
		
		if (chNumber.equals(changeNumberCreated)) {
			System.out.println("New Change number should be created successfully");
		} else {
			System.out.println("New chNumber number not created");

		}
		
		// Close the Browser
		driver.close();
				
		
	}	
		
}		
