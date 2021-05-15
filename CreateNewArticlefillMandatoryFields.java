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

public class CreateNewArticlefillMandatoryFields {

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
		
		// Once already existing Created Knowledge
		driver.findElement(By.xpath("//ul[@aria-label='Submodules for Module: Articles']//li[6]")).click();
		
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the First Name  and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Create New Article", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the created knowledge
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(3000);
		
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
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		Thread.sleep(2000);
		
		//Click Ok button
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);
		
		// Click Update button
		driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();
		Thread.sleep(3000);
		
		// Enter the First Name  and validate whether it is created or not
		searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Create New Article", Keys.ENTER);
		Thread.sleep(2000);
		String updatedCategory1 = driver.findElement(By.xpath("//table[@id='kb_knowledge_table']//tbody[1]//tr[1]//td[6]")).getText();
		String updatedCategory2 = driver.findElement(By.xpath("//table[@id='kb_knowledge_table']//tbody[1]//tr[1]//td[10]")).getText();
		System.out.println(updatedCategory1);
		System.out.println(updatedCategory2);
		
		if (updatedCategory1.equals("Java")) {
			System.out.println("Updated Category should be updated successfully : "  + updatedCategory1);
		} else {
			System.out.println("Updated Category not updated");
		}
		
		if (updatedCategory2.equals("IT")) {
			System.out.println("Updated Category should be updated successfully : "  + updatedCategory2);
		} else {
			System.out.println("Updated Category not updated");

		}

		// Close the Browser
		driver.close();
			
		}

}
