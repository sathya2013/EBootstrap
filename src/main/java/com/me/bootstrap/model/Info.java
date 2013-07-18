package com.me.bootstrap.model;

@SuppressWarnings("serial")
public class Info implements java.io.Serializable,Anchor{

	private String title;
	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;
	
	
	@Override
	public String getTitle() {
		
		return title;
	}

	@Override
	public String getUrl() {
		
		return url;
	}

	@Override
	public Boolean getNewWindow() {
		
		return true;
	}

	@Override
	public String getColor() {
		
		return null;
	}

	@Override
	public Boolean getStrong() {
		
		return null;
	}

	@Override
	public Boolean getEm() {
		
		return null;
	}

	@Override
	public String getDescription() {
		
		return null;
	}
}
