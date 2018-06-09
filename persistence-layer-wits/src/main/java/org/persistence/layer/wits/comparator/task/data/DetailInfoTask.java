package org.persistence.layer.wits.comparator.task.data;

import java.io.Serializable;
import java.util.Date;

public class DetailInfoTask implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String idTask;
	protected String wamatObjectCode;
	protected String cuType;
	protected String initiator;
	protected String nameprocess;
	protected String startDate;
	protected String wamatinput;
	protected String taskname;
	protected Date creationdate;
	protected String destination;
	protected String containingunitcode;
	
	public String getCuType() {
		return cuType;
	}

	public void setCuType(String cuType) {
		this.cuType = cuType;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getWamatObjectCode() {
		return wamatObjectCode;
	}

	public void setWamatObjectCode(String wamatObjectCode) {
		this.wamatObjectCode = wamatObjectCode;
	}

	public String getNameprocess() {
		return nameprocess;
	}

	public void setNameprocess(String nameprocess) {
		this.nameprocess = nameprocess;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getWamatinput() {
		return wamatinput;
	}

	public void setWamatinput(String wamatinput) {
		this.wamatinput = wamatinput;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContainingunitcode() {
		return containingunitcode;
	}

	public void setContainingunitcode(String containingunitcode) {
		this.containingunitcode = containingunitcode;
	}

}
