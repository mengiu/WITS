package org.persistence.layer.wits.dao.to.impl;


import org.persistence.layer.wits.dao.to.WamatObjectDifferencesTO;
import org.persistence.layer.wits.form.WamatObject;

public class WamatObjectDifferencesTOImpl implements WamatObjectDifferencesTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WamatObject before;
	protected WamatObject after;
	protected WamatObject entitywithChanging;

	public WamatObjectDifferencesTOImpl()
	{
		if (entitywithChanging==null)
			entitywithChanging = new WamatObject();
	}

	public WamatObjectDifferencesTOImpl(WamatObject before , WamatObject after)
	{
		this();
		entitywithChanging.setIdWamatObject(after.getIdWamatObject());
		entitywithChanging.setPackaged(null);
		this.after = after;
		this.before = before;
		init();
	}

	public void init()
	{
		if (getIsStatusStchanged())
		{
			entitywithChanging.setStatusSt(after.getStatusSt());
		}
		if (getIsOwnerStchanged())
		{
			entitywithChanging.setOwnerSt(after.getOwnerSt());
		}
		if (getIsWasteManagementPlanchanged())
		{
			entitywithChanging.setWasteManagementPlanSt(after.getWasteManagementPlanSt());
		}
		if (getIsHomogeneousLotchanged())
		{
			entitywithChanging.setHomogeneousLot(after.getHomogeneousLot());
		}
		if (getIsWamatProcessInstchanged())
		{
			entitywithChanging.setGenerationProcessInstance(after.getGenerationProcessInstance());
		}
		if (getIsMaterialStchanged())
		{
			entitywithChanging.setMaterialSt(after.getMaterialSt());
		}
		if (getIsContainingUnitchanged())
		{
			entitywithChanging.setContainingUnitByFkContainingUnit(after.getContainingUnitByFkContainingUnit());
		}
		if (getIsDescriptionchanged())
		{
			entitywithChanging.setDescriptionWamatObject(after.getDescriptionWamatObject());
		}
		if (getIsNetMasschanged())
		{
			entitywithChanging.setNetMass(after.getNetMass());
		}
		if (getIsPositionXchanged())
		{
			entitywithChanging.setPositionX(after.getPositionX());
		}
		if (getIsPositionYchanged())
		{
			entitywithChanging.setPositionY(after.getPositionY());
		}
		if (getIsPositionZchanged())
		{
			entitywithChanging.setPositionZ(after.getPositionZ());
		}
		if (getIsCodechanged())
		{
			entitywithChanging.setNameWamatObject(after.getNameWamatObject());
		}
		if (getIsPackagedchanged())
		{
			entitywithChanging.setPackaged(after.getPackaged());
		}
		if (getIsRpSurfaceContaminationchanged())
		{
			entitywithChanging.setRpSurfaceContamination(after.getRpSurfaceContamination());
		}
		if (getIsRpDoseRate1mchanged())
		{
			entitywithChanging.setRpDoseRate1m(after.getRpDoseRate1m());
		}
		if (getIsRpDoseRateContactchanged())
		{
			entitywithChanging.setRpDoseRateContact(after.getRpDoseRateContact());
		}
		if (getIsRpMeasurementDatechanged())
		{
			entitywithChanging.setRpMeasurementDate(after.getRpMeasurementDate());
		}

		if (getIsEventClosureDatechanged())
		{
			entitywithChanging.setEventClosureDate(after.getEventClosureDate());
		}
		if (getIsQuantityUnitchanged())
		{
			entitywithChanging.setQuantityUnitSt(after.getQuantityUnitSt());
		}
		
	}
	public WamatObject getBefore() {
		return before;
	}

	public void setBefore(WamatObject before) {
		this.before = before;
	}

	public WamatObject getAfter() {
		return after;
	}

	public void setAfter(WamatObject after) {
		this.after = after;
	}

	public Boolean getIsStatusStchanged() {
		if (before.getStatusSt()!=null &&
				after.getStatusSt()!=null)
			return !(before.getStatusSt().getIdStatusSt()==
			         after.getStatusSt().getIdStatusSt());
		else
		{
			if (before.getStatusSt()==null &&
					after.getStatusSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsOwnerStchanged() {
		if (before.getOwnerSt()!=null &&
				after.getOwnerSt()!=null)
			return !(before.getOwnerSt().getIdOwnerSt()==
			         after.getOwnerSt().getIdOwnerSt());
		else
		{
			if (before.getOwnerSt()==null &&
					after.getOwnerSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsWasteManagementPlanchanged() {
		if (before.getWasteManagementPlanSt()!=null &&
				after.getWasteManagementPlanSt()!=null)
			return !(before.getWasteManagementPlanSt().getIdWasteManagementPlanSt()==
			        after.getWasteManagementPlanSt().getIdWasteManagementPlanSt());
		else
		{
			if (before.getWasteManagementPlanSt()==null &&
					after.getWasteManagementPlanSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsHomogeneousLotchanged() {
		if (before.getHomogeneousLot()!=null &&
				after.getHomogeneousLot()!=null)
			return !(before.getHomogeneousLot().getIdHomogeneousLot()==
			         after.getHomogeneousLot().getIdHomogeneousLot());
		else
		{
			if (before.getHomogeneousLot()==null &&
					after.getHomogeneousLot()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsWamatProcessInstchanged() {
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


	public Boolean getIsMaterialStchanged() {
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


	public Boolean getIsContainingUnitchanged() {
		if (before.getContainingUnitByFkContainingUnit()!=null &&
				after.getContainingUnitByFkContainingUnit()!=null)
			return !(before.getContainingUnitByFkContainingUnit().getIdContainingUnit()==
			         after.getContainingUnitByFkContainingUnit().getIdContainingUnit());
		else
		{
			if (before.getContainingUnitByFkContainingUnit()==null &&
					after.getContainingUnitByFkContainingUnit()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsDescriptionchanged() {
		if (before.getDescriptionWamatObject()!=null &&
				after.getDescriptionWamatObject()!=null)
			return !before.getDescriptionWamatObject().equals(after.getDescriptionWamatObject());
		else
		{
			if (before.getDescriptionWamatObject()==null &&
					after.getDescriptionWamatObject()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsNetMasschanged() {
		if (before.getNetMass()!=null &&
				after.getNetMass()!=null)
			return !(before.getNetMass().compareTo(after.getNetMass())==0);
		else
		{
			if (before.getNetMass()==null &&
					after.getNetMass()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsQuantityUnitchanged() {
		if (before.getQuantityUnitSt()!=null &&
				after.getQuantityUnitSt()!=null)
			return !(before.getQuantityUnitSt().getIdQuantityUnitSt()==
	         after.getQuantityUnitSt().getIdQuantityUnitSt());
		else
		{
			if (before.getQuantityUnitSt()==null &&
					after.getQuantityUnitSt()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsPositionXchanged() {
		if (before.getPositionX()!=null &&
				after.getPositionX()!=null)
			return before.getPositionX()!=after.getPositionX();
		else
		{
			if (before.getPositionX()==null &&
					after.getPositionX()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsPositionYchanged() {
		if (before.getPositionY()!=null &&
				after.getPositionY()!=null)
			return before.getPositionY()!=after.getPositionY();
		else
		{
			if (before.getPositionY()==null &&
					after.getPositionY()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsPositionZchanged() {
		if (before.getPositionZ()!=null &&
				after.getPositionZ()!=null)
			return before.getPositionZ()!=after.getPositionZ();
		else
		{
			if (before.getPositionZ()==null &&
					after.getPositionZ()==null)
				return false;
			else
			{
				return true;
			}
		}
	}


	public Boolean getIsCodechanged() {
		if (before.getNameWamatObject()!=null &&
				after.getNameWamatObject()!=null)
			return !before.getNameWamatObject().equals(after.getNameWamatObject());
		else
		{
			if (before.getNameWamatObject()==null &&
					after.getNameWamatObject()==null)
				return false;
			else
			{
				return true;
			}
		}
	}



	public Boolean getIsRpSurfaceContaminationchanged() {
		if (before.getRpSurfaceContamination()!=null &&
				after.getRpSurfaceContamination()!=null)
			return before.getRpSurfaceContamination()!=after.getRpSurfaceContamination();
		else
		{
			if (before.getRpSurfaceContamination()==null &&
					after.getRpSurfaceContamination()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsPackagedchanged() {
		if (before.getPackaged()!=null &&
				after.getPackaged()!=null)
			return before.getPackaged()!=after.getPackaged();
		else
		{
			if (before.getPackaged()==null &&
					after.getPackaged()==null)
				return false;
			else
			{
				return true;
			}
		}
	}
	
	public Boolean getIsRpDoseRate1mchanged() {
		if (before.getRpDoseRate1m()!=null &&
				after.getRpDoseRate1m()!=null)
			return !(before.getRpDoseRate1m().compareTo(after.getRpDoseRate1m())==0);
		else
		{
			if (before.getRpDoseRate1m()==null &&
					after.getRpDoseRate1m()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsRpDoseRateContactchanged() {
		if (before.getRpDoseRateContact()!=null &&
				after.getRpDoseRateContact()!=null)
			return !(before.getRpDoseRateContact().compareTo(after.getRpDoseRateContact())==0);
		else
		{
			if (before.getRpDoseRateContact()==null &&
					after.getRpDoseRateContact()==null)
				return false;
			else
			{
				return true;
			}
		}
	}
	
	public Boolean getIsRpMeasurementDatechanged() {
		if (before.getRpMeasurementDate()!=null &&
				after.getRpMeasurementDate()!=null)
			return !(before.getRpMeasurementDate().compareTo(after.getRpMeasurementDate())==0);
		else
		{
			if (before.getRpMeasurementDate()==null &&
					after.getRpMeasurementDate()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public Boolean getIsEventClosureDatechanged() {
		if (before.getEventClosureDate()!=null &&
				after.getEventClosureDate()!=null)
			return !(before.getEventClosureDate().compareTo(after.getEventClosureDate())==0);
		else
		{
			if (before.getEventClosureDate()==null &&
					after.getEventClosureDate()==null)
				return false;
			else
			{
				return true;
			}
		}
	}

	public WamatObject getEntitywithChanging() {
		return entitywithChanging;
	}

	public void setEntitywithChanging(WamatObject entitywithChanging) {
		this.entitywithChanging = entitywithChanging;
	}

}
