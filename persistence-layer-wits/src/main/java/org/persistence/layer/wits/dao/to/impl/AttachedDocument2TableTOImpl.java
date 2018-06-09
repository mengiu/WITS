package org.persistence.layer.wits.dao.to.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.PersistenceConstants;
import org.persistence.layer.wits.dao.to.AttachedDocument2TableTO;
import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;

@XmlRootElement
public class AttachedDocument2TableTOImpl implements AttachedDocument2TableTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fkAttachedDocument;
	private String fkTableName;
	private int fkTableId;
	private String nameAttachedDocument;
	private Boolean active;
	private String witsUserSt;
	private Date changingDate;
	private String workflow;
	private Map<EntityEventType,Map<Integer,HistoryTO>> mapChanging;
	private List<ViewMetadataColumnTable> listViewMetadataColumnTable;

	public AttachedDocument2TableTOImpl()
	{
		if (mapChanging==null)
			mapChanging = new HashMap<EntityEventType, Map<Integer,HistoryTO>>();
	}
	public AttachedDocument2TableTOImpl(List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this();
		this.listViewMetadataColumnTable = listViewMetadataColumnTable;
	}

	public AttachedDocument2TableTOImpl(AttachedDocument2Table attachedDocument2Table,
			EntityEventType entityEventType, 
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		this.fkTableId = attachedDocument2Table.getId().getFkTableId();
		this.fkTableName = attachedDocument2Table.getId().getFkTableName();
		this.fkAttachedDocument = attachedDocument2Table.getId().getFkAttachedDocument();
		this.nameAttachedDocument = attachedDocument2Table.getNameAttachedDocument();
		this.active = attachedDocument2Table.getActive();
	}
	
	public AttachedDocument2TableTOImpl(AttachedDocument2TableTO attachedDocument2TableTO,
			String witsUserSt,
			Date changingDate, String workflow ,			
			EntityEventType entityEventType, 
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		this.witsUserSt = witsUserSt;
		this.changingDate = changingDate;
		this.workflow = workflow;
		this.fkTableId = attachedDocument2TableTO.getFkTableId();
		this.fkTableName = attachedDocument2TableTO.getFkTableName();
		this.fkAttachedDocument = attachedDocument2TableTO.getFkAttachedDocument();
		this.nameAttachedDocument = attachedDocument2TableTO.getNameAttachedDocument();
		this.active = attachedDocument2TableTO.getActive();
		init(entityEventType);
	}

	@Override
	@XmlAttribute
	public int getFkAttachedDocument() {
		return fkAttachedDocument;
	}
	public void setFkAttachedDocument(int fkAttachedDocument) {
		this.fkAttachedDocument = fkAttachedDocument;
	}
	@Override
	@XmlAttribute
	public String getFkTableName() {
		return fkTableName;
	}
	public void setFkTableName(String fkTableName) {
		this.fkTableName = fkTableName;
	}
	@Override
	@XmlAttribute
	public int getFkTableId() {
		return fkTableId;
	}
	public void setFkTableId(int fkTableId) {
		this.fkTableId = fkTableId;
	}
	@Override
	@XmlAttribute
	public String getNameAttachedDocument() {
		return nameAttachedDocument;
	}
	public void setNameAttachedDocument(String nameAttachedDocument) {
		this.nameAttachedDocument = nameAttachedDocument;
	}

	@Override
	@XmlAttribute
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging() {
		return mapChanging;
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

	private void init(
			EntityEventType entityEventType) {
		Integer index = new Integer(0);
		Map<Integer,HistoryTO> mapLocal = new HashMap<Integer, HistoryTO>();
		if (getNameAttachedDocument()!=null)
		{
		 HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
		 historyTOImpl.setRefer(getFkTableName());
		 historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ATTACHED_DOCUMENT_2_TABLE[3]));
		 historyTOImpl.setValue(getNameAttachedDocument());
		 mapLocal.put(index++, historyTOImpl);
		}
		if (getActive()!=null)
		{
		 HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
		 historyTOImpl.setRefer(getFkTableName());
		 historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ATTACHED_DOCUMENT_2_TABLE[4]));
		 historyTOImpl.setValue(getActive().toString());
		 mapLocal.put(index++, historyTOImpl);
		}
		mapChanging.put(entityEventType, mapLocal);
	}


}
