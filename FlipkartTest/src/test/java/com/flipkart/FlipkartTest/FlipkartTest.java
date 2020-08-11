package com.flipkart.FlipkartTest;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartTest {

	public String baseUrl = "https://www.flipkart.com/";
	String driverPath = "C:\\Jars\\84\\chromedriver.exe";
	public WebDriver driver;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test
	public void FlipkartTask_01() throws Throwable {
		Thread.sleep(200);

		driver.findElement(By.xpath("//div[@class='_3Njdz7']/button")).click();
		driver.findElement(By.xpath("//input[@class='LM6RPg']")).sendKeys("phone");
		driver.findElement(By.xpath("//button[@class='vh79eN']")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		JavascriptExecutor jav = (JavascriptExecutor) driver;
		jav.executeScript("window.scrollTo(0,1500)");
		driver.findElement(By.xpath("//div[text()='Budget']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']/../div[2][text()='Rs. 5000 and below']")).click();
		driver.findElement(
				By.xpath("//div[text()='Nokia 105 SS']/../../../../a/div/div[2]/div/div/div[text()='₹1,249']")).click();

		Thread.sleep(2000);

		int count = 2;
		Set<String> s1 = driver.getWindowHandles();
		for (String Str : s1) {
			driver.switchTo().window(Str);
			count++;
			if (count == 2) {
				break;
			}

		}
		Actions action = new Actions(driver);
		WebElement link = driver
				.findElement(By.xpath("//form[@class='EJrIpC']/../../div/form/input[@class='_3X4tVa']"));
		action.click(link).build().perform();
		link.sendKeys("560037");
		link.sendKeys(Keys.ENTER);

		WebElement link2 = driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']"));
		action.click(link2).build().perform();

		String exp = "₹1,249";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String act = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='_10vVqD']/../../div[@class='hJYgKM _2UO4l-']/span[text()=' ₹1,249']"))).getText();
		System.out.println("Price of Phone is " + act);
		Assert.assertTrue(act.contains(exp));

	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}

}
