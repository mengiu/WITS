package org.persistence.layer.wits.dao;

import java.util.Date;
import java.util.List;





import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.enumusertypes.MonitoredTablesType;
import org.persistence.layer.wits.form.LoggingEventWorkflow;
import org.persistence.layer.wits.form.WitsUserSt;


public interface LoggingEventWorkflowDAO {
	public long addLoggingEventWorkflow(LoggingEventWorkflow lew );
	public List<LoggingEventWorkflow> listLoggingEventWorkflow();
	public void removeLoggingEventWorkflow(long id);
	public LoggingEventWorkflow getLoggingEventWorkflow(long id);
	public void updateLoggingEventWorkflow(LoggingEventWorkflow lew);
	public LoggingEventWorkflow getLoggingEventWorkflow(Object entity , 
			Class<?> classEntity, EntityEventType entityEventType );
	public List<LoggingEventWorkflow> getLoggingEventWorkflowChanging(List<Object> listEntity , Class<?> classEntity ,
			WitsUserSt witsUserSt , Date dateFrom , Date dateTo , 
			MonitoredTablesType monitoredTablesType,
			List<EntityEventType> listEntityEventType );
	public LoggingEventWorkflow getLoggingEventWorkflow(Object entity , Class<?> classEntity);
	
}
