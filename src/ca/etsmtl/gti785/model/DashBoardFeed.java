package ca.etsmtl.gti785.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DashBoardFeed extends Feed {

	private ArrayList<DataSource> dataSources;
	private Settings settings;

	public DashBoardFeed(ArrayList<DataSource> dataSources, Settings settings) {
		super();
		this.dataSources = dataSources;
		this.settings = settings;
	}

	// //
	// // Accessors
	// //

	public Settings getSettings() {
		return settings;
	}

	public ArrayList<DataSource> getDataSources() {
		return dataSources;
	}

	// //
	// // Inner Class
	// //

	public static class Settings {

		private Expirations expirations = new Expirations();
		public boolean canStream = false;

		// //
		// // Accessors
		// //

		public Expirations getExpiration() {
			return expirations;
		}

		public Settings() {
			// TODO Auto-generated constructor stub
		}

		public Expirations getExpirations() {
			return expirations;
		}

		public void setExpirations(Expirations expirations) {
			this.expirations = expirations;
		}

		public boolean isCanStream() {
			return canStream;
		}

		public void setCanStream(boolean canStream) {
			this.canStream = canStream;
		}

	}

}
