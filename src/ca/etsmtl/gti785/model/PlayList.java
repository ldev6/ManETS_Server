package ca.etsmtl.gti785.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * Created by Phil on 24/09/13.
 */
public class PlayList extends Feed {
	public List<String> paths = new ArrayList<String>();
	public int index = 0;

	public boolean hasReachedEnd() {
		return index < paths.size();
	}

	public String getNext() {
		return paths.get(index);
	}

	public void randomise() {

	}
}
