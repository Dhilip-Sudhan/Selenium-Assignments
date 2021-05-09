package AssignmentWeek4Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeLead {

	public static void main(String[] args) throws InterruptedException {
		
		//Pseudo Code
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//* 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		 
		//* 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//* 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		
		//* 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//* 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		
		//* 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.linkText("Merge Contacts")).click();
		
		//* 7. Click on Widget of From Contact
		driver.findElement(By.xpath("//span[text()='From Contact']/following::img")).click();
		
		// Switch to Second Window
		Set<String> allwindowHandles = driver.getWindowHandles();
		List<String> listWindowHandles = new ArrayList<String>(allwindowHandles);
		driver.switchTo().window(listWindowHandles.get(1));
		
		//* 8. Click on First Resulting Contact
		//driver.findElement(By.name("id")).sendKeys("10185");
		//driver.findElement(By.xpath("//button[text()='Find Contacts']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody[1]/tr[1]/td[1]/div[1]/a[1]")).click();
		
		//Move the Control to the First Page
		driver.switchTo().window(listWindowHandles.get(0));
		
		//* 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//span[text()='To Contact']/following::img")).click();
		Thread.sleep(2000);
		
		// Switch to Second Window
		allwindowHandles = driver.getWindowHandles();
		listWindowHandles = new ArrayList<String>(allwindowHandles);
		driver.switchTo().window(listWindowHandles.get(1));
		
		 //* 10. Click on Second Resulting Contact
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/following::table/tbody[1]/tr[1]/td[1]/div[1]/a[1]")).click();
		Thread.sleep(2000);
		
		//Move the Control to the First Page
		driver.switchTo().window(listWindowHandles.get(0));
		
		//* 11. Click on Merge button using Xpath Locator
		driver.findElement(By.className("buttonDangerous")).click();
		Thread.sleep(2000);
		
		//* 12. Accept the Alert
		
		// 	switch to Alert
		Alert alert = driver.switchTo().alert();
		
		// 	Click Ok button in Alert
		alert.accept();
 
		//* 13. Verify the title of the page
		String title = driver.getTitle();
		System.out.println("The title of the page is : " + title);
		 
		driver.close();
	}

}
