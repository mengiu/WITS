package org.persistence.layer.wits.dao.to.impl;

import org.persistence.layer.wits.dao.to.CurrentWorkingWorkflowInfo;

public class CurrentWorkingWorkflowInfoImpl implements
		CurrentWorkingWorkflowInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    String executionId;
    String processInstanceId;
    String activitiTaskId;

    public CurrentWorkingWorkflowInfoImpl()
    {
    	
    }
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getActivitiTaskId() {
		return activitiTaskId;
	}
	public void setActivitiTaskId(String activitiTaskId) {
		this.activitiTaskId = activitiTaskId;
	}

}
