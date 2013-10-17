package ca.etsmtl.gti785.model;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Phil on 26/09/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

	String titre = "";
	String artist = "";
	Long duree = 0L;
	String album = "";
	String path = "";

	public Media() {
		// TODO Auto-generated constructor stub
	}

	public Media(Tag tag, String path, int length) {
		titre = tag.getFirst(FieldKey.TITLE);
		artist = tag.getFirst(FieldKey.ARTIST);
		album = tag.getFirst(FieldKey.ALBUM);
		this.duree = (long) length * 1000;
		this.path = path;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtiste(String artiste) {
		this.artist = artiste;
	}

	public Long getDuree() {
		return duree;
	}

	public void setDuree(Long duree) {
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
