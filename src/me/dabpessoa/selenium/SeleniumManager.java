package me.dabpessoa.selenium;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumManager {

	private Selenium selenium;
	private ProxyCredentials proxyCredentials;
	
	public SeleniumManager(String URLPath) {
		this(URLPath, null);
	}
	
	public SeleniumManager(ProxyCredentials proxyCredentials) {
		this(null, proxyCredentials);
	}
	
	public SeleniumManager(String URLPath, ProxyCredentials proxyCredentials) {
		selenium = new Selenium(URLPath, proxyCredentials);
		
	}
	
	public String getPageSource() {
		return selenium.getDriver().getPageSource();
	}
	
	public void findAndClickBySelector(String selector) {
		click(findFirstElementBySelector(selector));
	}
	
	public void findAndClickBySelector(String selector, int waitTime) {
		click(findFirstElementBySelector(selector, waitTime));
	}
	
	public List<WebElement> findElementsBySelector(String selector) {
		return selenium.getDriver().findElements(By.cssSelector(selector));
	}

	public List<WebElement> findElementsBySelector(WebElement webElement, String selector) {
		if (selector == null) {
			return Collections.emptyList();
		}

		if (webElement == null) {
			return findElementsBySelector(selector);
		}

		return webElement.findElements(By.cssSelector(selector));
	}
	
	public List<WebElement> findElementsBySelector(String selector, int waitTime) {
		return SeleniumUtils.waitAndSearchElements(selenium.getDriver(), waitTime, selector);
	}
	
	public WebElement findFirstElementBySelector(String selector) {
		return selenium.getDriver().findElement(By.cssSelector(selector));
	}

	public WebElement findFirstElementBySelector(WebElement webElement, String selector) {
		if (selector == null) {
			return null;
		}

		if (webElement == null) {
			return findFirstElementBySelector(selector);
		}

		return webElement.findElement(By.cssSelector(selector));
	}
	
	public WebElement findFirstElementBySelector(String selector, int waitTime) {
		return SeleniumUtils.waitAndSearchFirstElement(selenium.getDriver(), waitTime, selector);
	}
	
	public boolean waitFor(String selector, int waitTime) {
		return SeleniumUtils.waitElementBeRenderedAtPage(selenium.getDriver(), waitTime, selector);
	}
	
	public List<WebElement> findElementsByXPath(String xPath) {
		return selenium.getDriver().findElements(By.xpath(xPath));
	}
	
	public List<WebElement> findElementsByXPath(String xPath, int waitTime) {
		return SeleniumUtils.waitAndSearchElementsByXPath(selenium.getDriver(), waitTime, xPath);
	}
	
	public WebElement findFirstElementByXPath(String xPath) {
		return selenium.getDriver().findElement(By.xpath(xPath));
	}
	
	public WebElement findFirstElementByXPath(String xPath, int waitTime) {
		return SeleniumUtils.waitAndSearchElementByXPath(selenium.getDriver(), waitTime, xPath);
	}
	
	public void click(WebElement element) {
		if (element == null) return;
		element.click();
	}

	public void click(WebElement webElement, Integer waitTimeOut) {
		if (waitTimeOut == null) {
			waitTimeOut = 1;
		}

		waitForVisibility(webElement, waitTimeOut);
		click(webElement);
	}
	
	public void navigateBack() {
		selenium.getDriver().navigate().back();
	}
	
	public void navigateFoward() {
		selenium.getDriver().navigate().forward();
	}
	
	public void refreshPage() {
		selenium.getDriver().navigate().refresh();
	}
	
	public void waitForVisibility(WebElement element, int waitTimeOut) {
		(new WebDriverWait(getSelenium().getDriver(), waitTimeOut)).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForVisibility(String selector, int waitTimeOut) {
		(new WebDriverWait(getSelenium().getDriver(), waitTimeOut)).until(ExpectedConditions.visibilityOf(findFirstElementBySelector(selector)));
	}
	
	public void doGet() {
		selenium.getDriver().get(selenium.getUrl());
	}
	
	public void quit() {
		selenium.getDriver().quit();
	}
	
	public void addURLParam(String paramName, String paramValue) {
		selenium.addParam(paramName, paramValue);
	}
	
	public void addURLPath(String path) {
		selenium.addPath(path);
	}
	
	public void setBaseURL(String baseURL) {
		selenium.setBaseURL(baseURL);
	}
	
	public Selenium getSelenium() {
		return selenium;
	}
	
	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
	}
	
	public ProxyCredentials getProxyCredentials() {
		return proxyCredentials;
	}
	
	public void setProxyCredentials(ProxyCredentials proxyCredentials) {
		this.proxyCredentials = proxyCredentials;
	}
	
	public String getURL() {
		return selenium.getUrl();
	}
	
}
