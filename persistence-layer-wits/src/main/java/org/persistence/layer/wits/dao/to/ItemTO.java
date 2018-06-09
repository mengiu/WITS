package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.persistence.layer.wits.enumusertypes.EntityEventType;

public interface ItemTO extends Serializable {
	public int getIdItem();
	public String getGenerationProcessInstance();
	public String getMaterialSt();
	public String getDescriptionItem();
	public Short getItemNumber();
	public BigDecimal getQuantity();
	public String getWamatObject();
	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging();

}
