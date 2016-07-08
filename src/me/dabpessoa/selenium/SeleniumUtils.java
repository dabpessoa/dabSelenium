package me.dabpessoa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	public static boolean waitElementBeRenderedAtPage(WebDriver driver, int waitTime, String selector) {
		return (new WebDriverWait(driver, waitTime)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement element = null;
				try {
					element = driver.findElement(By.cssSelector(selector));
				} catch (Exception e) {
					element = null;
				} return element != null;
			}
		});
	}
	
	public static WebElement waitAndSearchElement(WebDriver driver, int waitTime, String selector) {
		try {
			return (new WebDriverWait(driver, waitTime)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return driver.findElement(By.cssSelector(selector));
				}
			});
		} catch (TimeoutException e) {
			return null;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public static WebElement waitAndSearchElementByXPath(WebDriver driver, int waitTime, String xPath) {
		return (new WebDriverWait(driver, waitTime)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return driver.findElement(By.xpath(xPath));
			}
		});
	}
	
	public boolean waitForElementSelector(int timeout, WebDriver driver, String selector) {
		try {
			return (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = null;
					try {
						element = driver.findElement(By.cssSelector(selector));
					} catch (Exception e) {
						element = null;
					} return element != null;
				}
			});
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	public WebElement waitAndGetElementByXPath(int timeout, WebDriver driver, String xPath) {
		try {
			return (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return driver.findElement(By.xpath(xPath));
				}
			});
		} catch (TimeoutException e) {
			return null;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
}
