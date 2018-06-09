package org.services.layer.wits.services.model;

public enum StatusReturnAnswer {
	OK("OK") ,
	WARNING("WARNING") ,
	ERROR("ERROR");

	private final String statusType;
	StatusReturnAnswer (final String statusType )
	{
		this.statusType = statusType;
	}
	public String getValue() {
		return this.statusType;
	}

}
