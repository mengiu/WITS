package org.services.layer.wits.services.model;

import java.io.Serializable;

public class InfoOwnerObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String previousOwner;
    private String nextOwner;
    private boolean notifyOwner;
    
    public InfoOwnerObject()
    {
    	setNotifyOwner(false);
    }

	public String getPreviousOwner() {
		return previousOwner;
	}

	public void setPreviousOwner(String previousOwner) {
		this.previousOwner = previousOwner;
	}

	public String getNextOwner() {
		return nextOwner;
	}

	public void setNextOwner(String nextOwner) {
		this.nextOwner = nextOwner;
	}

	public boolean isNotifyOwner() {
		return notifyOwner;
	}

	public void setNotifyOwner(boolean notifyOwner) {
		this.notifyOwner = notifyOwner;
	}
}
