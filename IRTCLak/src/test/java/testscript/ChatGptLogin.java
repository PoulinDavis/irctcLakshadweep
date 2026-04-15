package testscript;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ChatGptLogin extends Base {
	@Test
	
	public void login() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

	
	WebElement mobileOrEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobileOrEmail']")));
	mobileOrEmail.sendKeys("8301822892");


	WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

	password.sendKeys("Nora@123");
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement loginBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='LOGIN']")));
	//WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
	loginBtn.click();
	
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//Searching
		WebElement from= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From']/following::button[1]")));
		from.click();
		List<WebElement> optionsfrom = wait.until(
			    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']") ));

			// Select Kochi
			for (WebElement opt : optionsfrom) {
			    if (opt.getText().trim().equalsIgnoreCase("Chetlat")) //Kochi
			    {
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
				    if (opt.getText().trim().equalsIgnoreCase("Kiltan"))//Kalpeni
				    {
				        opt.click();
				        break;
				    }
				}
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				 WebElement datelabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));
				datelabel.click();
				WebElement date23=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--023 react-datepicker__day--highlighted']")));
				date23.click();
				//WebElement date29=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--029']")));
				//date29.click();
				WebElement search=driver.findElement(By.xpath("//button[@type='submit']"));
				search.click();
				//WebElement secondClass=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='SECOND CLASS']")));
				//secondClass.click();
				//WebElement poulin=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("By.xpath(\"//*[text()='Poulin']/preceding::button[1]\")")));
				//poulin.click();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				WebElement bunk=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='BUNK']")));
				bunk.click();
				//POULIN
				String namePoulin="Poulin";
				
				//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + namePoulin + "')]")));
				   
			    // Scroll into view
			    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			    Thread.sleep(200);
			    WebElement checkboxPoulin = wait.until(
				        ExpectedConditions.presenceOfElementLocated(
				            By.xpath("//*[contains(.,'" + namePoulin + "')]/ancestor::div//button[@role='checkbox']")
				        )
				    );
			    Thread.sleep(200);

			    js.executeScript("arguments[0].click();", checkboxPoulin);
				System.out.println("clicked poulin");
			    //DEEPAK
				String nameDeepak="Deepak";
				
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameDeepak + "')]")));
				   //Thread.sleep();
			    // Scroll into view
			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element2);
			    Thread.sleep(200);
			    WebElement checkboxDeepak = wait.until(
				        ExpectedConditions.presenceOfElementLocated(
				            By.xpath("//*[text()='Deepak']/preceding::button[1]")
				        )
				    );
			    Thread.sleep(200);

			    js.executeScript("arguments[0].click();", checkboxDeepak);
			    
			    //NORA
				
				String nameNora="Nora";
				
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameNora + "')]")));
				   
			    // Scroll into view
			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element1);
			    Thread.sleep(200);
			    WebElement checkboxNora = wait.until(
				        ExpectedConditions.presenceOfElementLocated(
				            By.xpath("//*[text()='Nora']/preceding::button[1]")
				        )
				    );
			    Thread.sleep(200);

			    js.executeScript("arguments[0].click();", checkboxNora);
			    
			    WebElement proceedbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='PROCEED']")));
				proceedbutton.click();
				
				//SEAT SELECTON
				
				js.executeScript("window.scrollBy(0,100)","");
				
				Actions act = new Actions(driver);
				//JavascriptExecutor js = (JavascriptExecutor) driver;

				String[] passengers = {"Deepak", "Poulin", "Nora"};
				int passengerIndex = 0;

				// 🔥 Wait for seat SVG once
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[name()='svg']")));

				// 🔥 Retry loop (handles slow site at booking time)
				//for (int retry = 0; retry < 5; retry++) {

				    List<WebElement> seats = driver.findElements(By.xpath(
				        "//*[name()='g' and .//*[name()='rect' and @fill='#fff']]"
				    ));
				   // js.executeScript("arguments[0].scrollIntoView({block:'center'});", seats);
				    if (seats.size() == 0) {
				        System.out.println("⏳ No seats yet... retrying");
				        //continue;
				    }

				    System.out.println("🔥 Seats found: " + seats.size());

				    for (WebElement seat : seats) {

				        if (passengerIndex >= passengers.length) break;
				        String passengerName = passengers[passengerIndex];


				        try {
				            // ✅ Scroll only this seat (NO random scroll)
				            //js.executeScript("arguments[0].scrollIntoView({block:'center'});", seat);

				            // ✅ Click seat (fast + fallback)
				            try {
				                act.moveToElement(seat).click().perform();
				            } catch (Exception e) {
				                js.executeScript("arguments[0].click();", seat);
				            }

				           // String passengerName = passengers[passengerIndex];
				            System.out.println("✅ Seat selected for " + passengerName);

				            // ✅ Correct checkbox (STRICT RELATION)
				            WebElement checkbox = driver.findElement(By.xpath(
				                "//span[text()='" + passengerName + "']/preceding-sibling::input[@type='checkbox']"
				            ));

				            if (!checkbox.isSelected()) {
				                js.executeScript("arguments[0].click();", checkbox);
				                Thread.sleep(200);
				                System.out.println("👤 Assigned: " + passengerName);
				                passengerIndex=passengerIndex+1;
				           }

				            //passengerIndex++;

				        } catch (Exception e) {
				            // skip fast
				        }
				    }

				    if (passengerIndex >= passengers.length) {
				        System.out.println("🎉 All seats selected");
				       // break;
				    }
				//}
			/*Actions act = new Actions(driver);

				// Passenger list
				String[] passengers = {"Poulin", "Deepak", "Nora"};
				int passengerIndex = 0;
WebElement scrollDiv=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='max-h-[450px] pt-7 overflow-auto']")));
((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollTop +=700;", scrollDiv);
				for (int i = 100; i <= 200; i++) {

				    if (passengerIndex >= passengers.length) {
				        break;
				    }

				    String seatNumber = String.valueOf(i);

				    try {
				        // 🔥 Filter only AVAILABLE seats using fill='#fff'
				        WebElement seat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				            "//*[name()='g' and .//*[name()='rect' and @fill='#fff'] " +
				            "and .//*[name()='tspan' and starts-with(normalize-space(text()), '" + seatNumber + "')]]"
				        )));

				        // Scroll only when needed
				     // small scroll first
				       // ((JavascriptExecutor) driver).executeScript(
				           // "arguments[0].scrollTop += 500;", scrollDiv);

				        // then precise
				        ((JavascriptExecutor) driver)
				            .executeScript("arguments[0].scrollIntoView({block:'center'});", seat);

				        // Click seat
				        act.moveToElement(seat).click().perform();

				        String passengerName = passengers[passengerIndex];
				        System.out.println("✅ Seat selected: " + seatNumber + " for " + passengerName);
				        
				        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				            "//span[text()='" + passengerName + "']/preceding::input[@type='checkbox']"
				        )));
				       // ((JavascriptExecutor) driver)
				         // .executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

				        if (!checkbox.isSelected()) {
				            checkbox.click();
				            System.out.println("👤 Assigned: " + passengerName);
				        }

				        passengerIndex++;
				       
                           
				    } catch (Exception e) {
				        // Seat not available or not present → skip instantly
				    }
				}*/
				
										//confirm seat
										
										
										//WebElement confirmSeatButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm Seats']")));
										//confirmSeatButton.click();
								
//SEAT SELECTION poulin
				
				/********WebElement seat7u = driver.findElement(By.xpath(
						"//*[name()='g'][.//*[name()='tspan' and text()='7   U']]"
						));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat7u);
						Thread.sleep(500);
						//js.executeScript("arguments[0].click();", seat7u);
						Actions act=new Actions(driver);
						act.moveToElement(seat7u).click().perform();
						System.out.println("locator");
						WebElement poulinseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Poulin']/preceding-sibling::input[@type='checkbox']")));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", poulinseat);
						Thread.sleep(800);
						poulinseat.click();
						
						
						//SEAT SELECTION Deepak
						
						WebElement seat267u = driver.findElement(By.xpath(
								"//*[name()='g'][.//*[name()='tspan' and text()='267   U']]"
								));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat267u);
								Thread.sleep(500);
								//js.executeScript("arguments[0].click();", seat7u);
								Actions act1=new Actions(driver);
								act1.moveToElement(seat267u).click().perform();
								System.out.println("locator");
								WebElement deepakseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Deepak']/preceding-sibling::input[@type='checkbox']")));
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deepakseat);
								Thread.sleep(800);
								deepakseat.click();
								
								
								//SEAT SELECTION Nora
								
								WebElement seat240l = driver.findElement(By.xpath(
										"//*[name()='g'][.//*[name()='tspan' and text()='240   L']]"
										));
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seat240l);
										Thread.sleep(500);
										//js.executeScript("arguments[0].click();", seat7u);
										Actions act2=new Actions(driver);
										act2.moveToElement(seat240l).click().perform();
										System.out.println("locator");
										WebElement noraseat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Nora']/preceding-sibling::input[@type='checkbox']")));
										((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", noraseat);
										Thread.sleep(800);
										noraseat.click();*********/
						

		
		
}

}
