package com.datalicious.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.datalicious.selenium.beans.CheckDetails;
import com.datalicious.selenium.util.CSVOperations;

public class SearchInGoogle {
	private static WebDriver driver;
	private CheckDetails checkDetails;

	void searchInGoogle() {
		driver = new FirefoxDriver();
		driver.get("www.google.co.in");
		driver.findElement(By.id("lst-ib")).sendKeys("Dataliticious");
		driver.findElement(By.id("_fZl")).click();
		driver.findElement(By.linkText("//a[contains(.,'Datalicious')]"))
				.click();
	}

	void clickDataAnalytics() {
		driver.findElement(By.xpath("//a[contains(.,'Research + Resources')]"))
				.click();
	}

	void checkDTAndDp() {
		driver.findElement(By.xpath("//a[contains(.,'Research + Resources')]"))
				.click();
	}

	void clickOptimaHub() {
		driver.findElement(By.xpath("//a[contains(.,'OptimaHub ')]")).click();
		checkDetails.setParamName("dt");
		checkDetails.setResult("PASS");
		CSVOperations.writeTestResults(checkDetails);
	}

	public static void main(String args[]) {
		SearchInGoogle s = new SearchInGoogle();
		s.searchInGoogle();
		s.clickDataAnalytics();
		s.clickOptimaHub();
		s.checkDTAndDp();
	}
}
