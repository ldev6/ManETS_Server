package ca.etsmtl.gti785.model;

/**
 * Created by Phil on 25/09/13.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerState extends Feed {
	boolean pause = true;
	String currentID = "0";
	String currentMedia = "No meda";
	long currentLenght = 0L;
	long currentPosition = 0L;
	int volume = 0;

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getCurrentID() {
		return currentID;
	}

	public void setCurrentID(String currentID) {
		this.currentID = currentID;
	}

	public String getCurrentMedia() {
		return currentMedia;
	}

	public void setCurrentMedia(String currentMedia) {
		this.currentMedia = currentMedia;
	}

	public long getCurrentLenght() {
		return currentLenght;
	}

	public void setCurrentLenght(long currentLenght) {
		this.currentLenght = currentLenght;
	}

	public long getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(long currentPosition) {
		this.currentPosition = currentPosition;
	}
}
