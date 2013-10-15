package ca.etsmtl.gti785.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DashBoardFeed extends Feed {

	private ArrayList<DataSource> dataSources;

	public DashBoardFeed(ArrayList<DataSource> dataSources ) {
		super();
		this.dataSources = dataSources;
	}

	// //
	// // Accessors
	// //



	public ArrayList<DataSource> getDataSources() {
		return dataSources;
	}
}
