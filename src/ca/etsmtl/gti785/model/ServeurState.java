package ca.etsmtl.gti785.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServeurState {

	Media currentMedia = null;
	int currentID = 0;
	boolean pause = false;
	float currentPosition = 0;
	int volume = 0;

	public ServeurState() {
	}

	public ServeurState(Media media, int id, int volume, float currentPosition) {
		currentMedia = media;
		currentID = id;
		pause = false;
		currentPosition = 0;
		this.volume = volume;
	}

	public Media getCurrentMedia() {
		return currentMedia;
	}

	public void setCurrentMedia(Media currentMedia) {
		this.currentMedia = currentMedia;
	}

	public int getCurrentID() {
		return currentID;
	}

	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public float getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(long currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
