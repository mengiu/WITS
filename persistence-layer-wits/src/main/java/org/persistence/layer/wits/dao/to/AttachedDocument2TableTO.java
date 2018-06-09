package org.persistence.layer.wits.dao.to;

import java.io.Serializable;
import java.util.Map;

import org.persistence.layer.wits.enumusertypes.EntityEventType;

public interface AttachedDocument2TableTO extends Serializable {

	public int getFkAttachedDocument();
	public String getFkTableName();
	public int getFkTableId();
	public String getNameAttachedDocument();
	public Boolean getActive();
	public Map<EntityEventType, Map<Integer, HistoryTO>> getMapChanging();
}
