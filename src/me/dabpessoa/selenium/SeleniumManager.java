package me.dabpessoa.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	
	public static void main(String[] args) {
		
		SeleniumManager manager = new SeleniumManager("www.google.com", Selenium.createProxy("proxy", 8080));
		manager.init();
		
		// código aqui...
		
		manager.quit();
		
	}
	
	public List<WebElement> findElementsBySelector(String selector) {
		return selenium.getDriver().findElements(By.cssSelector(selector));
	}
	
	public WebElement findFirstElementBySelector(String selector) {
		return selenium.getDriver().findElement(By.cssSelector(selector));
	}
	
	public List<WebElement> findElementsByXPath(String xPath) {
		return selenium.getDriver().findElements(By.xpath(xPath));
	}
	
	public WebElement findFirstElementByXPath(String xPath) {
		return selenium.getDriver().findElement(By.xpath(xPath));
	}
	
	public void init() {
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
	
}
