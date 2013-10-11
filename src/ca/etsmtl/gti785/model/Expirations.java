package ca.etsmtl.gti785.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Expirations {

	private String version = "1.0.0";
	private String warning_date = "2014-01-01";
	private String expiration_date = "2015-01-01";
	private String warning_message = "Warning this app is out of date";
	private String expiration_message = "Warning this app is crap";

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the warning_date
	 */
	public String getWarning_date() {
		return warning_date;
	}

	/**
	 * @return the expiration_date
	 */
	public String getExpirationDate() {
		return expiration_date;
	}

	/**
	 * @return the warning_message
	 */
	public String getWarning_message() {
		return warning_message;
	}

	/**
	 * @return the expiration_message
	 */
	public String getExpirationMessage() {
		return expiration_message;
	}

}
