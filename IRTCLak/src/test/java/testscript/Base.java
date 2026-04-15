package testscript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	public WebDriver driver;
	@BeforeMethod
    public void browserInitialisation()
    {
	    driver=new ChromeDriver();
		driver.get("https://lakshadweep.irctc.co.in/login");
		
		driver.manage().window().maximize();
		//driver.get("https://www.amazon.in/");
   }
	//@AfterMethod
	public void browserQuitClose() throws InterruptedException
	
	{
	//driver.close();
		Thread.sleep(3000);
	driver.quit();
     }

	

}
