package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.ItemDifferencesTO;
import org.persistence.layer.wits.form.Item;

public class ItemDifferencesTOImpl implements ItemDifferencesTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Item before;
	protected Item after;
	protected Item itemwithChanging;

	public ItemDifferencesTOImpl()
	{
		if (itemwithChanging==null)
			itemwithChanging = new Item();
	}

	public ItemDifferencesTOImpl(Item before , Item after)
	{
		this();
		this.after = after;
		this.before = before;
		itemwithChanging.setIdItem(after.getIdItem());
		init();
	}
	
	public void init()
	{
		if (getIsDescriptionItemChanged())
		{
			itemwithChanging.setDescriptionItem(after.getDescriptionItem());
		}
		if (getIsWamatObjectChanged())
		{
			itemwithChanging.setWamatObject(after.getWamatObject());
		}
		if (getIsGenerationProcessInstanceChanged())
		{
			itemwithChanging.setGenerationProcessInstance(after.getGenerationProcessInstance());
		}
		if (getIsItemNumberChanged())
		{
			itemwithChanging.setItemNumber(after.getItemNumber());
		}
		if (getIsQuantityChanged())
		{
			itemwithChanging.setQuantity(after.getQuantity());
		}
		if (getIsMaterialStChanged())
		{
			itemwithChanging.setMaterialSt(after.getMaterialSt());
		}
		
	}

	@Override
	public Item getBefore() {
		return before;
	}

	@Override
	public Item getAfter() {
		return after;
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

	public boolean getIsGenerationProcessInstanceChanged() {
		if (before.getGenerationProcessInstance()!=null &&
				after.getGenerationProcessInstance()!=null)
			return !(before.getGenerationProcessInstance().getIdGpInstance()==
			         after.getGenerationProcessInstance().getIdGpInstance());
		else
		{
			if (before.getGenerationProcessInstance()==null &&
					after.getGenerationProcessInstance()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public boolean getIsMaterialStChanged() {
		if (before.getMaterialSt()!=null &&
				after.getMaterialSt()!=null)
			return !(before.getMaterialSt().getIdMaterialSt()==after.getMaterialSt().getIdMaterialSt());
		else
		{
			if (before.getMaterialSt()==null &&
					after.getMaterialSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public boolean getIsDescriptionItemChanged() {
		if (before.getDescriptionItem()!=null &&
				after.getDescriptionItem()!=null)
			return !before.getDescriptionItem().equals(after.getDescriptionItem());
		else
		{
			if (before.getDescriptionItem()==null &&
					after.getDescriptionItem()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public boolean getIsItemNumberChanged() {
		if (before.getItemNumber()!=null &&
				after.getItemNumber()!=null)
			return before.getItemNumber()!=after.getItemNumber();
		else
		{
			if (before.getItemNumber()==null &&
					after.getItemNumber()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public boolean getIsQuantityChanged() {
		if (before.getQuantity()!=null &&
				after.getQuantity()!=null)
			return !(before.getQuantity().compareTo(after.getQuantity())==0);
		else
		{
			if (before.getQuantity()==null &&
					after.getQuantity()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public void setBefore(Item before) {
		this.before = before;
	}

	public void setAfter(Item after) {
		this.after = after;
	}

	public Item getItemwithChanging() {
		return itemwithChanging;
	}

	public void setItemwithChanging(Item itemwithChanging) {
		this.itemwithChanging = itemwithChanging;
	}

}
