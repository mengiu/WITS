package org.persistence.layer.wits.dao.to.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.dao.to.WamatExtendedFieldTO;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.WamatExtendedField;

@XmlRootElement
public class WamatExtendedFieldTOImpl implements WamatExtendedFieldTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idWamatExtendedField;
	private Boolean confirmed;
	private String wamatObject;
	private String wamatExtendedFieldTypeSt;
	private String value;
	private Map<EntityEventType,Map<Integer,HistoryTO>> mapChanging;
	private String witsUserSt;
	private Date changingDate;
	private String workflow;

	public WamatExtendedFieldTOImpl()
	{
		if (mapChanging==null)
			mapChanging = new HashMap<EntityEventType,Map<Integer,HistoryTO>>();
		
	}
	
	public WamatExtendedFieldTOImpl( WamatExtendedField wamatExtendedField )
	{
		setIdWamatExtendedField(wamatExtendedField.getIdWamatExtendedField());
		if (wamatExtendedField.getWamatObject()!=null)
		 setWamatObject(wamatExtendedField.getWamatObject().getNameWamatObject());
		if (wamatExtendedField.getWamatExtendedFieldTypeSt()!=null)
		 setWamatExtendedFieldTypeSt(wamatExtendedField.getWamatExtendedFieldTypeSt().getNameWamatExtField());
		setValue(wamatExtendedField.getValue());
	}
	
	
	public WamatExtendedFieldTOImpl( WamatExtendedFieldTO wamatExtendedFieldTO , String witsUserSt,
			Date changingDate, String workflow , EntityEventType entityEventType)
	{
		this();
		setIdWamatExtendedField(wamatExtendedFieldTO.getIdWamatExtendedField());
		setWamatObject(wamatExtendedFieldTO.getWamatObject());
		setWamatExtendedFieldTypeSt(wamatExtendedFieldTO.getWamatExtendedFieldTypeSt());
		setValue(wamatExtendedFieldTO.getValue());
		this.workflow = workflow;
		this.witsUserSt = witsUserSt;
		this.changingDate = changingDate;
	    init(entityEventType);
	}
	public void init(EntityEventType entityEventType)
	{
		Integer index = new Integer(0);
		String strNameEntity = WamatExtendedField.class.getAnnotation(Table.class).name();
		Map<Integer,HistoryTO> mapLocal = new HashMap<Integer, HistoryTO>();
		if (getValue()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getWamatExtendedFieldTypeSt());
			historyTOImpl.setValue(getValue());
			mapLocal.put(index++, historyTOImpl);
		}
		mapChanging.put(entityEventType, mapLocal);
	}

	@Override
	@XmlAttribute
	public int getIdWamatExtendedField() {
		return idWamatExtendedField;
	}

	public void setIdWamatExtendedField(int idWamatExtendedField) {
		this.idWamatExtendedField = idWamatExtendedField;
	}

	@Override
	@XmlAttribute
	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	@XmlAttribute
	public String getWamatObject() {
		return wamatObject;
	}

	public void setWamatObject(String wamatObject) {
		this.wamatObject = wamatObject;
	}

	@Override
	@XmlAttribute
	public String getWamatExtendedFieldTypeSt() {
		return wamatExtendedFieldTypeSt;
	}

	public void setWamatExtendedFieldTypeSt(String wamatExtendedFieldTypeSt) {
		this.wamatExtendedFieldTypeSt = wamatExtendedFieldTypeSt;
	}

	@Override
	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public Map<EntityEventType,Map<Integer,HistoryTO>> getMapChanging() {
		return mapChanging;
	}



}
