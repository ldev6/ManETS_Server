package ca.etsmtl.gti785.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Expirations {

    public static class Expiration {

        private String version;
        private String warning_date;
        private String expiration_date;
        private String warning_message;
        private String expiration_message;

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

    private List<Expiration> expirations = new ArrayList<Expiration>();

    /**
     * @return the expirations
     */
    public List<Expiration> getExpirations() {
        return expirations;
    }

}
