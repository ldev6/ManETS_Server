package ca.etsmtl.gti785.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DashBoardFeed extends Feed {

    private ArrayList<DataSource> dataSources;
    private Settings settings;

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
        // //
        // // Accessors
        // //

        public Expirations getExpiration() {
            return expirations;
        }
    }

}
