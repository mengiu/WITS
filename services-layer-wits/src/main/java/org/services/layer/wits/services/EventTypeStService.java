package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.EventTypeSt;

public interface EventTypeStService {
	public int addEventTypeSt(EventTypeSt ets);
	public List<EventTypeSt> listEventTypeSt();
	public void removeEventTypeSt(int id);
	public EventTypeSt getEventTypeSt(int id);
	public void updateEventTypeSt(EventTypeSt ets);

}
