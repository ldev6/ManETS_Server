package ca.etsmtl.gti785;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author http://software.danielwatrous.com/restful-java-servlet/
 */
class RestRequest {
	private Pattern listAllPattern = Pattern.compile("/list");

	private Pattern listIdPattern = Pattern.compile("/list/*");

	private Pattern openPattern = Pattern.compile("/open/*");

	private Pattern statePattern = Pattern.compile("/state/*");

	private Pattern pausePattern = Pattern.compile("/pause");

	private Pattern stopPattern = Pattern.compile("/stop");

	private Pattern nextPattern = Pattern.compile("/next");

	// private Pattern volumePattern = Pattern.compile("/volume/([0-9]*)");
	private Pattern volumePattern = Pattern.compile("/volume");

	// private Pattern seekPattern = Pattern.compile("/seek/([0-9]*)");
	private Pattern seekPattern = Pattern.compile("/seek");

	private Pattern playlistPattern = Pattern.compile("/playlist");

	private Pattern playlistPlayPattern = Pattern.compile("/playlist-play");

	private Pattern previousPattern = Pattern.compile("/previous");

	private Pattern addPattern = Pattern.compile("/add");

	private Pattern removePattern = Pattern.compile("/remove");

	private Pattern clearPattern = Pattern.compile("/clear");

	private Pattern orderPattern = Pattern.compile("/order");

	private Object id;

	public Controller c = Controller.NONE;

	public RestRequest(String pathInfo) {
		// regex parse pathInfo
		Matcher matcher;

		// Check for ID case first, since the All pattern would also match
		matcher = listAllPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.LIST;
			return;
		}

		matcher = listIdPattern.matcher(pathInfo);
		if (matcher.find()) {
			id = matcher.group(1);
			c = Controller.LIST;
			return;
		}

		matcher = openPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.OPEN;
			return;
		}

		matcher = statePattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.STATE;
			return;
		}

		matcher = pausePattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.PAUSE;
			return;
		}

		matcher = stopPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.STOP;
			return;
		}

		matcher = nextPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.NEXT;
			return;
		}

		matcher = volumePattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.VOLUME;
			return;
		}

		matcher = seekPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.SEEK;
			return;
		}
		
		matcher = playlistPlayPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.PLAYPLAYLIST;
			return;
		}

		matcher = playlistPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.PLAYLIST;
			return;
		}


		matcher = previousPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.PREVIOUS;
			return;
		}

		matcher = addPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.ADD;
			return;
		}

		matcher = removePattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.REMOVE;
			return;
		}

		matcher = clearPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.CLEAR;
			return;
		}

		matcher = orderPattern.matcher(pathInfo);
		if (matcher.find()) {
			c = Controller.ORDER;
			return;
		}

		id = -404;
		c = Controller.NONE;
		return;
	}

	public Object getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
