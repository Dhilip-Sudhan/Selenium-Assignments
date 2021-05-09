package AssignmentWeek4Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign2Nykaa {

	public static void main(String[] args) throws InterruptedException {
		//Pseudo Code
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		//1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		
		//2) Mouseover on Brands and Mouseover on Popular
		Actions builder = new Actions(driver);
		Action brands = builder.moveToElement(driver.findElementByXPath("//a[text()='brands']")).build();
		brands.perform();
		
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		Actions action = new Actions(driver);
		builder.moveToElement(popular).build().perform();
		
		//3) Click L'Oreal Paris\
		driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']")).click();
		Thread.sleep(2000);
		
		//Window Handles
		Set<String> allWindowsHandles =driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(allWindowsHandles);
		driver.switchTo().window(listOfWindows.get(1));
				
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("The title of the page is : " + title);
		} else {
			System.out.println("The title of the page is : " + title);
		}
				
		//5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='3']/following-sibling::div[1]")).click();
		
		//6) Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Personal Care']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Bath & Shower']")).click();
		Thread.sleep(2000);
		String filterval = driver.findElement(By.xpath("//label[@for='chk_Shampoo_undefined']")).getText();
		filterval = filterval.replaceAll("\\D", "");
		int filtervalcount = Integer.parseInt(filterval);
		System.out.println("Filtered no of items :" +filtervalcount);
		driver.findElement(By.xpath("//span[text()='Shampoo']//following::div[1]")).click();
		
		//7) check whether the Filter is applied with Shampoo
		String textShampoo = driver.findElement(By.xpath("//ul[@class='pull-left applied-filter-lists']//li")).getText();
		//System.out.println(text);
		if(textShampoo.contains("Shampoo")) {
			System.out.println("Filtered with Shampoo");
		}
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@id='listingContainer']/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[10]/div[1]/a[1]/div[1]/div[2]/div[1]/h2[1]/span[1]")).click();
		
		//9) GO to the new window and select size as 175ml
		allWindowsHandles =driver.getWindowHandles();
		listOfWindows = new ArrayList<String>(allWindowsHandles);
		driver.switchTo().window(listOfWindows.get(2));
		
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		Thread.sleep(1000);
		
		//10) Print the MRP of the product
		String mrpprice = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();
		mrpprice = mrpprice.replaceAll("\\D", "");
		int mrppricecount = Integer.parseInt(mrpprice);
		System.out.println("MRP of the selected product is :" +mrppricecount);
		
		//11) Click on ADD to BAG
		driver.findElement(By.xpath("//div[text()='DELIVERY OPTIONS']//preceding::button[1]")).click();
		Thread.sleep(2000);
	
		
		//12) Go to Shopping Bag
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		Thread.sleep(2000);
		
		//13) Print the Grand Total amount
		String grandtotamt = driver.findElement(By.xpath("(//div[text()='Grand Total:']//following::div[1])[1]")).getText();
		grandtotamt = grandtotamt.replaceAll("\\D", "");
		int grandtotamtcnt = Integer.parseInt(grandtotamt);
		System.out.println("the Grand Total is:" +grandtotamtcnt);
		
		//14) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		Thread.sleep(2000);
		
		//15) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		Thread.sleep(2000);
		
		//16) Check if this grand total is the same in step 13
		String grandtotamt1 = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		grandtotamt1 = grandtotamt1.replaceAll("\\D", "");
		int grandtotamtcnt1 = Integer.parseInt(grandtotamt1);
		System.out.println("the Grand Total in Final Screen is:" +grandtotamtcnt1);
		
		if(grandtotamtcnt==grandtotamtcnt1) {
			System.out.println("Price amount is same");
		}
		
		//17) Close all windows
		driver.quit();

	}

}
