package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.AttachedDocument2TableDifferencesTO;
import org.persistence.layer.wits.form.AttachedDocument2Table;

public class AttachedDocument2TableDifferencesTOImpl 
                   implements AttachedDocument2TableDifferencesTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AttachedDocument2Table before;
	AttachedDocument2Table after;
	AttachedDocument2Table entitywithChanging;
	
	public AttachedDocument2TableDifferencesTOImpl()
	{
		if (entitywithChanging==null)
			entitywithChanging = new AttachedDocument2Table();
	}

	public AttachedDocument2TableDifferencesTOImpl(AttachedDocument2Table before , 
			AttachedDocument2Table after)
	{
		this();
		entitywithChanging.setId(after.getId());
		this.after = after;
		this.before = before;
		init();
	}
	public void init()
	{
		if (getIsNameAttachedDocumentchanged())
		{
			entitywithChanging.setNameAttachedDocument(after.getNameAttachedDocument());
		}
		if (getIsActivechanged())
		{
			entitywithChanging.setActive(after.getActive());
		}
	}

	public Boolean getIsNameAttachedDocumentchanged() {
		if (before.getNameAttachedDocument()!=null &&
				after.getNameAttachedDocument()!=null)
			return !before.getNameAttachedDocument().equals(after.getNameAttachedDocument());
		else
		{
			if (before.getNameAttachedDocument()==null &&
					after.getNameAttachedDocument()==null)
				return false;
			else
			{
				return true;
			}
		}
	}
	public Boolean getIsActivechanged() {
		if (before.getActive()!=null &&
				after.getActive()!=null)
			return before.getActive()!=after.getActive();
		else
		{
			if (before.getActive()==null &&
					after.getActive()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public AttachedDocument2Table getBefore() {
		return before;
	}

	public void setBefore(AttachedDocument2Table before) {
		this.before = before;
	}

	public AttachedDocument2Table getAfter() {
		return after;
	}

	public void setAfter(AttachedDocument2Table after) {
		this.after = after;
	}

	public AttachedDocument2Table getEntitywithChanging() {
		return entitywithChanging;
	}

	public void setEntitywithChanging(AttachedDocument2Table entitywithChanging) {
		this.entitywithChanging = entitywithChanging;
	}
	
}
