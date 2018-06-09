package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.WamatExtendedFieldDifferencesTO;
import org.persistence.layer.wits.form.WamatExtendedField;

public class WamatExtendedFieldDifferencesTOImpl implements
		WamatExtendedFieldDifferencesTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WamatExtendedField before;
	protected WamatExtendedField after;
	protected WamatExtendedField wamatExtendedFieldChanging;
	
	public WamatExtendedFieldDifferencesTOImpl()
	{
		if (wamatExtendedFieldChanging==null)
			wamatExtendedFieldChanging = new WamatExtendedField();
	}

	public WamatExtendedFieldDifferencesTOImpl(WamatExtendedField before , 
			WamatExtendedField after)
	{
		this();
		this.after = after;
		this.before = before;
		wamatExtendedFieldChanging.setIdWamatExtendedField(after.getIdWamatExtendedField());
		wamatExtendedFieldChanging.setWamatExtendedFieldTypeSt(after.getWamatExtendedFieldTypeSt());
		init();
	}

	public void init()
	{
		if (getIsValueChanged())
		{
			wamatExtendedFieldChanging.setValue(after.getValue());
		}
		
	}

	public WamatExtendedField getBefore() {
		return before;
	}

	public void setBefore(WamatExtendedField before) {
		this.before = before;
	}

	public WamatExtendedField getAfter() {
		return after;
	}

	public void setAfter(WamatExtendedField after) {
		this.after = after;
	}



	public boolean getIsValueChanged() {
		if (before.getValue()!=null &&
				after.getValue()!=null)
			return !before.getValue().trim().equals(after.getValue().trim());
		else
		{
			if (
				(before.getValue()==null &&  after.getValue()==null ) ||
			    ((before.getValue()!=null && before.getValue().trim().length()== 0) && 
			    (after.getValue()!=null && after.getValue().trim().length()== 0))
			    )
				return false;
			else
			{
				return true;
			}
		}
	}

	public WamatExtendedField getWamatExtendedFieldChanging() {
		return wamatExtendedFieldChanging;
	}

	public void setWamatExtendedFieldChanging(
			WamatExtendedField wamatExtendedFieldBeforeChanging) {
		this.wamatExtendedFieldChanging = wamatExtendedFieldBeforeChanging;
	}


}
