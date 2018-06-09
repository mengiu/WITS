package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.EventTypeSt;

public interface EventTypeStDAO {
	public int addEventTypeSt(EventTypeSt ets);
	public List<EventTypeSt> listEventTypeSt();
	public void removeEventTypeSt(int id);
	public EventTypeSt getEventTypeSt(int id);
	public void updateEventTypeSt(EventTypeSt ets);

}
