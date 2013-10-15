package ca.etsmtl.gti785.model;

import java.util.List;

public class ListReponse {

	private List<RepertoireDefinition> listRepertoire;
	private List<Media> listMedia;

	public ListReponse(List<RepertoireDefinition> listRepertoire,
			List<Media> listMedia) {
		this.listRepertoire = listRepertoire;
		// TODO Auto-generated constructor stub
		this.listMedia = listMedia;
	}

	public List<RepertoireDefinition> getListRepertoire() {
		return listRepertoire;
	}

	public void setListRepertoire(List<RepertoireDefinition> listRepertoire) {
		this.listRepertoire = listRepertoire;
	}

	public List<Media> getListMedia() {
		return listMedia;
	}

	public void setListMedia(List<Media> listMedia) {
		this.listMedia = listMedia;
	}

}
