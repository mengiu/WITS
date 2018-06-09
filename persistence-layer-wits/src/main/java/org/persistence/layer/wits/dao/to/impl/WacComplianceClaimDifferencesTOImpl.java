package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.WacComplianceClaimDifferencesTO;
import org.persistence.layer.wits.form.WacComplianceClaim;

public class WacComplianceClaimDifferencesTOImpl implements
		WacComplianceClaimDifferencesTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WacComplianceClaim before;
	protected WacComplianceClaim after;
	protected WacComplianceClaim wacComplianceClaimBeforeChanging;
	
	public WacComplianceClaimDifferencesTOImpl()
	{
		if (wacComplianceClaimBeforeChanging==null)
			wacComplianceClaimBeforeChanging = new WacComplianceClaim();
	}

	public WacComplianceClaimDifferencesTOImpl(WacComplianceClaim before , 
			WacComplianceClaim after)
	{
		this();
		this.after = after;
		this.before = before;
		wacComplianceClaimBeforeChanging.setIdWacComplianceClaim(after.getIdWacComplianceClaim());
		init();
	}

	public void init()
	{
		if (getIsNameWACChanged())
		{
			wacComplianceClaimBeforeChanging.setWacSt(after.getWacSt());
		}
		if (getIsWamatObjectChanged())
		{
			wacComplianceClaimBeforeChanging.setWamatObject(after.getWamatObject());
		}
		
	}
	
	public WacComplianceClaim getBefore() {
		return before;
	}
	public void setBefore(WacComplianceClaim before) {
		this.before = before;
	}
	public WacComplianceClaim getAfter() {
		return after;
	}
	public void setAfter(WacComplianceClaim after) {
		this.after = after;
	}

	public boolean getIsNameWACChanged() {
		if (before.getWacSt()!=null &&
				after.getWacSt()!=null)
			return !before.getWacSt().getNameWacSt().equals(after.getWacSt().getNameWacSt());
		else
		{
			if (before.getWacSt()==null &&
					after.getWacSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public boolean getIsWamatObjectChanged() {
		if (before.getWamatObject()!=null &&
				after.getWamatObject()!=null)
			return !(before.getWamatObject().getIdWamatObject()==
			         after.getWamatObject().getIdWamatObject());
		else
		{
			if (before.getWamatObject()==null &&
					after.getWamatObject()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public WacComplianceClaim getWacComplianceClaimBeforeChanging() {
		return wacComplianceClaimBeforeChanging;
	}

	public void setWacComplianceClaimBeforeChanging(
			WacComplianceClaim wacComplianceClaimBeforeChanging) {
		this.wacComplianceClaimBeforeChanging = wacComplianceClaimBeforeChanging;
	}

}
