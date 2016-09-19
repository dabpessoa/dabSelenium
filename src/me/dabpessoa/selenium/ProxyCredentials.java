package me.dabpessoa.selenium;

public class ProxyCredentials {

	private String host;
	private Integer port;
	private String user;
	private String password;
	
	public ProxyCredentials(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	
	public ProxyCredentials(String host, Integer port, String user, String password) {
		this(host, port);
		this.user = user;
		this.password = password;
	}
	
	public boolean empty() {
		return (host == null || host.equalsIgnoreCase("")) && (port == null); 
	}
	
	public boolean notEmpty() {
		return !empty();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}