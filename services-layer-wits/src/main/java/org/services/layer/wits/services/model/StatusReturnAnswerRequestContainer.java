package org.services.layer.wits.services.model;

public enum StatusReturnAnswerRequestContainer {
	CREATE_CONTAINER("Create_Container") ,
	NO_CONTAINER("No_Container");

	private final String statusReturnAnswerRequestContainer;
	StatusReturnAnswerRequestContainer (final String statusReturnAnswerRequestContainer )
	{
		this.statusReturnAnswerRequestContainer = statusReturnAnswerRequestContainer;
	}
	public String getValue() {
		return this.statusReturnAnswerRequestContainer;
	}

}
