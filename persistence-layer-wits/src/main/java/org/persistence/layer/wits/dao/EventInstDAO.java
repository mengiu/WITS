package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableEvents;
import org.persistence.layer.wits.form.EventInstance;
import org.persistence.layer.wits.form.EventTypeSt;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;

public interface EventInstDAO {
	public int addEventInst(EventInstance ei);
	public List<EventInstance> listEventInst();
	public void removeEventInst(int id);
	public EventInstance getEventInst(int id);
	public void updateEventInst(EventInstance ei);
	public List<EventInstance> listEventInst(WamatObject wamatObject, WitsUserSt user, EventTypeSt eventTypeSt,
			OrdersFieldsTableEvents ordersFieldsTableEvents , Boolean ascending );

}
