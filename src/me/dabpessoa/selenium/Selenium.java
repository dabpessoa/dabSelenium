package me.dabpessoa.selenium;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Selenium {

	public static final String URL_PARAM_SIGNAL = "?";
	public static final String URL_PARAM_SEPARATOR = ";";
	public static final String URL_PATH_SEPARATOR = "/";
	
	private String baseURL;
	private String url; // baseURL + params
	private WebDriver driver;
	private Map<String, String> paramsMap;
	
	public Selenium() {
		this(null, null);
	}
	
	public Selenium(String webPath) {
		this(webPath, null);
	}
	
	public Selenium(ProxyCredentials proxyCredentials) {
		this(null, proxyCredentials);
	}
	
	public Selenium(String baseURL, ProxyCredentials proxyCredentials) {
		paramsMap = new HashMap<String, String>();
		
		if (baseURL != null) {
			setBaseURL(baseURL);
		}
		
		if (proxyCredentials != null) {
			DesiredCapabilities cap = DesiredCapabilities.htmlUnitWithJs();
			cap.setCapability(CapabilityType.PROXY, createSeleniumProxy(proxyCredentials));

			driver = new HtmlUnitDriver(cap);
		} else {
			driver = new HtmlUnitDriver();
		}
	}
	
	public void addParam(String paramName, String paramValue) {
		/*
		 * Pensar em uma forma de tratar par�metros repetidos.
		 */
		if (paramName != null && paramValue != null) {
			paramsMap.put(paramName, paramValue);
			String param = paramName+"="+paramValue;
			if (!containsURLParamSignal()) {
				setUrl(getUrl()+URL_PARAM_SIGNAL+param);
			} else {
				setUrl(getUrl()+"&"+param);
			}
		}
	}
	
	public boolean containsURLParamSignal() {
		return getUrl().indexOf(URL_PARAM_SIGNAL) != -1;
	}
	
	public void addPath(String path) {
		if (getUrl() == null) {
			setUrl(path);
			return;
		}
		
		String paramsString = null;
		if (getUrl().indexOf(URL_PARAM_SIGNAL) != -1) {
			paramsString = getUrl().substring(getUrl().indexOf(URL_PARAM_SIGNAL));
			setUrl(getUrl().substring(0, getUrl().indexOf(URL_PARAM_SIGNAL)));
		}
		
		setUrl(getUrl() + URL_PATH_SEPARATOR + path);
		if (paramsString != null) {
			setUrl(getUrl() + paramsString);
		}
	}
	
	public static ProxyCredentials createProxy(String host, int port) {
		return new ProxyCredentials(host, port);
	}
	
	public static ProxyCredentials createProxy(String host, int port, String user, String password) {
		return new ProxyCredentials(host, port, user, password);
	}
	
	private Proxy createSeleniumProxy(ProxyCredentials proxyCredentials) {
		if (proxyCredentials == null) {
			return null;
		}
		
		String PROXY = proxyCredentials.getHost()+":"+proxyCredentials.getPort();

		Proxy proxy = new Proxy();
		proxy.setHttpProxy(PROXY)
		     .setFtpProxy(PROXY)
		     .setSslProxy(PROXY);
//		     .setSocksProxy(PROXY)
//		     .setSocksUsername(proxyCredentials.getUser())
//		     .setSocksPassword(proxyCredentials.getPassword());
		
		return proxy;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
		setUrl(baseURL);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Map<String, String> getParamsMap() {
		return paramsMap;
	}
	
}
