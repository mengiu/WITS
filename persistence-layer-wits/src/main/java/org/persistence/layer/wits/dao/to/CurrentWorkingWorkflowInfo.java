package org.persistence.layer.wits.dao.to;

import java.io.Serializable;

public interface CurrentWorkingWorkflowInfo extends Serializable {
	public String getExecutionId();
	public String getProcessInstanceId();
	public String getActivitiTaskId();
}
