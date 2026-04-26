package testscript;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TicketBooking extends Base{

@Test
public void chatgptTicketBooking() throws InterruptedException {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

	WebElement mobileOrEmail = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='mobileOrEmail']")));
	mobileOrEmail.sendKeys("8301822892");

	WebElement password = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));

	password.sendKeys("Nora@123");
	WebElement loginBtn = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='LOGIN']")));
	loginBtn.click();

	// SEARCHING
	WebElement from = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From']/following::button[1]")));
	from.click();
	List<WebElement> optionsfrom = wait
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));

	// Select Kochi
	for (WebElement opt : optionsfrom) {
		if (opt.getText().trim().equalsIgnoreCase("Kochi")) //// Kochi,Kavaratti,Beypore,Mangalore,Androth,Kalpeni,Agatti,Kalpeni,Bangaram,Minicoy,Amini,Kadmat,Kiltan,Chetlat,Bitra
		{
			opt.click();
			break;
		}
	}

	WebElement to = driver.findElement(By.xpath("//label[text()='To']/following::button[1]"));
	to.click();
	List<WebElement> optionsTo = wait
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));

	// Select Kochi
	for (WebElement opt : optionsTo) {
		if (opt.getText().trim().equalsIgnoreCase("Minicoy"))// Kochi,Kavaratti,Beypore,Mangalore,Androth,Kalpeni,Agatti,Kalpeni,Bangaram,Minicoy,Amini,Kadmat,Kiltan,Chetlat,Bitra
		{
			opt.click();
			break;
		}
	}

	WebElement datelabel = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));
	datelabel.click();

	List<WebElement> date = driver
			.findElements(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--010 react-datepicker__day--weekend']"));
	// date.click();
	if (date.size() > 0) {
		date.get(0).click();
	} else {
		WebElement nextMonthbutton = driver.findElement(By.xpath("//button[@aria-label='Next Month']"));
		nextMonthbutton.click();
		WebElement datenextmonth = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='react-datepicker__day react-datepicker__day--010 react-datepicker__day--weekend']")));
		datenextmonth.click();
	}

	LocalTime targetTime = LocalTime.of(16, 00);

	// ⏳ Wait EXACTLY till time
	while (LocalTime.now().isBefore(targetTime)) {
		// idle
	}
	WebElement search = driver.findElement(By.xpath("//button[@type='submit']"));
	search.click();

	WebElement bunk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='BUNK']")));
	bunk.click();
	// POULIN
	String namePoulin = "Poulin";
	// Thread.sleep(200);
	WebElement checkboxPoulin = wait.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath("//*[contains(.,'" + namePoulin + "')]/ancestor::div//button[@role='checkbox']")));
	// Thread.sleep(200);

	js.executeScript("arguments[0].click();", checkboxPoulin);
	// System.out.println("clicked poulin");
	// DEEPAK
	String nameDeepak = "Deepak";

	WebElement element2 = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameDeepak + "')]")));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element2);
	// Thread.sleep(200);
	WebElement checkboxDeepak = wait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Deepak']/preceding::button[1]")));
	// Thread.sleep(100);

	js.executeScript("arguments[0].click();", checkboxDeepak);

	// NORA

	String nameNora = "Nora";

	WebElement element1 = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'" + nameNora + "')]")));

	// Scroll into view
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element1);
	Thread.sleep(100);
	WebElement checkboxNora = wait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Nora']/preceding::button[1]")));
	// Thread.sleep(100);
	js.executeScript("arguments[0].click();", checkboxNora);
	WebElement proceedbutton = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='PROCEED']")));
	proceedbutton.click();

	Actions act = new Actions(driver);
	// JavascriptExecutor js = (JavascriptExecutor) driver;

	String[] passengers = { "Poulin", "Deepak", "Nora" };
	int passengerIndex = 0;

	int attempt = 0;
	int maxRetries = 6;
	boolean bookingSuccess = false;
	boolean isProceedVisible = false;
	Set<String> triedSeats = new HashSet<>();

	while (attempt < maxRetries && !bookingSuccess) {

		try {
			System.out.println("🚀 Attempt: " + (attempt + 1));

			// 🔥 Wait until at least 1 seat is available
			wait.until(driver -> {
				List<WebElement> seats = driver
						.findElements(By.xpath("//*[name()='g' and .//*[name()='rect' and @fill='#fff']]"));
				return seats.size() > 0;
			});

			List<WebElement> seats = driver
					.findElements(By.xpath("//*[name()='g' and .//*[name()='rect' and @fill='#fff']]"));

			System.out.println("🔥 Seats found: " + seats.size());

			for (int i = 0; i <= seats.size(); i++) {

				for (int k = 0; k <= seats.size(); k++) {
					// 🔥 ALWAYS re-fetch element (avoid stale)
					if (passengerIndex >= passengers.length)
					{
						System.out.println("passenger index increased");
						break;
					}
					seats = driver.findElements(By.xpath("//*[name()='g' and .//*[name()='rect' and @fill='#fff']]"));
					System.out.println("new available seat "+seats.size());

					if (i >= seats.size())
					{
						System.out.println("i>seatsize");
						break;
					}

					WebElement seat = seats.get(k);

					

					String seatText = seat.getText().trim();
					//int seatNumber = Integer.parseInt(seatText.replaceAll("[^0-9]", ""));

					// filter Range
					//if (seatNumber < 100 || seatNumber > 200) {
						//System.out.println("number not between 100-200");
						//continue;
					//}

					if (triedSeats.contains(seatText))
					{
						System.out.println("tried seat");
						continue;
					}

					String passengerName = passengers[passengerIndex];

					try {
						// 🔽 Scroll seat into view (center)
						js.executeScript("arguments[0].scrollIntoView({block:'center'});", seat);

						// ⚡ Fast click
						try {
							act.moveToElement(seat).click().perform();
						} catch (Exception e) {
							js.executeScript("arguments[0].click();", seat);
						}

						System.out.println("✅ Seat selected: " + seatText);

						// 👤 Assign passenger
						WebElement checkbox = driver.findElement(By.xpath(
								"//span[text()='" + passengerName + "']/preceding-sibling::input[@type='checkbox']"));

						if (!checkbox.isSelected()) {
							js.executeScript("arguments[0].click();", checkbox);
							System.out.println("👤 Assigned: " + passengerName);
							passengerIndex++;
						}

						triedSeats.add(seatText);

					} catch (Exception e) {
						triedSeats.add(seatText); // avoid retry same bad seat
					}
				}
				if (passengerIndex >= passengers.length) {
					System.out.println("🎉 All seats selected");
					
				}

				// ✅ Confirm Seats
				WebElement confirmSeatButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm Seats']")));
				confirmSeatButton.click();
				System.out.println("clicked confirm");

				// ✅ Check Proceed button

				try {

					WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

					isProceedVisible = shortWait.until(
							d -> d.findElements(By.xpath("//button[text()='Proceed' and @type='submit']")).size() > 0);
					System.out.println("proceed button found");
					System.out.println(isProceedVisible);
				} catch (Exception e) {
					isProceedVisible = false;
					passengerIndex=0;
				}

				if (isProceedVisible) {
					WebElement proceedbtn = driver
							.findElement(By.xpath("//button[text()='Proceed' and @type='submit']"));
					proceedbtn.click();
					System.out.println("🎉 BOOKING SUCCESS proceed button clicked");
					bookingSuccess = true;
					break;
				}

			}
			if (isProceedVisible) {
				System.out.println("🎉 BOOKING SUCCESS while break");
				bookingSuccess = true;
				break;
			}

		} catch (Exception e) {
			System.out.println("❌ Seat lost, retrying...");
		}

		// 🔁 Refresh and retfry
		driver.navigate().refresh();

		// 🔥 IMPORTANT: Wait after refresh again
		/*
		 * wait.until(driver -> { List<WebElement> seats = driver.findElements(By.xpath(
		 * "//*[name()='g' and .//*[name()='rect' and @fill='#fff']]" )); return
		 * seats.size() > 0; });
		 */

		attempt++;
		passengerIndex=0;
		

		if (bookingSuccess) {
			System.out.println("SUCCESS");
			break;
		}
	}

	if (!bookingSuccess) {
		System.out.println("❌ Failed after max attempts");
	}
	WebElement proceedbtn = driver.findElement(By.xpath("//button[text()='Proceed' and @type='submit']"));
	proceedbtn.click();

}

}
