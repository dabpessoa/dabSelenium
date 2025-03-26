package me.dabpessoa.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import me.dabpessoa.util.RegexUtils;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Run {

	public static void main(String[] args) {

//		SeleniumManager manager = new SeleniumManager("http://www.imdb.com", Selenium.createProxy("proxy", 8080, "diego.pessoa", ""));
		SeleniumManager manager = new SeleniumManager("https://www.imdb.com");
		manager.addURLPath("find/");
		manager.addURLParam("ref_", "nv_sr_fn");
		manager.addURLParam("s", "tt");
		manager.addURLParam("q", "À Procura de Dory");
		
		manager.doGet();

		String movieListSelector = "main ul.ipc-metadata-list > li.ipc-metadata-list-summary-item";
		String movieInfoLinkSelector = "a.ipc-metadata-list-summary-item__t";
		String movieTitleSelector = "h1 > span.hero__primary-text";
		String movieReleaseYearSelector = "section.ipc-page-section ul > li.ipc-inline-list__item > a[href*='releaseinfo']";

		List<WebElement> movieResults = manager.findElementsBySelector(movieListSelector);
        int elementsSize = movieResults.size();
		int count = 0;

			while (count < elementsSize) {
				WebElement movieResult = movieResults.get(count++);
				WebElement movieInfoLink = manager.findFirstElementBySelector(movieResult, movieInfoLinkSelector);

				manager.click(movieInfoLink, 10);

				// Movie Title
				WebElement movieTitleElement = manager.findFirstElementBySelector(movieTitleSelector);
				String movieTitleText = movieTitleElement.getText();
				if (movieTitleText != null) {
					movieTitleText = movieTitleText.trim();
				}

				// Ano de Lançamento
				WebElement movieReleaseYearElement = manager.findFirstElementBySelector(movieReleaseYearSelector);
				String movieReleaseYearText = movieReleaseYearElement.getText();
				if (movieReleaseYearText != null) {
					movieReleaseYearText = movieReleaseYearText.trim();
				}

				// Imprimir
				System.out.println(movieTitleText+" => "+movieReleaseYearText);

				manager.navigateBack();
				// É necessário puxar a lista de filmes novamente para evitar erro de "Stale Element", pois houve navegação de página.
				movieResults = manager.findElementsBySelector(movieListSelector);
			}
		
		manager.quit();
	}
	
}
