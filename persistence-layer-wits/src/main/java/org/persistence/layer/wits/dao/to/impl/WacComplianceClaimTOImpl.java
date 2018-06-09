package org.persistence.layer.wits.dao.to.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.PersistenceConstants;
import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.dao.to.WacComplianceClaimTO;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WacComplianceClaim;

@XmlRootElement
public class WacComplianceClaimTOImpl implements WacComplianceClaimTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idWacComplianceClaim;
	private String wamatObject;
	private String wacSt;
	private Map<EntityEventType,Map<Integer,HistoryTO>> mapChanging;
	private String witsUserSt;
	private Date changingDate;
	private String workflow;
	private List<ViewMetadataColumnTable> listViewMetadataColumnTable;
	
	public WacComplianceClaimTOImpl()
	{
		if (mapChanging==null)
			mapChanging = new HashMap<EntityEventType,Map<Integer,HistoryTO>>();
		
	}

	public WacComplianceClaimTOImpl(List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
        this();
		this.listViewMetadataColumnTable = listViewMetadataColumnTable;
		
	}
	
	public WacComplianceClaimTOImpl( WacComplianceClaim wacComplianceClaim,
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		setIdWacComplianceClaim(wacComplianceClaim.getIdWacComplianceClaim());
		if (wacComplianceClaim.getWacSt()!=null)
		 setWacSt(wacComplianceClaim.getWacSt().getNameWacSt());
		if (wacComplianceClaim.getWamatObject()!=null)
		 setWamatObject(wacComplianceClaim.getWamatObject().getNameWamatObject());
	}
	
	
	public WacComplianceClaimTOImpl( WacComplianceClaimTO wacComplianceClaimTO , String witsUserSt,
			Date changingDate, String workflow , EntityEventType entityEventType,
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		setWacSt(wacComplianceClaimTO.getWacSt());
		setWamatObject(wacComplianceClaimTO.getWamatObject());
		this.workflow = workflow;
		this.witsUserSt = witsUserSt;
		this.changingDate = changingDate;
	    init(entityEventType);
	}
	private void init(EntityEventType entityEventType)
	{
		Integer index = new Integer(0);
		String strNameEntity = WacComplianceClaim.class.getAnnotation(Table.class).name();
		Map<Integer,HistoryTO> mapLocal = new HashMap<Integer, HistoryTO>();
		if (getWamatObject()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAC_COMPLIANCE_CLAIM[1]));
			historyTOImpl.setValue(getWamatObject());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getWacSt()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAC_COMPLIANCE_CLAIM[2]));
			historyTOImpl.setValue(getWacSt());
			mapLocal.put(index++, historyTOImpl);
		}
		mapChanging.put(entityEventType, mapLocal);
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
	public String getWacSt() {
		return wacSt;
	}
	public void setWacSt(String wacSt) {
		this.wacSt = wacSt;
	}

	@Override
	@XmlAttribute
	public int getIdWacComplianceClaim() {
		return idWacComplianceClaim;
	}

	@Override
	public Map<EntityEventType,Map<Integer,HistoryTO>> getMapChanging() {
		return mapChanging;
	}

	public void setIdWacComplianceClaim(int idWacComplianceClaim) {
		this.idWacComplianceClaim = idWacComplianceClaim;
	}
    private String getCommentColumnTable(String column) {
    	if (listViewMetadataColumnTable!=null)
    	{
    		for (ViewMetadataColumnTable item : listViewMetadataColumnTable)
    		{
    			if (item.getColumnName().equals(column.toUpperCase()))
    			{
    				return ((item.getComments()!=null) ? item.getComments() : "null");
    			}
    		}
    	}
    	return "null";
    }

}
