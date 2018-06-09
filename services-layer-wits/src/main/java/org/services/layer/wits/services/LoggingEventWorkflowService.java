package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.LoggingEventWorkflow;

public interface LoggingEventWorkflowService {
	public long addLoggingEventWorkflow(LoggingEventWorkflow lew );
	public List<LoggingEventWorkflow> listLoggingEventWorkflow();
	public void removeLoggingEventWorkflow(long id);
	public LoggingEventWorkflow getLoggingEventWorkflow(long id);
	public void updateLoggingEventWorkflow(LoggingEventWorkflow lew);
	public LoggingEventWorkflow getLoggingEventWorkflow(Object entity , Class<?> classEntity);

}
