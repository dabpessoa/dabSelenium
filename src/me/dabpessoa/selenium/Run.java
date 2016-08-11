package me.dabpessoa.selenium;

import java.util.List;

import org.openqa.selenium.WebElement;

import me.dabpessoa.util.RegexUtils;

public class Run {

	public static void main(String[] args) {
//		SeleniumManager manager = new SeleniumManager("http://www.imdb.com", Selenium.createProxy("proxy", 8080));
		SeleniumManager manager = new SeleniumManager("http://www.imdb.com");
		manager.addURLPath("find");
		manager.addURLParam("ref_", "nv_sr_fn");
		manager.addURLParam("s", "tt");
		manager.addURLParam("q", "À Procura de Dory");
		
		manager.init();
		
		List<WebElement> movieResultsLinkElement = manager.findElementsBySelector("#main div.findSection > table > tbody > .findResult > td.result_text > a");
		int size = movieResultsLinkElement.size();
		int count = 0;
		while (count < size) {
			WebElement movieResultLink = manager.findElementsBySelector("#main div.findSection > table > tbody > .findResult > td.result_text > a").get(count++);
			
			manager.waitForVisibility(movieResultLink, 10);
			
			manager.click(movieResultLink);
			
			WebElement movieTitleElement = manager.findFirstElementBySelector("#title-overview-widget > div.vital > div.title_block > div > div.titleBar > div.title_wrapper > h1");
			
			// Movie Title
			String movieTitleText = movieTitleElement.getText();
			movieTitleText = RegexUtils.replaceMatches("(\\([0-9]*\\)$)", movieTitleText, "");
			if (movieTitleText != null) movieTitleText = movieTitleText.trim();
			
			// Ano de Lançamento
			String releaseYearString = RegexUtils.findFirstMatche("(\\([0-9]*\\)$)", movieTitleElement.getText());
			Integer releaseYear = null;
			if (releaseYearString != null && releaseYearString.trim().length() == 6) {
				releaseYearString = releaseYearString.trim();
				releaseYear = Integer.parseInt(releaseYearString.substring(1,5));
			}
			
			// Imprimir
			System.out.println(movieTitleText+" => "+releaseYear);
			
			manager.navigateBack();
		}
		
		manager.quit();
	}
	
}
