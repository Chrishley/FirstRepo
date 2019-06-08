package mycompany;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mycompany.app.Datadriven;

public class FirstTest {
	WebDriver driver;
	ArrayList<String> data;

	@BeforeSuite
	public void initiate() throws IOException {
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = new ChromeDriver();
		Datadriven d = new Datadriven();
		data = d.getdata("Test1");
	}

	@Test(priority = 0)
	public void Login() throws InterruptedException, IOException {

		driver.get("https://www.gmail.com/");
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(data.get(1));
		WebDriverWait wait = new WebDriverWait (driver,3);
		WebElement next = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]"));
		next.click();
		//driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div[2]")).click();
		/*WebElement element = driver.findElement(By.xpath("(//div[@id='identifierNext']/div)[2]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();*/
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(data.get(2));
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]")).click();

	}

	@Test(priority = 1)
	public void Composeemail() throws InterruptedException, IOException {

		// WebDriverWait wait = new WebDriverWait(driver, 40);
		// WebElement element =
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='nM']/div/div/div)[1]")));
		// element.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//div[@class='nM']/div/div/div)[1]")).click();
		driver.findElement(By.xpath("//textarea[contains(@role,'combobox')]")).sendKeys(data.get(3));
		driver.findElement(By.name("subjectbox")).sendKeys("Test email by Selenium");
		driver.findElement(By.xpath("//*[@ g_editable=\"true\"]")).sendKeys(data.get(4));
		driver.findElement(By.xpath("//tr[@class='btC']/td/div/div/div[contains(@aria-label,'Send ‪(⌘Enter)‬')]"))
				.click();
		Thread.sleep(3000);
	}

	@AfterSuite
	public void end() {
		 driver.quit();

	}

}
