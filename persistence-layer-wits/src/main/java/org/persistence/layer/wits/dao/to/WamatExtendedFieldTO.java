package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.util.Map;

import org.persistence.layer.wits.enumusertypes.EntityEventType;


public interface WamatExtendedFieldTO extends Serializable {
	public int getIdWamatExtendedField();
	public Boolean getConfirmed();
	public String getWamatObject();
	public String getWamatExtendedFieldTypeSt();
	public String getValue();
	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging();
	
}
