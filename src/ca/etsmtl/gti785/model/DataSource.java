package ca.etsmtl.gti785.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource {

	private String id = "";

	private int refreshRate = 3600;

	private String url = "";

	private Class<? extends Feed> classObjectFeed;

	public DataSource(String id, String url) {
		// Map type with the Parser class
		this.id = id;
		// trim the url
		this.url = url;

	}

	public String getUrl() {
		return url;
	}

	public Class<? extends Feed> getClassObjectFeed() {
		return classObjectFeed;
	}

	public String getId() {
		return id;
	}

	public int getRefreshRate() {
		return refreshRate;
	}
}
