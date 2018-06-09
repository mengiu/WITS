package org.persistence.layer.wits.dao.to.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.PersistenceConstants;
import org.persistence.layer.wits.dao.to.HistoryTO;
import org.persistence.layer.wits.dao.to.WamatObjectTO;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WamatObject;

@XmlRootElement
public class WamatObjectTOImpl implements WamatObjectTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idWamatObject;
	private String statusSt;
	private String ownerSt;
	private String wasteManagementPlan;
	private String homogeneousLot;
	private String wamatProcessInst;
	private String materialSt;
	private String containingUnit;
	private String description;
	private BigDecimal netMass;
	private Short positionX;
	private Short positionY;
	private Short positionZ;
	private String code;
	private Boolean packaged;
	private Map<EntityEventType,Map<Integer,HistoryTO>> mapChanging;
	private String witsUserSt;
	private Date changingDate;
	private String workflow;
	private BigDecimal rpDoseRateContact;
	private BigDecimal rpDoseRate1m;
	private Boolean rpSurfaceContamination;
	private Date rpMeasurementDate;
	private Date eventClosureDate;
	private List<ViewMetadataColumnTable> listViewMetadataColumnTable;
     
	public WamatObjectTOImpl()
	{
		if (mapChanging==null)
			mapChanging = new HashMap<EntityEventType,Map<Integer,HistoryTO>>();
	}

	public WamatObjectTOImpl(List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
        this();
		this.listViewMetadataColumnTable = listViewMetadataColumnTable;
	}
	
	public WamatObjectTOImpl( WamatObject wamatObject, List<ViewMetadataColumnTable> listViewMetadataColumnTable )
	{
		this(listViewMetadataColumnTable);
		setIdWamatObject(wamatObject.getIdWamatObject());
		if (wamatObject.getStatusSt()!=null)
		 setStatusSt(wamatObject.getStatusSt().getNameStatusSt());
		if (wamatObject.getOwnerSt()!=null)
		 setOwnerSt(wamatObject.getOwnerSt().getNameOwnerSt());
		if (wamatObject.getWasteManagementPlanSt()!=null)
		 setWasteManagementPlan(wamatObject.getWasteManagementPlanSt().getNameWasteManagementPlanSt());
		if (wamatObject.getHomogeneousLot()!=null)
		 setHomogeneousLot(wamatObject.getHomogeneousLot().getNameHomogeneousLot());
		if (wamatObject.getGenerationProcessInstance()!=null)
		 setWamatProcessInst(wamatObject.getGenerationProcessInstance().getNameGpInstance());
		if (wamatObject.getMaterialSt()!=null)
		 setMaterialSt(wamatObject.getMaterialSt().getNameMaterialSt());
		if (wamatObject.getContainingUnitByFkContainingUnit()!=null)
		 setContainingUnit(wamatObject.getContainingUnitByFkContainingUnit().getNameContainingUnit());
		setDescription(wamatObject.getDescriptionWamatObject());
		setNetMass(wamatObject.getNetMass());
		setPositionX(wamatObject.getPositionX());
		setPositionY(wamatObject.getPositionY());
		setPositionZ(wamatObject.getPositionZ());
		setCode(wamatObject.getNameWamatObject());
		setPackaged(wamatObject.getPackaged());
		setRpDoseRate1m(wamatObject.getRpDoseRate1m());
		setRpDoseRateContact(wamatObject.getRpDoseRateContact());
		setRpMeasurementDate(wamatObject.getRpMeasurementDate());
		setRpSurfaceContamination(wamatObject.getRpSurfaceContamination());
		setEventClosureDate(wamatObject.getEventClosureDate());
	}
	public WamatObjectTOImpl( WamatObjectTO wamatObjectTO , String witsUserSt,
			Date changingDate, String workflow , EntityEventType entityEventType,
			List<ViewMetadataColumnTable> listViewMetadataColumnTable)
	{
		this(listViewMetadataColumnTable);
		setIdWamatObject(wamatObjectTO.getIdWamatObject());
		setStatusSt(wamatObjectTO.getStatusSt());
		setOwnerSt(wamatObjectTO.getOwnerSt());
		setWasteManagementPlan(wamatObjectTO.getWasteManagementPlan());
		setHomogeneousLot(wamatObjectTO.getHomogeneousLot());
		setWamatProcessInst(wamatObjectTO.getWamatProcessInst());
		setMaterialSt(wamatObjectTO.getMaterialSt());
		setContainingUnit(wamatObjectTO.getContainingUnit());
		setDescription(wamatObjectTO.getDescription());
		setNetMass(wamatObjectTO.getNetMass());
		setPositionX(wamatObjectTO.getPositionX());
		setPositionY(wamatObjectTO.getPositionY());
		setPositionZ(wamatObjectTO.getPositionZ());
		setCode(wamatObjectTO.getCode());
		setPackaged(wamatObjectTO.getPackaged());
		setRpDoseRate1m(wamatObjectTO.getRpDoseRate1m());
		setRpDoseRateContact(wamatObjectTO.getRpDoseRateContact());
		setRpMeasurementDate(wamatObjectTO.getRpMeasurementDate());
		setRpSurfaceContamination(wamatObjectTO.getRpSurfaceContamination());
		setEventClosureDate(wamatObjectTO.getEventClosureDate());
		this.workflow = workflow;
		this.witsUserSt = witsUserSt;
		this.changingDate = changingDate;
	    init(entityEventType);	
	}

	@Override
	@XmlAttribute
	public int getIdWamatObject() {
		return idWamatObject;
	}

	@Override
	@XmlAttribute
	public String getStatusSt() {
		return statusSt;
	}


	@Override
	@XmlAttribute
	public String getOwnerSt() {
		return ownerSt;
	}

	@Override
	@XmlAttribute
	public String getWasteManagementPlan() {
		return wasteManagementPlan;
	}

	@Override
	@XmlAttribute
	public String getHomogeneousLot() {
		return homogeneousLot;
	}

	@Override
	@XmlAttribute
	public String getWamatProcessInst() {
		return wamatProcessInst;
	}

	@Override
	@XmlAttribute
	public String getMaterialSt() {
		return materialSt;
	}

	@Override
	@XmlAttribute
	public String getContainingUnit() {
		return containingUnit;
	}


	@Override
	@XmlAttribute
	public String getDescription() {
		return description;
	}

	@Override
	@XmlAttribute
	public BigDecimal getNetMass() {
		return netMass;
	}


	@Override
	@XmlAttribute
	public Short getPositionX() {
		return positionX;
	}

	@Override
	@XmlAttribute
	public Short getPositionY() {
		return positionY;
	}

	@Override
	@XmlAttribute
	public Short getPositionZ() {
		return positionZ;
	}

	@Override
	@XmlAttribute
	public String getCode() {
		return code;
	}



	@Override
	@XmlAttribute
	public Boolean getPackaged() {
		return packaged;
	}

	@Override
	@XmlAttribute
	public BigDecimal getRpDoseRateContact() {
		return rpDoseRateContact;
	}
	@Override
	@XmlAttribute
	public BigDecimal getRpDoseRate1m() {
		return rpDoseRate1m;
	}
	@Override
	@XmlAttribute
	public Boolean getRpSurfaceContamination() {
		return rpSurfaceContamination;
	}
	@Override
	@XmlAttribute
	public Date getRpMeasurementDate() {
		return rpMeasurementDate;
	}
	@Override
	@XmlAttribute
	public Date getEventClosureDate() {
		return eventClosureDate;
	}

	public void setIdWamatObject(int idWamatObject) {
		this.idWamatObject = idWamatObject;
	}
	public void setStatusSt(String statusSt) {
		this.statusSt = statusSt;
	}
	public void setOwnerSt(String ownerSt) {
		this.ownerSt = ownerSt;
	}
	public void setWasteManagementPlan(String wasteManagementPlan) {
		this.wasteManagementPlan = wasteManagementPlan;
	}
	public void setHomogeneousLot(String homogeneousLot) {
		this.homogeneousLot = homogeneousLot;
	}
	public void setWamatProcessInst(String wamatProcessInst) {
		this.wamatProcessInst = wamatProcessInst;
	}
	public void setMaterialSt(String materialSt) {
		this.materialSt = materialSt;
	}
	public void setContainingUnit(String containingUnit) {
		this.containingUnit = containingUnit;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNetMass(BigDecimal netMass) {
		this.netMass = netMass;
	}
	public void setPositionX(Short positionX) {
		this.positionX = positionX;
	}
	public void setPositionY(Short positionY) {
		this.positionY = positionY;
	}
	public void setPositionZ(Short positionZ) {
		this.positionZ = positionZ;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public void setPackaged(Boolean packaged) {
		this.packaged = packaged;
	}

	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging() {
		return mapChanging;
	}

	public String getWitsUserSt() {
		return witsUserSt;
	}

	public void setWitsUserSt(String witsUserSt) {
		this.witsUserSt = witsUserSt;
	}

	public Date getChangingDate() {
		return changingDate;
	}

	public void setChangingDate(Date changingDate) {
		this.changingDate = changingDate;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public void setRpDoseRateContact(BigDecimal rpDoseRateContact) {
		this.rpDoseRateContact = rpDoseRateContact;
	}

	public void setRpDoseRate1m(BigDecimal rpDoseRate1m) {
		this.rpDoseRate1m = rpDoseRate1m;
	}

	public void setRpSurfaceContamination(Boolean rpSurfaceContamination) {
		this.rpSurfaceContamination = rpSurfaceContamination;
	}

	public void setRpMeasurementDate(Date rpMeasurementDate) {
		this.rpMeasurementDate = rpMeasurementDate;
	}

	public void setEventClosureDate(Date eventClosureDate) {
		this.eventClosureDate = eventClosureDate;
	}

	public void init(EntityEventType entityEventType)
	{
		Integer index = new Integer(0);
		String strNameEntity = WamatObject.class.getAnnotation(Table.class).name();
		Map<Integer,HistoryTO> mapLocal = new HashMap<Integer, HistoryTO>();
		if (getStatusSt()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[2]));
			historyTOImpl.setValue(getStatusSt());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getOwnerSt()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[4]));
			historyTOImpl.setValue(getOwnerSt());
			mapLocal.put(index++, historyTOImpl);

		}
		if (getWasteManagementPlan()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[12]));
			historyTOImpl.setValue(getWasteManagementPlan());
			mapLocal.put(index++, historyTOImpl);

		}
		if (getHomogeneousLot()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[7]));
			historyTOImpl.setValue(getHomogeneousLot());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getWamatProcessInst()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[11]));
			historyTOImpl.setValue(getWamatProcessInst());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getMaterialSt()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[1]));
			historyTOImpl.setValue(getMaterialSt());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getContainingUnit()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[5]));
			historyTOImpl.setValue(getContainingUnit());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getDescription()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[3]));
			historyTOImpl.setValue(getDescription());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getNetMass()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[6]));
			historyTOImpl.setValue(getNetMass().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getPositionX()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[8]));
			historyTOImpl.setValue(getPositionX().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getPositionY()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[9]));
			historyTOImpl.setValue(getPositionY().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getPositionZ()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[10]));
			historyTOImpl.setValue(getPositionZ().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getCode()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[13]));
			historyTOImpl.setValue(getCode().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getPackaged()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[14]));
			historyTOImpl.setValue(getPackaged().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getRpSurfaceContamination()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[18]));
			historyTOImpl.setValue(getRpSurfaceContamination().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getRpDoseRate1m()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[17]));
			historyTOImpl.setValue(getRpDoseRate1m().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getRpDoseRateContact()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[16]));
			historyTOImpl.setValue(getRpDoseRateContact().toString());
			mapLocal.put(index++, historyTOImpl);
		}
		if (getRpMeasurementDate()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[19]));
			historyTOImpl.setValue(PersistenceConstants.DEFAULT_DATE_FORMATTER.format(getRpMeasurementDate()));
			mapLocal.put(index++, historyTOImpl);
		}
		if (getEventClosureDate()!=null)
		{
			HistoryTOImpl historyTOImpl = new HistoryTOImpl( witsUserSt , changingDate , workflow);
			historyTOImpl.setRefer(strNameEntity);
			historyTOImpl.setField(getCommentColumnTable(PersistenceConstants.FIELD_NAME_TABLE_WAMAT_OBJECT[20]));
			historyTOImpl.setValue(PersistenceConstants.DEFAULT_DATE_FORMATTER.format(getEventClosureDate()));
			mapLocal.put(index++, historyTOImpl);
		}
		mapChanging.put(entityEventType, mapLocal);
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
