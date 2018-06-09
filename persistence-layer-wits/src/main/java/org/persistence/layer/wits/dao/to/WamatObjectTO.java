package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.persistence.layer.wits.enumusertypes.EntityEventType;



public interface WamatObjectTO extends Serializable {

	public int getIdWamatObject();
	public String getStatusSt();
	public String getOwnerSt();
	public String getWasteManagementPlan();
	public String getHomogeneousLot();
	public String getWamatProcessInst();
	public String getMaterialSt();
	public String getContainingUnit();
	public String getDescription();
	public BigDecimal getNetMass();
	public Short getPositionX();
	public Short getPositionY();
	public Short getPositionZ();
	public String getCode();
	public Boolean getPackaged();
	public BigDecimal getRpDoseRateContact();
	public BigDecimal getRpDoseRate1m();
	public Boolean getRpSurfaceContamination();
	public Date getRpMeasurementDate();
	public Date getEventClosureDate();
	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging();
}
