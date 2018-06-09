package org.persistence.layer.wits.comparator;

import java.util.Comparator;

import org.persistence.layer.wits.form.WamatObject;

public class WamatObjectComparer implements Comparator<WamatObject> {
	protected Boolean ascending;
	
    public WamatObjectComparer(Boolean ascending)
    {
    	this.ascending = ascending;
    }
	@Override
	public int compare(WamatObject o1, WamatObject o2) {
		if (ascending)
		{
			return new Integer(o1.getIdWamatObject()).compareTo(o2.getIdWamatObject());
		}
		else
		{
			return new Integer(o2.getIdWamatObject()).compareTo(o1.getIdWamatObject());
		}
	}

}
