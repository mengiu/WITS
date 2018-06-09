package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.util.Map;

import org.persistence.layer.wits.enumusertypes.EntityEventType;

public interface WacComplianceClaimTO extends Serializable {
	public String getWacSt();
	public String getWamatObject();
	public int getIdWacComplianceClaim();
	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging();
}
