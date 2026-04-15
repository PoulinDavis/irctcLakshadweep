package testscript;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginPage extends Base{
	@Test
	public void login() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		//LOGIN
		
		WebElement mobileOrEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobileOrEmail']")));
		mobileOrEmail.sendKeys("8301822892");
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
		password.sendKeys("Nora@123");
		WebElement loginButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='LOGIN']")));
		loginButton.click();
		
		//SEARCHING
		
		WebElement from= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From']/following::button[1]")));
		from.click();
		List<WebElement> optionsfrom = wait.until(
			ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));

					// Select Kavaratti
					for (WebElement opt : optionsfrom) {
					    if (opt.getText().trim().equalsIgnoreCase("Kochi")) {
					        opt.click();
					        break;
					    }
					}
					
		WebElement to=driver.findElement(By.xpath("//label[text()='To']/following::button[1]"));
		to.click();
		List<WebElement> optionsTo = wait.until(
			ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']") ));

						// Select Kochi
						for (WebElement opt : optionsTo) {
						    if (opt.getText().trim().equalsIgnoreCase("Kalpeni")) {
						        opt.click();
						        break;
						    }
						}
						
		 WebElement datelabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));
		 datelabel.click();
		 WebElement date29=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--028']")));
			date29.click();
		 WebElement search=driver.findElement(By.xpath("//button[@type='submit']"));
		 search.click();
		 driver.navigate().refresh();
		 
		 //BUNK SELECTION

		 WebElement bunk=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='BUNK']")));
		 bunk.click();
		 
		//POULIN
		 
		String namePoulin="Poulin";
		//Thread.sleep(500);
	    WebElement checkboxPoulin = wait.until(
		        ExpectedConditions.presenceOfElementLocated(
		            By.xpath("//*[contains(.,'" + namePoulin + "')]/ancestor::div//button[@role='checkbox']")
		        )
		    );
	    //Thread.sleep(200);
	    js.executeScript("arguments[0].click();", checkboxPoulin);
		System.out.println("clicked poulin");
		
	    //DEEPAK
		
		String nameDeepak="Deepak";
		
		WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameDeepak + "')]")));
		  // Thread.sleep(200);
	    // Scroll into view
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element2);
	    //Thread.sleep(200);
	    WebElement checkboxDeepak = wait.until(
		        ExpectedConditions.presenceOfElementLocated(
		            By.xpath("//*[text()='Deepak']/preceding::button[1]")
		        )
		    );
	   // Thread.sleep(500);

	    js.executeScript("arguments[0].click();", checkboxDeepak);
	    
	    //NORA
		
		String nameNora="Nora";
		
		WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameNora + "')]")));
		   
	    // Scroll into view
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element1);
	    //Thread.sleep(500);
	    WebElement checkboxNora = wait.until(
		        ExpectedConditions.presenceOfElementLocated(
		            By.xpath("//*[text()='Nora']/preceding::button[1]")
		        )
		    );
	    //Thread.sleep(500);

	    js.executeScript("arguments[0].click();", checkboxNora);
	    
	    //PROCEED BUTTON
	    
	    WebElement proceedbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='PROCEED']")));
		proceedbutton.click();
		
		
		//SEAT SELECTION POULIN
		
		WebElement seat238L = driver.findElement(By.xpath(
				"//*[name()='g'][.//*[name()='tspan' and text()='152   L']]"
				));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat238L);
				Thread.sleep(500);
				
				Actions act=new Actions(driver);
				act.moveToElement(seat238L).click().perform();
				System.out.println("locator");
				WebElement poulinseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Poulin']/preceding-sibling::input[@type='checkbox']")));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", poulinseat);
				Thread.sleep(800);
				poulinseat.click();
				
				
		//SEAT SELECTION DEEPAK
				
				WebElement seat239U = driver.findElement(By.xpath(
						"//*[name()='g'][.//*[name()='tspan' and text()='151   U']]"
						));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat239U);
						Thread.sleep(500);
						//js.executeScript("arguments[0].click();", seat7u);
						Actions act1=new Actions(driver);
						act1.moveToElement(seat239U).click().perform();
						System.out.println("locator");
						WebElement deepakseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Deepak']/preceding-sibling::input[@type='checkbox']")));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deepakseat);
						Thread.sleep(800);
						deepakseat.click();
						
						
		//SEAT SELECTION NORA
						
						WebElement seat240L = driver.findElement(By.xpath(
								"//*[name()='g'][.//*[name()='tspan' and text()='150   L']]"
								));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat240L);
								Thread.sleep(500);
								//js.executeScript("arguments[0].click();", seat7u);
								Actions act2=new Actions(driver);
								act2.moveToElement(seat240L).click().perform();
								System.out.println("locator");
								WebElement noraseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Nora']/preceding-sibling::input[@type='checkbox']")));
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", noraseat);
								Thread.sleep(800);
								noraseat.click();
			//CONFIRM SEAT
								
								
			WebElement confirmSeatButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm Seats']")));
			confirmSeatButton.click();
			
	}
	
}
