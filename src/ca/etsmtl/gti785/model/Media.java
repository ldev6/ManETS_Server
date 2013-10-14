package ca.etsmtl.gti785.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Phil on 26/09/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

	String media;
	String titre;
	String artiste;
	String duree;
	String album;
	String path;

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArtiste() {
		return artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
