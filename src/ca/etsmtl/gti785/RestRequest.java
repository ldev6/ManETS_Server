package ca.etsmtl.gti785;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

/**
 * @author http://software.danielwatrous.com/restful-java-servlet/
 */
class RestRequest {
	// Accommodate two requests, one for all resources, another for a specific
	// resource
	private Pattern regExAllPattern = Pattern.compile("/ServletManETS");
	private Pattern regExIdPattern = Pattern.compile("/ServletManETS/([0-9]*)");

	private Integer id;

	public RestRequest(String pathInfo) throws ServletException {
		// regex parse pathInfo
		Matcher matcher;

		// Check for ID case first, since the All pattern would also match
		matcher = regExIdPattern.matcher(pathInfo);
		if (matcher.find()) {
			id = Integer.parseInt(matcher.group(1));
			return;
		}

		matcher = regExAllPattern.matcher(pathInfo);
		if (matcher.find())
			return;

		throw new ServletException("Invalid URI");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
