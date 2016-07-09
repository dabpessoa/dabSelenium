package me.dabpessoa.selenium;

import java.util.List;

import org.openqa.selenium.WebElement;

public class Run {

	public static void main(String[] args) {
//		SeleniumManager manager = new SeleniumManager("www.google.com", Selenium.createProxy("proxy", 8080));
		SeleniumManager manager = new SeleniumManager("http://www.imdb.com");
		manager.addURLPath("find");
		manager.addURLParam("ref_", "nv_sr_fn");
		manager.addURLParam("s", "tt");
		manager.addURLParam("q", "Batman superman");
		
		manager.init();
		
		List<WebElement> movieResultsLinkElement = manager.findElementsBySelector("#main div.findSection > table > tbody > .findResult > td.result_text > a");
		int size = movieResultsLinkElement.size();
		int count = 0;
		while (count < size) {
			WebElement movieResultLink = manager.findElementsBySelector("#main div.findSection > table > tbody > .findResult > td.result_text > a").get(count++);
			
			manager.waitForVisibility(movieResultLink, 10);
			
			manager.click(movieResultLink);
			
			WebElement movieTitleElement = manager.findFirstElementBySelector("#title-overview-widget > div.vital > div.title_block > div > div.titleBar > div.title_wrapper > h1");
			String movieTitleText = movieTitleElement.getText();
			
			System.out.println(movieTitleText);
			
			manager.navigateBack();
		}
		
		manager.quit();
	}
	
}
