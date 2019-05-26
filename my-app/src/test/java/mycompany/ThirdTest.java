package mycompany;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ThirdTest {
	WebDriver driver;
	ArrayList<String> data;

	@BeforeSuite
	public void initiate() throws IOException {
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = new ChromeDriver();
		Datadriven d = new Datadriven();
		data = d.getdata("Test3");
	}

	@Test(priority = 0)
	public void Login() throws InterruptedException, IOException {
		
		driver.get("https://www.gmail.com/");
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(data.get(1));
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(data.get(2));
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();

	}

	@Test(priority = 1)
	public void Composeemail() throws InterruptedException, IOException {
		Datadriven d = new Datadriven();
		ArrayList<String> data = d.getdata("Test2");
		// WebDriverWait wait = new WebDriverWait(driver, 40);
		// WebElement element =
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='nM']/div/div/div)[1]")));
		// element.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//div[@class='nM']/div/div/div)[1]")).click();
		driver.findElement(By.xpath("//*[@role=\"combobox\"]")).sendKeys(data.get(3));
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
