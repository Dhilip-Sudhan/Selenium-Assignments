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

public class UpdateChangeProposal {

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
		driver.findElement(By.id("filter")).sendKeys("Proposal", Keys.ENTER);
		
		// My Proposal
		driver.findElement(By.xpath("//div[text()='My Proposals']")).click();
		Thread.sleep(2000);
				
		// Switch to Iframe
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		// Enter the Proposal number and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("for text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Proposal Created Automation", Keys.ENTER);
		Thread.sleep(2000);
		
		// Open the existing Incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		Thread.sleep(2000);
		
		// select State as In-progress
		WebElement stateDropDown = driver.findElement(By.xpath("//select[@id='std_change_proposal.state']"));
		Select steDropdown = new Select(stateDropDown); 
		steDropdown.selectByVisibleText("In Progress");
		Thread.sleep(1000);
		
		// Select the Category
		driver.findElement(By.xpath("//span[text()='Category']//following::span[2]")).click();
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
		
		// Enter Template Management from the text box and click Enter
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Template Management", Keys.ENTER);
		Thread.sleep(2000);
		
		// Select one of the value from  the second window
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		Thread.sleep(2000);
		
		// Move to First Window
		driver.switchTo().window(lstofWindows.get(0));
		
		// Switch to Iframe
		iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
				
		// Description
		//driver.findElement(By.id("std_change_proposal.description")).sendKeys("Proposal Description");
		
		// Business Justification
		//driver.findElement(By.id("std_change_proposal.business_justification")).sendKeys("Proposal Business Justification");
		
		// Work Notes
		//driver.findElement(By.id("std_change_proposal.work_notes")).sendKeys("Work Notes");
		
		// Click Change Request Values
		driver.findElement(By.xpath("//span[text()='Change Request values']")).click();
		Thread.sleep(2000);
		
		// Assignment Group
		//String assgnGroup = driver.findElement(By.id("//*[@id='select2-chosen-16']")).getAttribute("attribue");
		//if (assgnGroup.equals("Assignment Group")) {
			//driver.findElement(By.id("s2id_autogen6_search")).sendKeys("Assignment Group",Keys.ENTER);
			//Thread.sleep(2000);
			//driver.findElement(By.xpath("//input[@class='pull-left form-control filter-reference-input']")).sendKeys("Assignment Group Description",Keys.ENTER);
			
			driver.findElement(By.xpath("//button[@class='icon-search btn btn-default filerTableAction']")).click();
			// Print the number of windows open
			allwindowHandles = driver.getWindowHandles();
			System.out.println(allwindowHandles.size());

			//	Get the second window from the Set
			lstofWindows = new ArrayList<String>(allwindowHandles);

			//	Get the Second windows handle
			SecondWindowHandle = lstofWindows.get(1);
			
			//	Move the control to Second window
			driver.switchTo().window(SecondWindowHandle);
			
			// Enter Template Management from the text box and click Enter
			//driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Analytics Settings MAnagers", Keys.ENTER);
			//Thread.sleep(2000);
			
			// Select one of the value from  the second window
			driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
			Thread.sleep(2000);
			
			// Move to First Window
			driver.switchTo().window(lstofWindows.get(0));
		//} else {
			//System.out.println("The Assignment Group listbox is not displayed");
		//}
		
		// Switch to Iframe
		iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);

		// Justification
		//String justification = driver.findElement(By.id("//*[@id='select2-chosen-18']")).getAttribute("attribue");
		//if (justification.equals("Justification")) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@aria-label='Input value for field: Justification']")).sendKeys("Justification Description",Keys.ENTER);
		//} else {
			//System.out.println("The Justification listbox is not displayed");
		//}
		
		//Risk and Impact analysis
		//String riskImpact = driver.findElement(By.id("//*[@id='select2-chosen-20']")).getAttribute("attribue");
		//if (riskImpact.equals("Risk and impact analysis")) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@aria-label='Input value for field: Risk and impact analysis']")).sendKeys("Risk and impact analysis Description",Keys.ENTER);
		//} else {
			//System.out.println("The Risk and Impact analysis listbox is not displayed");
		//}
		
		// click Update button
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		
		// close the Window		
		driver.close();
		
		
		//driver.findElement(By.xpath("//span[text()='Change Request values']//following::a[2]")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.id("s2id_autogen6_search")).sendKeys("Justification",Keys.ENTER);
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[text()='Change Request values']//following::a[2]")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.id("s2id_autogen6_search")).sendKeys("Justification",Keys.ENTER);
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[text()='Change Request values']//following::a[3]")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.id("s2id_autogen6_search")).sendKeys("Risk and Impact analysis",Keys.ENTER);
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("(//textarea[@class='filerTableInput form-control'])[2]")).sendKeys("Risk and Impact analysis",Keys.ENTER);
		
		
		
				
		
		

	}

}
