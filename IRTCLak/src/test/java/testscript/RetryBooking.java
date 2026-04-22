package testscript;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RetryBooking extends Base {
@Test
	
	public void login() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

	
	WebElement mobileOrEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobileOrEmail']")));
	mobileOrEmail.sendKeys("8301822892");


	WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

	password.sendKeys("Nora@123");
	WebElement loginBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='LOGIN']")));
	loginBtn.click();
	
	//SEARCHING
	WebElement from= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From']/following::button[1]")));
	from.click();
	List<WebElement> optionsfrom = wait.until(
		ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']") ));

				// Select Kochi
				for (WebElement opt : optionsfrom) {
				    if (opt.getText().trim().equalsIgnoreCase("Kavaratti")) ////Kochi,Kavaratti,Beypore,Mangalore,Androth,Kalpeni,Agatti,Kalpeni,Bangaram,Minicoy,Amini,Kadmat,Kiltan,Chetlat,Bitra
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
					    if (opt.getText().trim().equalsIgnoreCase("Kochi"))//Kochi,Kavaratti,Beypore,Mangalore,Androth,Kalpeni,Agatti,Kalpeni,Bangaram,Minicoy,Amini,Kadmat,Kiltan,Chetlat,Bitra
					    {
					        opt.click();
					        break;
					    }
					}
					
					 WebElement datelabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));
					 datelabel.click();
					 WebElement date=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--018 react-datepicker__day--weekend']")));
					 date.click();
					 
					 
					 LocalTime targetTime = LocalTime.of(10,00);

					// ⏳ Wait EXACTLY till time
					 while (LocalTime.now().isBefore(targetTime)) {
					     // idle
					}
					 WebElement search=driver.findElement(By.xpath("//button[@type='submit']"));
					 search.click();

					WebElement bunk=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='BUNK']")));
					 bunk.click();
					 //POULIN
					 String namePoulin="Poulin";
					 //Thread.sleep(200);
					    WebElement checkboxPoulin = wait.until(
						        ExpectedConditions.presenceOfElementLocated(
						            By.xpath("//*[contains(.,'" + namePoulin + "')]/ancestor::div//button[@role='checkbox']")
						        )
						    );
					   // Thread.sleep(200);

					    js.executeScript("arguments[0].click();", checkboxPoulin);
						//System.out.println("clicked poulin");
					    //DEEPAK
						String nameDeepak="Deepak";
						
						WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameDeepak + "')]")));
						 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element2);
						   // Thread.sleep(200);
						    WebElement checkboxDeepak = wait.until(
							        ExpectedConditions.presenceOfElementLocated(
							            By.xpath("//*[text()='Deepak']/preceding::button[1]")
							        )
							    );
						    //Thread.sleep(100);

						    js.executeScript("arguments[0].click();", checkboxDeepak);
						    
						    //NORA
							
							String nameNora="Nora";
							
							WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameNora + "')]")));
							   
						    // Scroll into view
						    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element1);
						    Thread.sleep(100);
						    WebElement checkboxNora = wait.until(
							        ExpectedConditions.presenceOfElementLocated(
							            By.xpath("//*[text()='Nora']/preceding::button[1]")
							        )
							    );
						    //Thread.sleep(100);
						    js.executeScript("arguments[0].click();", checkboxNora);
						    WebElement proceedbutton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='PROCEED']")));
							proceedbutton.click();
//******************************************************************************************************/
							int maxRetries = 5;
							int attempt = 0;
							boolean bookingSuccess = false;

							
							    
							
							//SEAT SELECTON
							
							//js.executeScript("window.scrollBy(0,100)","");
							
							Actions act = new Actions(driver);
							//JavascriptExecutor js = (JavascriptExecutor) driver;

							String[] passengers = {"Poulin", "Deepak", "Nora"};
							int passengerIndex = 0;
							int count=0;
							int requiredSeats=3;

							// 🔥 Wait for seat SVG once
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[name()='svg']")));

							// 🔥 Retry loop (handles slow site at booking time)
							//for (int retry = 0; retry < 5; retry++) {
							 
							   // js.executeScript("arguments[0].scrollIntoView({block:'center'});", seats);
							   
							    
							    while (attempt < maxRetries && !bookingSuccess) {

								    try {
								        System.out.println("Attempt: " + (attempt + 1));
								
								        Set<String> triedSeats= new HashSet<>();

									    List<WebElement> seats = driver.findElements(By.xpath(
									        "//*[name()='g' and .//*[name()='rect' and @fill='#fff']]"
									    ));
									    if (seats.size() == 0) {
									        System.out.println("⏳ No seats yet... retrying");
									        //continue;
									    }

									    System.out.println("🔥 Seats found: " + seats.size());
							    for (WebElement seat : seats) {

							        if (passengerIndex >= passengers.length) break;
							        String passengerName = passengers[passengerIndex];
							        String seatText=seat.getText().trim();
							        if(triedSeats.contains(seatText)) {
							        	continue;
							        }

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
							            System.out.println("✅ Seat selected for " + passengerName+seatText);
							            
							           

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
							            triedSeats.add(seatText);
							            count++;
							            if(count==requiredSeats) {
							            	break;
							            
							        }
							        

							            //passengerIndex++;

							        } catch (Exception e) {
							            // skip fast
							        }
							    

							    if (passengerIndex >= passengers.length) {
							        System.out.println("🎉 All seats selected");
							       // break;
							    }seats.remove(seat);

							    }

						        if(count<requiredSeats) {
						        	System.out.println("Not Enough seats available");
						        }
							    //js.executeScript("window.scrollBy(0,300)","");
						    
						  //CONFIRM SEAT
							WebElement confirmSeatButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm Seats']")));
							confirmSeatButton.click();
							
							// Step 4: Check if Proceed button appears
					        boolean isProceedVisible = wait.until(driver -> {
					            List<WebElement> proceedBtn = driver.findElements(By.xpath("//button[text()='Proceed'and @type='submit']"));
					            return proceedBtn.size() > 0;});

					        if (isProceedVisible) {
					            System.out.println("✅ Seat booked successfully");
					            bookingSuccess = true;
					            break;
					        }
							    
							    }catch (Exception e) {
					        System.out.println("❌ Seat lost, retrying...");
					        driver.navigate().refresh();
					    }

					    // Step 5: Refresh or reset before retry
					    

					    // Optional wait before retry
					    //Thread.sleep(200);

					    attempt++;
					}

					if (!bookingSuccess) {
					    System.out.println("❌ Failed after max attempts");
					}
}
				

}
