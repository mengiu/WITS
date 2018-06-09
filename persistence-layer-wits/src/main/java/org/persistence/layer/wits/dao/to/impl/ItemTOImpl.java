package org.persistence.layer.wits.dao.to.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.PersistenceConstants;
import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.dao.to.ItemTO;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WamatObject;


@XmlRootElement
public class ItemTOImpl implements ItemTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idItem;
	private String wamatObject;
	private String generationProcessInstance;
	private String materialSt;
	private String descriptionItem;
	private Short itemNumber;
	private BigDecimal quantity;
	private Map<EntityEventType,Map<Integer,HistoryTO>> mapChanging;
	private String witsUserSt;
	private Date changingDate;
	private String workflow;
	private List<ViewMetadataColumnTable> listViewMetadataColumnTable;
	
	public ItemTOImpl()
	{
		if (mapChanging==null)
			mapChanging = new HashMap<EntityEventType,Map<Integer,HistoryTO>>();
		
	}
	public ItemTOImpl(List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
        this();
		this.listViewMetadataColumnTable = listViewMetadataColumnTable;		
	}
	public ItemTOImpl( ItemTO itemTO , String witsUserSt,
			Date changingDate, String workflow , EntityEventType entityEventType,
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		idItem = itemTO.getIdItem();
		if (itemTO.getGenerationProcessInstance()!=null)
		 generationProcessInstance = itemTO.getGenerationProcessInstance();
		if (itemTO.getMaterialSt()!=null)
		materialSt = itemTO.getMaterialSt();
		descriptionItem = itemTO.getDescriptionItem();
		itemNumber = itemTO.getItemNumber();
		quantity = itemTO.getQuantity();
		wamatObject = itemTO.getWamatObject();
		this.workflow = workflow;
		this.witsUserSt = witsUserSt;
		this.changingDate = changingDate;
	    init(entityEventType);
		
	}
	public ItemTOImpl(Item pItem,List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		idItem = pItem.getIdItem();
		if (pItem.getGenerationProcessInstance()!=null)
		 generationProcessInstance = pItem.getGenerationProcessInstance().getNameGpInstance();
		if (pItem.getMaterialSt()!=null)
		 materialSt = pItem.getMaterialSt().getNameMaterialSt();
		descriptionItem = pItem.getDescriptionItem();
		itemNumber = pItem.getItemNumber();
		quantity = pItem.getQuantity();
		if (pItem.getWamatObject()!=null)
		 wamatObject = pItem.getWamatObject().getNameWamatObject();
	}
	private void init(EntityEventType entityEventType)
	{
		Integer index = new Integer(0);
		String strNameEntity = Item.class.getAnnotation(Table.class).name();
		Map<Integer,HistoryTO> mapLocal = new HashMap<Integer, HistoryTO>();
		if (getDescriptionItem()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[1]));
			historyTOImpl.setValue(getDescriptionItem());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getWamatObject()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[2]));
			historyTOImpl.setValue(getWamatObject());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getGenerationProcessInstance()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[3]));
			historyTOImpl.setValue(getGenerationProcessInstance());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getItemNumber()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[4]));
			historyTOImpl.setValue(getItemNumber().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getQuantity()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[5]));
			historyTOImpl.setValue(getQuantity().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getMaterialSt()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_ITEM[6]));
			historyTOImpl.setValue(getMaterialSt());
			mapLocal.put(index++, historyTOImpl);
		}
		mapChanging.put(entityEventType, mapLocal);
		
	}
	
	@Override
	@XmlAttribute
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	@Override
	@XmlAttribute
	public String getGenerationProcessInstance() {
		return generationProcessInstance;
	}
	public void setGenerationProcessInstance(String generationProcessInstance) {
		this.generationProcessInstance = generationProcessInstance;
	}
	@Override
	@XmlAttribute
	public String getMaterialSt() {
		return materialSt;
	}
	public void setMaterialSt(String materialSt) {
		this.materialSt = materialSt;
	}
	@Override
	@XmlAttribute
	public String getDescriptionItem() {
		return descriptionItem;
	}
	public void setDescriptionItem(String descriptionItem) {
		this.descriptionItem = descriptionItem;
	}
	@Override
	@XmlAttribute
	public Short getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Short itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Override
	@XmlAttribute
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	@XmlAttribute
	public String getWamatObject() {
		return wamatObject;
	}
	public void setWamatObject(String wamatObject) {
		this.wamatObject = wamatObject;
	}
	public Map<EntityEventType,Map<Integer,HistoryTO>> getMapChanging() {
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

}
