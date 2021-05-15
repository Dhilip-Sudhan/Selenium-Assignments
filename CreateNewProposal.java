package AssignmentWeek4Day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewProposal {

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
		Thread.sleep(2000);
		
		// My Proposal
		driver.findElement(By.xpath("//div[text()='My Proposals']")).click();
		Thread.sleep(2000);
		
		
		// Switch to Frame
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);
		Thread.sleep(2000);
		
		//Click New button
		driver.findElement(By.id("sysverb_new")).click();
		Thread.sleep(2000);
		
		// Capture the created proposal number
		String proposalNumber = driver.findElement(By.id("std_change_proposal.number")).getAttribute("value");
		System.out.println("The Created Proposal number is : " + proposalNumber);
		
		// Enter Template Description
		driver.findElement(By.id("std_change_proposal.short_description")).sendKeys("Proposal Created Automation");
		Thread.sleep(1000);
		
		// Capture the Description
		String descText = driver.findElement(By.id("std_change_proposal.short_description")).getText();
		//System.out.println(descText);
		
		// click Submit button
		driver.findElement(By.id("sysverb_insert")).click();
		Thread.sleep(2000);
			
		// Enter the Proposal number and validate whether it is created or not
		WebElement searchDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select srchDropdown = new Select(searchDropDown); 
		srchDropdown.selectByVisibleText("Number");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(proposalNumber, Keys.ENTER);
		Thread.sleep(2000);
		String proposalNumberCreated = driver.findElement(By.xpath("//table[@id='std_change_proposal_table']//tbody[1]//tr[1]//td[3]")).getText();
		String descriptionCreated = driver.findElement(By.xpath("//table[@id='std_change_proposal_table']//tbody[1]//tr[1]//td[4]")).getText();
		System.out.println(proposalNumberCreated);
		System.out.println(descriptionCreated);
		
		if (proposalNumber.equals(proposalNumberCreated)) {
			System.out.println("Proposal number should be created successfully");
		} else {
			System.out.println("Proposal number not created");

		}	
		
		// close the Window		
		driver.close();
				

	}

}
