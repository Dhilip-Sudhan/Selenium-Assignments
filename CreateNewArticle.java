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

public class CreateNewArticle {

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
		driver.findElement(By.id("filter")).sendKeys("Knowledge", Keys.ENTER);
		Thread.sleep(5000);
		
		// Click Create New Article
		driver.findElement(By.xpath("(//ul[@aria-label='Submodules for Module: Articles']//a[1])[1]")).click();
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		
		// Capture the Created Article number
		String arNumber = driver.findElement(By.id("sys_readonly.kb_knowledge.number")).getAttribute("value");
		System.out.println("The Created Article number is : " + arNumber);
		
		// Click Knowledge Base and Select IT
		driver.findElement(By.xpath("//span[text()='Knowledge base']//following::span[2]")).click();
		Thread.sleep(2000);
		
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
		WebElement knowledgeDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select knowDropdown = new Select(knowledgeDropDown); 
		knowDropdown.selectByVisibleText("Title");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("IT", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select one of the value from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch back to Iframe
		driver.switchTo().frame(iframe);

		// Select Category and Select Value as Java
		driver.findElement(By.xpath("//span[text()='Category']//following::span[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='IT']")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[text()='Java']")).click();
		driver.findElement(By.xpath("//span[text()='Android']")).click();
		Thread.sleep(2000);
		
		//Click Ok button
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);
		
		// Enter Short Description
		driver.findElement(By.id("kb_knowledge.short_description")).sendKeys("Create New Article");
		
		// Click Submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		Thread.sleep(3000);
		
		// Enter the First Name  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Create New Article", Keys.ENTER);
		Thread.sleep(2000);
		String arNumberCreated = driver.findElement(By.xpath("//table[@id='kb_knowledge_table']//tbody[1]//tr[1]//td[3]")).getText();
		System.out.println(arNumberCreated);
		
		if (arNumber.equals(arNumberCreated)) {
			System.out.println("New Article should be created successfully");
		} else {
			System.out.println("New Article not created");

		}

	// Close the Browser
		driver.close();
		
		
	}

}
